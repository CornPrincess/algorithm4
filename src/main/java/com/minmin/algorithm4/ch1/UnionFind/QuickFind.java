package com.minmin.algorithm4.ch1.UnionFind;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2020-05-12
 * Time: 20:26
 */
public class QuickFind {
    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == id[pid]) {
                id[i] = qid;
            }
        }
    }
}
