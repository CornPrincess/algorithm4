package com.minmin.algorithm4.ch2sort.merge;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-27
 * Time: 20:21
 */
public class Merge2 {
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        Comparable[] aux = new Comparable[a.length];
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <=hi; k++) {
            // left side use out, use right side
            if (i > mid) {
                a[k] = aux[j++];
            // right side use out, use left side
            } else if (j > hi) {
                a[k] = aux[i++];
            // right side number smaller than left side number, use right side
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            // left side number smaller than right side number, use left side
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}
