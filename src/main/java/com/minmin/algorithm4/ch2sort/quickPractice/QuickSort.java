package com.minmin.algorithm4.ch2sort.quickPractice;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-28
 * Time: 22:08
 */
public class QuickSort {
    private QuickSort() {}

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

//    public static void sort2(Comparable[] a, int lo, int hi) {
//        if (hi <= lo) return;
//
//    }
//
//    private static int partition2(Comparable[] a, int lo, int hi) {
//        int lt = lo;
//        int i = lo + 1;
//        int gt = hi;
//        Comparable v = a[lo];
//        while (i <= gt) {
//            int cmp = a[i].compareTo(v);
//            if (cmp < 0) exch(a, lt++, i++);
//            else if (cmp > 0) exch(a, i, gt--);
//            else i++;
//        }
//    }

    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k > a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (hi > lo) {
            int i = partition(a, lo, hi);
            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }


    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            while (less(v, a[--j])) {
                if (j == lo) break;
            }

            if (i >= j) break;

            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;   // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        QuickSort.sort(a);
        show(a);
        assert isSorted(a);

        // shuffle
        StdRandom.shuffle(a);

        // display results again using select
//        StdOut.println();
//        for (int i = 0; i < a.length; i++) {
//            String ith = (String) QuickSort.select(a, i);
//            StdOut.println(ith);
//        }
    }

}
