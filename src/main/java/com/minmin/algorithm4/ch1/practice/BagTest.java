package com.minmin.algorithm4.ch1.practice;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-25
 * Time: 20:28
 */
public class BagTest {
    public static void main(String[] args) {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);
        for (int i :bag) {
            System.out.println(i);
        }
    }
}
