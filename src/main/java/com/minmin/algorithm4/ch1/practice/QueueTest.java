package com.minmin.algorithm4.ch1.practice;

import edu.princeton.cs.algs4.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-07-25
 * Time: 20:19
 */
public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        int size = queue.size();
//        for (int i = 0; i < size; i++) {
//            System.out.println(queue.dequeue());
//        }
//        while (!queue.isEmpty()) {
//            System.out.println(queue.dequeue());
//        }
        for (int i : queue) {
            System.out.println(i);
        }
    }
}
