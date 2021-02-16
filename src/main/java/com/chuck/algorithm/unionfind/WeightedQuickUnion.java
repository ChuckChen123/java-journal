package com.chuck.algorithm.unionfind;

public class WeightedQuickUnion implements UnionFind {

  private int[] data;
  private int[] sz;

  public WeightedQuickUnion(int n) {
    this.data = new int[n + 1];
    this.sz = new int[n + 1];

    // 初始化data
    // 插入 1, 2, 3, ... , n
    for (int i=0; i<=n; i++) {
      this.data[i] = i;
    }

    for (int i=0; i<=n; i++) {
      this.sz[i] = 1;
    }
  }

  private int root(int n) {
    if (n == this.data[n]) {
      return n;
    }

    return root(this.data[n]);
  }

  @Override
  public void union(int a, int b) {
    int aRoot = root(a);
    int bRoot = root(b);

    if (aRoot == bRoot) {
      return;
    }

    if (sz[aRoot] >= sz[bRoot]) {
      data[bRoot] = aRoot;
      sz[aRoot] += sz[bRoot];
    } else {
      data[aRoot] = bRoot;
      sz[bRoot] += sz[aRoot];
    }
  }

  @Override
  public boolean connected(int a, int b) {
    return root(a) == root(b);
  }
}
