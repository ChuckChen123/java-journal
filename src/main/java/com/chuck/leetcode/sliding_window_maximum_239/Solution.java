package com.chuck.leetcode.sliding_window_maximum_239;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{7, 2, 4}, 2)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 0) {
            return new int[]{};
        }

        int lo = 0;
        int hi = Math.min(nums.length - 1, k - 1);
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i=lo; i<=hi; i++) {
            if (deque.isEmpty()) {
                deque.addLast(i);
                continue;
            }

            while (deque.peekLast() != null && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.addLast(i);
        }

        result[0] = nums[deque.peekFirst()];
        lo++;
        hi++;
        int start = 1;
        while (hi < nums.length) {

            while (deque.peekLast() != null && nums[hi] > nums[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.addLast(hi);

            while (deque.peekFirst() != null && deque.peekFirst() < lo) {
                deque.pollFirst();
            }

            lo++;
            hi++;
            result[start++] = nums[deque.peekFirst()];
        }

        return result;
    }
}
