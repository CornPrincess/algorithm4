package com.minmin.algorithm4.ch2sort.priorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-29
 * Time: 00:34
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        int[] arr = {1,3,5,6,3,1,6,8,3,4,8};
        int[] result = topK(arr, 5);
        System.out.println(Arrays.toString(result));
    }

    private static int[] topK(int[] arr, int k) {
        if (k < 0 || k > arr.length) {
            return null;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }
        return result;
    }
}
