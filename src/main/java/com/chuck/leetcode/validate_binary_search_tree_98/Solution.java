package com.chuck.leetcode.validate_binary_search_tree_98;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.递归设定每个节点的边界，判断各节点是否满足在边界中，时间复杂度O(N)
 * 2.进行中序遍历，判断节点是否是依次递增，时间复杂度O(N)
 */
public class Solution {

  /**
   * 递归设定每个节点的边界，判断各节点是否满足在边界中，时间复杂度O(N)
   *
   * @param root
   * @return
   */
  public boolean resolve1(TreeNode root) {

    return r1(root, null, null);
  }

  /**
   * 进行中序遍历，判断节点是否是依次递增
   * @param root
   * @return
   */
  public boolean resolve2(TreeNode root) {
    return r2(root, new ArrayList<>());
  }

  private boolean r1(TreeNode root, Integer lowerBound, Integer upperBound) {
    if (root == null) {
      return true;
    }

    if (lowerBound != null && root.val <= lowerBound) {
      return false;
    }

    if (upperBound != null && root.val >= upperBound) {
      return false;
    }

    if (!r1(root.left, lowerBound, root.val) || !r1(root.right, root.val, upperBound)) {
      return false;
    }

    return true;
  }

  private boolean r2(TreeNode root, List<Integer> arr) {

    if (root == null) {
      return true;
    }

    if (!r2(root.left, arr)) {
      return false;
    }
    if (arr.size() > 0 && arr.get(arr.size() - 1) >= root.val) {
      return false;
    }
    arr.add(root.val);
    if (!r2(root.right, arr)) {
      return false;
    }

    return true;
  }

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}
