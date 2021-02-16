package com.chuck.algorithm.unionfind;

/**
 * 快速查找
 * 复杂度为n方
 * @author chuck
 */
public class QuickFind implements UnionFind {

  private int[] data;

  public QuickFind(int n) {
    this.data = new int[n + 1];

    // 初始化data
    // 插入 1, 2, 3, ... , n
    for (int i=0; i<=n; i++) {
      this.data[i] = i;
    }
  }

  @Override
  public void union(int a, int b) {
    if (this.data[a] == this.data[b]) {
      return;
    }

    // 获得a位置的群标志
    int aValue = this.data[a];
    // 获取b位置的群标志
    int bValue = this.data[b];

    // 将值为aValue的元素的值改为bValue
    for (int i=0; i<this.data.length; i++) {
      if (this.data[i] == aValue) {
        this.data[i] = bValue;
      }
    }
  }

  @Override
  public boolean connected(int a, int b) {
    return this.data[a] == this.data[b];
  }
}
