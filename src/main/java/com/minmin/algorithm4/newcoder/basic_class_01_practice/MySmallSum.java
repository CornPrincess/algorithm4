package com.minmin.algorithm4.newcoder.basic_class_01_practice;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-30
 * Time: 00:26
 */
public class MySmallSum {
    private static int[] aux;

    private static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    private static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        aux = new int[arr.length];
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        return mergeSort(arr, lo, mid) + mergeSort(arr, mid + 1, hi) + merge(arr, lo, mid, hi);
    }

    private static int merge(int[] arr, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int i = lo;
        int j = mid + 1;
        int res = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (aux[i] >= aux[j]) {
                arr[k] = aux[j++];
            } else {
                res += aux[i] * (hi - j + 1);
                arr[k] = aux[i++];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ComparatorUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ComparatorUtil.copyArray(arr1);
            int result =  smallSum(arr1);
            int expect = comparator(arr2);
            if (result != expect) {
                succeed = false;
                ComparatorUtil.printArray(arr1);
                ComparatorUtil.printArray(arr2);
                System.out.println(result);
                System.out.println(expect);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
