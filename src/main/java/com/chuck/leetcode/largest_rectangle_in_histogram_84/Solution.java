package com.chuck.leetcode.largest_rectangle_in_histogram_84;

import java.util.Stack;

/**
 * 1.暴力解法，按照高度寻找最大面积，时间复杂度O(N ^ 2)
 * 2.使用栈，空间换时间，时间复杂度O(N)
 */
public class Solution {

    /**
     * 暴力解法
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {

        int max = 0;
        // 遍历heights中的每个柱子
        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i];
            int h = i + 1;
            int count = 1;
            // 寻找右边的比当前高度高的柱子数量
            while (h < heights.length && heights[h] >= currentHeight) {
                count++;
                h++;
            }

            h = i - 1;
            // 寻找左边的比当前高度高的柱子数量
            while (h >= 0 && heights[h] >= currentHeight) {
                count++;
                h--;
            }

            max = Math.max(currentHeight * count, max);
        }

        return max;
    }

    /**
     * 使用栈
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea1(int[] heights) {

        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return heights[0];
        }

        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < len; i++) {

            int curr = heights[i];

            if (stack.isEmpty() || heights[stack.peek()] <= curr) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && heights[stack.peek()] > curr) {

                int j = stack.pop();
                int width = 0;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                max = Math.max(max, width * heights[j]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {

            int j = stack.pop();
            int width = 0;
            if (stack.isEmpty()) {
                width = len;
            } else {
                width = len - stack.peek() - 1;
            }

            max = Math.max(max, width * heights[j]);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea1(new int[]{4, 2, 0, 3, 2, 5}));
    }
}
