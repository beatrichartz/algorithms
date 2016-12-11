package beatrichartz.algorithms.quick_union.examples.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLength;
    private final int virtualTopSite;
    private final int virtualBottomSite;
    private boolean[] sites;
    private WeightedQuickUnionUF fullUnionFind;
    private WeightedQuickUnionUF unionFindOnlyTop;

    public Percolation(int sideLength) {
        if (sideLength <= 0) { throw new IllegalArgumentException(); }
        this.sideLength = sideLength;

        this.virtualTopSite = sideLength * sideLength;
        this.virtualBottomSite = sideLength * sideLength + 1;
        this.sites = new boolean[sideLength * sideLength];

        this.fullUnionFind = new WeightedQuickUnionUF(sideLength * sideLength + 2);
        this.unionFindOnlyTop = new WeightedQuickUnionUF(sideLength * sideLength + 1);

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                sites[i * sideLength + j] = false;
            }
        }
    }

    public void open(int row, int col) {
        checkIndex(row, col);
        connectOpenSite(row, col, row - 1, col);
        connectOpenSite(row, col, row + 1, col);
        connectOpenSite(row, col, row, col - 1);
        connectOpenSite(row, col, row, col + 1);

        sites[getSiteIndex(row, col)] = true;
    }

    private void connectOpenSite(int openRow, int openCol, int toRow, int toCol) {
        int siteIndex = getSiteIndex(openRow, openCol);
        if (toCol <= 0 || toCol > sideLength) { return; }

        if (toRow <= 0) {
            fullUnionFind.union(siteIndex, virtualTopSite);
            unionFindOnlyTop.union(siteIndex, virtualTopSite);
            return;
        }
        if (toRow > sideLength) {
            fullUnionFind.union(siteIndex, virtualBottomSite);
            return;
        }

        if (!isOpen(toRow, toCol)) { return; }

        fullUnionFind.union(siteIndex, getSiteIndex(toRow, toCol));
        unionFindOnlyTop.union(siteIndex, getSiteIndex(toRow, toCol));
    }

    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        if (!isOpen(row, col)) { return false; }

        return unionFindOnlyTop.connected(virtualTopSite, getSiteIndex(row, col));
    }

    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return sites[getSiteIndex(row, col)];
    }

    private int getSiteIndex(int row, int col) {
        return (row - 1) * sideLength + col - 1;
    }

    public boolean percolates() {
        return fullUnionFind.connected(virtualTopSite, virtualBottomSite);
    }

    private void checkIndex(int row, int col) {
        if (row > sideLength || row <= 0 || col > sideLength || col <= 0) { throw new IndexOutOfBoundsException(); }
    }


}
