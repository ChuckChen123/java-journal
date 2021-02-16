package com.chuck.leetcode.two_sum_ii_input_array_is_sorted_167;

/**
 * 1.双指针，两边夹逼，时间复杂度O(N)
 * 2.先确定一个数，另一个数使用二分查找，时间复杂度O(NlogN)
 */
public class Solution {

  /**
   * 双指针，两边夹逼，时间复杂度O(N)
   * @param numbers
   * @param target
   * @return
   */
  public int[] resolve1(int[] numbers, int target) {

    int lo = 0;
    int hi = numbers.length - 1;

    while (lo < hi) {
      int r = numbers[hi] + numbers[lo];

      if (r > target) {
        hi--;
        continue;
      }

      if (r < target) {
        lo++;
        continue;
      }

      return new int[]{lo + 1, hi + 1};
    }

    return new int[]{};
  }

  /**
   * 先确定一个数，另一个数使用二分查找，时间复杂度O(NlogN)
   * @param numbers
   * @param target
   * @return
   */
  public int[] resolve2(int[] numbers, int target) {

    for (int i=0; i<numbers.length; i++) {

      int j = binarySearch(numbers, i + 1, numbers.length - 1, target - numbers[i]);
      if (j != -1) {
        return new int[]{i + 1, j + 1};
      }
    }

    return new int[]{};
  }

  private int binarySearch(int[] arr, int lo, int hi, int target) {

    if (lo > hi) {
      return -1;
    }

    int mid = lo + (hi - lo) / 2;
    if (arr[mid] > target) {
      return binarySearch(arr, lo, mid - 1, target);
    }
    if (arr[mid] < target) {
      return binarySearch(arr, mid + 1, hi, target);
    }

    return mid;
  }
}
