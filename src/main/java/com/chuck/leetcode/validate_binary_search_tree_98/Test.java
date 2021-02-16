package com.chuck.leetcode.validate_binary_search_tree_98;

import com.chuck.leetcode.validate_binary_search_tree_98.Solution.TreeNode;

public class Test {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(1);
    root.right = new TreeNode(4);
    TreeNode child = root.right;
    child.left = new TreeNode(3);
    child.right = new TreeNode(6);
    Solution solution = new Solution();
    System.out.println(solution.resolve2(root));
  }
}
