package com.chuck.algorithm.unionfind;

public interface UnionFind {

  void union(int a, int b);

  boolean connected(int a, int b);
}
