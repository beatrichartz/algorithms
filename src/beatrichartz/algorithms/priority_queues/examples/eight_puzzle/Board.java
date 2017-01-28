package beatrichartz.algorithms.priority_queues.examples.eight_puzzle;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {
    private int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = getCopy(blocks);
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < blocks.length * blocks.length; i++) {
            int x = i % blocks.length;
            int y = i / blocks.length;

            int block = blocks[y][x];
            if (block != 0 && block != i + 1) hamming++;
        }

        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < blocks.length * blocks.length; i++) {
            int x = i % blocks.length;
            int y = i / blocks.length;

            int block = blocks[y][x];
            if (block != 0 && block != i + 1) {
                int blockX = (block - 1) % blocks.length;
                int blockY = (block - 1) / blocks.length;

                manhattan += Math.abs(blockX - x) + Math.abs(blockY - y);
            }
        }

        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board otherBoard = (Board) o;

        if (otherBoard.blocks.length != blocks.length) return false;

        for (int i = 0; i < blocks.length * blocks.length; i++) {
            int x = i % blocks.length;
            int y = i / blocks.length;

            int block = blocks[y][x];
            int otherBlock = otherBoard.blocks[y][x];
            if (block != otherBlock) return false;
        }

        return true;
    }

    public Board twin() {
        int x1 = 0;
        int y1 = 0;
        int x2 = blocks.length - 1;
        int y2 = blocks.length - 1;

        if (blocks[x1][y1] == 0) x1++;
        if (blocks[x2][y2] == 0) x2--;

        int[][] twinBlocks = getCopy(blocks);
        int moveBlock = twinBlocks[y1][x1];
        twinBlocks[y1][x1] = twinBlocks[y2][x2];
        twinBlocks[y2][x2] = moveBlock;

        return new Board(twinBlocks);
    }

    private int[][] getCopy(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            int[] innerCopy = new int[blocks.length];
            System.arraycopy(blocks[i], 0, innerCopy, 0, blocks.length);
            copy[i] = innerCopy;
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = blocks.length;
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int randomCoordinate() {
        return StdRandom.uniform(blocks.length - 1);
    }

    private class BoardIterator implements Iterator<Board> {
        private Board[] boards;
        private int position;

        private int[] getNeighbouringPositions(int position, int maxPosition) {
            int[] neighbouringPositions = {position - 1, position + 1};
            if (position - 1 < 0) {
                neighbouringPositions = new int[]{position + 1};
            } else if (position + 1 > maxPosition) {
                neighbouringPositions = new int[]{position - 1};
            }

            return neighbouringPositions;
        }

        public BoardIterator(int[][] blocks) {
            for (int i = 0; i < blocks.length * blocks.length; i++) {
                int x = i % blocks.length;
                int y = i / blocks.length;

                int block = blocks[y][x];
                if (block == 0) {
                    int[] newXs = getNeighbouringPositions(x, blocks.length - 1);
                    int[] newYs = getNeighbouringPositions(y, blocks.length - 1);

                    boards = new Board[newXs.length + newYs.length];
                    for (int j = 0; j < newYs.length; j++) {
                        int[][] yNeighbour = getCopy(blocks);
                        swap(yNeighbour, x, x, y, newYs[j]);
                        boards[j] = new Board(yNeighbour);
                    }

                    for (int j = 0; j < newXs.length; j++) {
                        int[][] xNeighbour = getCopy(blocks);
                        swap(xNeighbour, x, newXs[j], y, y);
                        boards[newYs.length + j] = new Board(xNeighbour);
                    }

                    return;
                }
            }
        }

        public boolean hasNext() {
            return position < boards.length;
        }

        public Board next() {
            if (!hasNext()) throw new NoSuchElementException();

            return boards[position++];
        }

        private void swap(int[][] blocks, int x1, int x2, int y1, int y2) {
            int moveBlock = blocks[y1][x1];
            blocks[y1][x1] = blocks[y2][x2];
            blocks[y2][x2] = moveBlock;
        }

        private int[][] getCopy(int[][] blocks) {
            int[][] copy = new int[blocks.length][blocks.length];
            for (int i = 0; i < blocks.length; i++) {
                int[] innerCopy = new int[blocks.length];
                System.arraycopy(blocks[i], 0, innerCopy, 0, blocks.length);
                copy[i] = innerCopy;
            }

            return copy;
        }
    }

    private class IterableBoard implements Iterable<Board> {
        private int[][] blocks;

        public IterableBoard(int[][] blocks) {
            this.blocks = blocks;
        }

        public Iterator<Board> iterator() {
            return new BoardIterator(blocks);
        }
    }

    public Iterable<Board> neighbors() {
        return new IterableBoard(blocks);
    }
}
