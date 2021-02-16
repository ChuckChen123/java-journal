package com.chuck.algorithm.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UnionFindTest {

  @Test
  void testQuickFind() {

    commonTest(new QuickFind(9));
  }

  @Test
  void testQuickUnion() {

    commonTest(new QuickUnion(9));
  }

  @Test
  void testWeightedQuickUnion() {
    commonTest(new WeightedQuickUnion(9));
  }

  private void commonTest(UnionFind unionFind) {

    unionFind.union(4, 3);
    unionFind.union(3, 8);
    unionFind.union(6, 5);
    unionFind.union(9, 4);
    unionFind.union(2, 1);

    // false
    assertEquals(false, unionFind.connected(0, 7));
    // true
    assertEquals(true, unionFind.connected(8, 9));

    unionFind.union(5, 0);
    unionFind.union(7, 2);
    unionFind.union(6, 1);
    unionFind.union(1, 0);

    // true
    assertEquals(true, unionFind.connected(0, 7));
  }
}
