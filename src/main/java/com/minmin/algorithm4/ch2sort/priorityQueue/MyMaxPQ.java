package com.minmin.algorithm4.ch2sort.priorityQueue;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-29
 * Time: 00:47
 */
public class MyMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public MyMaxPQ() {
        this(1);
    }

    public MyMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void insert(Key x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(k, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 67, 3, 5, 28, 8, 8, 6};
        int k = 5;
        MyMaxPQ myMaxPQ = new MyMaxPQ(k);
        for (int i = 0; i < arr.length; i++) {
            myMaxPQ.insert(arr[i]);
            if (myMaxPQ.size() > k) {
                myMaxPQ.delMax();
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.println(myMaxPQ.delMax());
        }
    }
}
