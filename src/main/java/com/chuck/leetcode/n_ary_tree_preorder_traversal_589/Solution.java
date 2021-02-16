package com.chuck.leetcode.n_ary_tree_preorder_traversal_589;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历：根-左-右
 * 中序遍历：左-根-右
 * 后序遍历：左-右-根
 *
 * 1.前序遍历递归，时间复杂度O(N)
 */
public class Solution {

  class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  /**
   * 递归前序遍历
   * @param root
   * @return
   */
  private List<Integer> resolve1(Node root) {

    List<Integer> result = new ArrayList<>();
    doResolve1(root, result);
    return result;
  }

  private void doResolve1(Node root, List<Integer> result) {

    if (root == null) {
      return;
    }

    result.add(root.val);
    for (Node child : root.children) {
      doResolve1(child, result);
    }
  }
}
