package com.chuck.leetcode.maximum_depth_of_binary_tree_104;

/**
 * 1.递归，遍历左右子树，谁的深度大用谁，时间复杂度O(N)
 */
public class Solution {

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public int resolve1(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int l = resolve1(root.left);
        int r = resolve1(root.right);

        int d = (l > r) ? l : r;
        return d + 1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
