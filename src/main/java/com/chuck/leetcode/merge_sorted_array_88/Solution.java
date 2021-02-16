package com.chuck.leetcode.merge_sorted_array_88;

/**
 * 1.双指针，两个指针分别从大到小遍历两个数组
 * 比较大小后放入新的数组，时间复杂度O(M + N)
 */
public class Solution {

  public void resolve1(int[] nums1, int m, int [] nums2, int n) {

    int mi = m - 1;
    int ni = n - 1;
    int i = m + n - 1;

    while (mi >= 0 && ni >= 0) {

      if (nums1[mi] >= nums2[ni]) {
        nums1[i--] = nums1[mi--];
        continue;
      }

      if (nums1[mi] < nums2[ni]) {
        nums1[i--] = nums2[ni--];
        continue;
      }
    }

    if (ni >= 0) {
      System.arraycopy(nums2, 0, nums1, 0, ni + 1);
    }
  }
}
