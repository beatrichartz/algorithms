package beatrichartz.algorithms.quick_union.examples.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLength;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private boolean[] sites;
    private WeightedQuickUnionUF unionFind;

    public Percolation(int sideLength) {
        if (sideLength <= 0) { throw new IllegalArgumentException(); }
        this.sideLength = sideLength;

        this.virtualTopSite = sideLength * sideLength;
        this.virtualBottomSite = sideLength * sideLength + 1;
        this.sites = new boolean[sideLength * sideLength];

        this.unionFind = new WeightedQuickUnionUF(sideLength * sideLength + 2);

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                sites[i * sideLength + j] = false;
            }
        }
    }

    public void open(int row, int col) {
        connectOpenSite(row, col, row - 1, col);
        connectOpenSite(row, col, row + 1, col);
        connectOpenSite(row, col, row, col - 1);
        connectOpenSite(row, col, row, col + 1);

        sites[getSiteIndex(row, col)] = true;
    }

    private void connectOpenSite(int openRow, int openCol, int toRow, int toCol) {
        int siteIndex = getSiteIndex(openRow, openCol);
        if (toCol <= 0 || toCol > sideLength) return;

        if (toRow <= 0) {
            unionFind.union(siteIndex, virtualTopSite);
            return;
        }
        if (toRow > sideLength) {
            unionFind.union(siteIndex, virtualBottomSite);
            return;
        }

        if (!isOpen(toRow, toCol)) return;

        unionFind.union(siteIndex, getSiteIndex(toRow, toCol));
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;

        return unionFind.connected(virtualTopSite, getSiteIndex(row, col));
    }

    public boolean isOpen(int row, int col) {
        return sites[getSiteIndex(row, col)];
    }

    private int getSiteIndex(int row, int col) {
        return (row - 1) * sideLength + col - 1;
    }

    public boolean percolates() {
        return unionFind.connected(virtualTopSite, virtualBottomSite);
    }

}
