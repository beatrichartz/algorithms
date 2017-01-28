package beatrichartz.algorithms.priority_queues.examples.eight_puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Solver {
    private SearchNode node;
    private SearchNode twinNode;

    private class SearchNode implements Comparable<SearchNode> {
        private final SearchNode previous;
        private final Board board;
        private final int moves;
        private final int priority;

        public SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            this.priority = moves + board.manhattan();
        }

        @Override
        public int compareTo(SearchNode otherNode) {
            int comparison = this.priority - otherNode.getPriority();
            return Math.max(-1, Math.min(1, comparison));
        }

        private int getPriority() {
            return priority;
        }

        private SearchNode getPrevious() {
            return previous;
        }

        private Board getBoard() {
            return board;
        }

        private boolean isGoalNode() {
            return board.isGoal();
        }

        private int getMoves() {
            return moves;
        }

        @Override
        public String toString() {
            return "SearchNode{\n" +
                    "moves =\t" + moves +
                    "\nmanhattan =\t" + board.manhattan() +
                    "\nhamming =\t" + board.hamming() +
                    "\npriority =\t" + priority +
                    "\nboard = \n" + board +
                    "\n}";
        }
    }

    public Solver(Board board) {
        node = new SearchNode(board, 0, null);
        twinNode = new SearchNode(board.twin(), 0, null);

        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        MinPQ<SearchNode> twinNodePriorityQueue = new MinPQ<>();

        while (!node.getBoard().isGoal() && !twinNode.getBoard().isGoal()) {
            node = searchForGoal(priorityQueue, node);
            twinNode = searchForGoal(twinNodePriorityQueue, twinNode);
        }
    }

    private SearchNode searchForGoal(MinPQ<SearchNode> priorityQueue, SearchNode node) {
        SearchNode previous = node.getPrevious();
        Iterable<Board> nextBoardNeighbours = node.getBoard().neighbors();
        for (Board neighbourBoard : nextBoardNeighbours) {
            if (previous != null && previous.getBoard().equals(neighbourBoard)) continue;

            priorityQueue.insert(new SearchNode(neighbourBoard, node.getMoves() + 1, node));
        }
        return priorityQueue.delMin();
    }

    public boolean isSolvable() {
        return node.isGoalNode();
    }

    private class BoardIterator implements Iterator<Board> {
        private final Board[] boards;
        private int index;

        public BoardIterator(SearchNode node) {
            boards = new Board[node.getMoves() + 1];
            while (node != null) {
                boards[node.getMoves()] = node.getBoard();
                node = node.getPrevious();
            }
        }

        public boolean hasNext() {
            return index < boards.length;
        }

        public Board next() {
            if (!hasNext()) throw new NoSuchElementException();

            return boards[index++];
        }
    }

    private class BoardIterable implements Iterable<Board> {
        private final SearchNode node;

        public BoardIterable(SearchNode node) {
            this.node = node;
        }

        public Iterator<Board> iterator() {
            return new BoardIterator(node);
        }
    }

    public Iterable<Board> solution() {
        return isSolvable() ? new BoardIterable(node) : null;
    }

    public int moves() {
        return isSolvable() ? node.getMoves() : -1;
    }

}
