package com.chuck.leetcode.rotate_array_189;

/**
 * 1.暴力解法，时间复杂度O(N * N)
 * 2.旋转数组，时间复杂度O(N)
 */
public class Solution {

    public static void main(String[] args) {
        rotate1(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }

    /**
     * 暴力解法
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {

        k = k % nums.length;
        for (int i=0; i<k; i++) {
            int lo = 0;
            int hi = 1;
            int temp = nums[lo];
            while (lo < nums.length) {

                if (hi == nums.length) {
                    hi = 0;
                }

                int t = nums[hi];
                nums[hi] = temp;
                temp = t;

                lo++;
                hi++;
            }
        }
    }

    /**
     * 旋转数组解法
     * @param nums
     * @param k
     */
    public static void rotate1(int[] nums, int k) {

        k = k % nums.length;
        int count = 0;
        for (int i=0; count<nums.length; i++) {
            int current = i;
            int temp = nums[current];
            do {
                int target = (current + k) % nums.length;
                int t = nums[target];
                nums[target] = temp;
                temp = t;
                current = target;
                count++;
            } while (current != i);
        }
    }
}
