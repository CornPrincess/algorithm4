package com.minmin.algorithm4.ch1.UnionFind.Assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-05-13
 * Time: 19:07
 */
public class PercolationStats {
    private final double[] thresholds;
    private final int trails;
    private final int size;
    private final double CONFIDENCE_95 = 1.96;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trials is out of bounds");
        }

        this.trails = trials;
        this.thresholds = new double[trials];
        this.size = n;

        for (int i = 0; i < trials; i++) {
            thresholds[i] = calcPercolationThreshold();
        }
        this.mean = StdStats.mean(this.thresholds);
        this.stddev = this.trails == 1 ? Double.NaN : StdStats.stddev(this.thresholds);
    }

    // sample mean of percolation threshold
    public double mean() {
//        this.mean = StdStats.mean(this.thresholds);
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
//        if (this.trails == 1) {
//            return Double.NaN;
//        }
//        return StdStats.stddev(this.thresholds);
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean - CONFIDENCE_95 * this.stddev / Math.sqrt(this.trails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean + CONFIDENCE_95 * this.stddev / Math.sqrt(this.trails);
    }

    private double calcPercolationThreshold() {
        Percolation p = new Percolation(this.size);
        int row;
        int col;
        while (!p.percolates()) {
            row = StdRandom.uniform(this.size) + 1;
            col = StdRandom.uniform(this.size) + 1;
            p.open(row, col);
        }
        return p.numberOfOpenSites() / Math.pow(this.size, 2);
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch time = new Stopwatch();
//        int n = Integer.parseInt(args[0]);
//        int trails = Integer.parseInt(args[1]);
        int n = 100;
        for (int i = 10; i < 400; i = i * 2) {
            PercolationStats stats = new PercolationStats(i, 100);
            StdOut.println("n = " + i + ", trails = " + 100);
            StdOut.printf("mean = %f\n", stats.mean());
            StdOut.printf("stddev = %-26f\n", stats.stddev());
            StdOut.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
            StdOut.println(time.elapsedTime() + " seconds");
            StdOut.println("=================================================");
        }
    }
}
