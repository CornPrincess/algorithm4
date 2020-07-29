package com.minmin.algorithm4.ch1.practice;

import edu.princeton.cs.algs4.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-25
 * Time: 20:14
 */
public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int size = stack.size();
//        for (int i = 0; i < size; i++) {
//            System.out.println(stack.pop());
//        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
