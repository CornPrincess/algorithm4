package com.minmin.algorithm4.newcoder.basic_class_01_practice;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-29
 * Time: 23:55
 */
public class MyMergeTest {
    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ComparatorUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ComparatorUtil.copyArray(arr1);
            MyMerge.sort(arr1);
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
