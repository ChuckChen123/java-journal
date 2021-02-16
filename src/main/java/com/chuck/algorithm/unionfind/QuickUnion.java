package com.chuck.algorithm.unionfind;

/**
 * @author chuck
 */
public class QuickUnion implements UnionFind {

  private int[] data;

  public QuickUnion(int n) {
    this.data = new int[n + 1];

    // 初始化data
    // 插入 1, 2, 3, ... , n
    for (int i=0; i<=n; i++) {
      this.data[i] = i;
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
    this.data[root(a)] = this.data[root(b)];
  }

  @Override
  public boolean connected(int a, int b) {
    return root(a) == root(b);
  }
}
