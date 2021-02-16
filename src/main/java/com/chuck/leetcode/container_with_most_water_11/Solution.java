package com.chuck.leetcode.container_with_most_water_11;

/**
 * 1.暴力求解，双重循环，时间复杂度O(N ^ 2)
 * 2.双指针，夹逼判断，时间复杂度O(N)（从最两端向中间靠拢，高度较低的指针移动，直到指针相遇）
 */
public class Solution {

    /**
     * 暴力求解，双重循环
     *
     * @param height
     * @return
     */
    public int resolve1(int[] height) {

        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int newArea = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, newArea);
            }
        }

        return max;
    }

    /**
     * 双指针，夹逼判断
     *
     * @param height
     * @return
     */
    public int resolve2(int[] height) {

        int max = 0;
        int lo = 0;
        int hi = height.length - 1;

        while (lo < hi) {
            int width = hi - lo;
            int minHeight = (height[lo] < height[hi]) ? height[lo++] : height[hi--];
            max = Math.max(max, width * minHeight);
        }

        return max;
    }
}
