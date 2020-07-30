package com.minmin.algorithm4.newcoder.basic_class_01_practice;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-30
 * Time: 01:33
 */
public class MyNetherLandsFlag {
    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    private static void sort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int lo, int hi) {
        if (hi <=  lo) return;
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        int v = arr[lo];
        while (i <= gt) {
            if (arr[i] < v) {
                exch(arr, i++, lt++);
            } else if (arr[i] > v) {
                exch(arr, i, gt--);
            } else {
                i++;
            }
        }
        sort(arr, lo, lt - 1);
        sort(arr, gt+1, hi);
    }

    private static void exch(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ComparatorUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ComparatorUtil.copyArray(arr1);
            sort(arr1);
            comparator(arr2);
            if (!ComparatorUtil.isEqual(arr1, arr2)) {
                succeed = false;
                ComparatorUtil.printArray(arr1);
                ComparatorUtil.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
