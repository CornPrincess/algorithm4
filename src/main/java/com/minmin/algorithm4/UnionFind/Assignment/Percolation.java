package com.minmin.algorithm4.UnionFind.Assignment;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-05-12
 * Time: 23:05
 *
 * Performance requirements.  The constructor should take time proportional to n2;
 * all methods should take constant time plus a constant number of calls to the union–find methods union(), find(), connected(), and count().
 */
public class Percolation {
    private boolean[][] grid;
    private final int virtualTop;
    private final int virtualBottom;
    private final int size;
    private int openCount = 0;
//    private final WeightedQuickUnionUF uf;
//    private final WeightedQuickUnionUF uf;
    private final QuickFindUF uf;
    private final QuickFindUF ufForIsFull;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must bigger than zero.");
        }
        grid = new boolean[n][n];
//        uf = new WeightedQuickUnionUF(n * n + 2);
//        ufForIsFull = new WeightedQuickUnionUF(n * n + 1);
        uf = new QuickFindUF(n * n + 2);
        ufForIsFull = new QuickFindUF(n * n + 1);
        size = n;
        virtualTop = size * size;
        virtualBottom = size * size + 1;
//        initVirtualTopUnion();
//        initVirtualBottomUnion();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row - 1][col - 1] = true;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        for (int i = 0; i < dx.length; i++) {
            int r = row + dx[i];
            int c = col + dy[i];
            try {
                validate(r, c);
            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
                continue;
            }
            if (isOpen(r, c)) {
                uf.union(convertRowColToUFIndex(row, col), convertRowColToUFIndex(r, c));
                ufForIsFull.union(convertRowColToUFIndex(row, col), convertRowColToUFIndex(r, c));
            }
        }

        // if row == 1, union virtualTop
        if (row == 1) {
            uf.union(convertRowColToUFIndex(row, col), virtualTop);
            ufForIsFull.union(convertRowColToUFIndex(row, col), virtualTop);
        }

        // if row == size, union virtualBottom
        if (row == size) {
            uf.union(convertRowColToUFIndex(row, col), virtualBottom);
        }
        openCount++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
//        return uf.connected(virtualTop, convertRowColToUFIndex(row, col));
        return ufForIsFull.find(virtualTop) == ufForIsFull.find(convertRowColToUFIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
//        return uf.connected(virtualTop, virtualBottom);
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    private void validate(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("row or col is not between 1 and " + size);
        }
    }

//    private void initVirtualTopUnion() {
//        virtualTop = size * size;
//        for (int i = 0; i < size; i++) {
//            uf.union(i, virtualTop);
//        }
//    }
//
//    private void initVirtualBottomUnion() {
//        virtualBottom = size * size + 1;
//        int start = size * (size - 1);
//        int end = size * size;
//        for (int i = start; i < end; i++) {
//            uf.union(i, virtualBottom);
//        }
//    }

    private int convertRowColToUFIndex(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        System.out.println(p.isOpen(1, 1));
        System.out.println(p.isFull(1, 1));
        p.open(1, 1);
        System.out.println(p.isOpen(1, 1));
        System.out.println(p.isFull(1, 1));
        p.open(2, 2);
        System.out.println(p.percolates());
    }
}
