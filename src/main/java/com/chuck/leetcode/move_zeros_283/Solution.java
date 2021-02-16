package com.chuck.leetcode.move_zeros_283;

/**
 * 1.双指针法，swap，时间复杂度O(N)
 */
public class Solution {

  /**
   * 双指针法，swap
   * lo寻找0，hi寻找非0，然后swap
   * @param nums
   */
  public void resolve1(int[] nums) {

    for (int lo=0, hi=1; hi<nums.length; hi++) {

      // 当满足lo为0，hi为非0时，进行交换
      if ((nums[lo] == 0) && (nums[hi] != 0)) {
        swap(nums, lo++, hi);
      } else if (nums[lo] != 0) {
        // lo找到0时留在原地，等待hi找到非0
        lo++;
      }
    }
  }

  private void swap(int[] arr, int x, int y) {
    int temp = arr[x];
    arr[x] = arr[y];
    arr[y] = temp;
  }
}
