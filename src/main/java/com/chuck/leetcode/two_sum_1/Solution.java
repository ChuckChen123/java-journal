package com.chuck.leetcode.two_sum_1;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.双重循环，暴力求解，时间复杂度O(N ^ 2)
 * 2.hash表，时间复杂度O(N)
 */
public class Solution {

  /**
   * 双重循环，暴力求解
   * @param nums
   * @param target
   * @return
   */
  public int[] resolve1(int[] nums, int target) {

    int[] result = new int[2];
    for (int i=0; i<nums.length-1; i++) {
      for (int j=i+1; j<nums.length; j++) {
        if ((nums[i] + nums[j]) == target) {
          result[0] = i;
          result[1] = j;
          return result;
        }
      }
    }

    return result;
  }

  /**
   * hash表求解
   * @param nums
   * @param target
   * @return
   */
  public int[] resolve2(int[] nums, int target) {

    int[] result = new int[2];
    Map<Integer, Integer> numsMap = new HashMap<>();
    for (int i=0; i<nums.length; i++) {
      int o = target - nums[i];
      if (numsMap.containsKey(o)) {
        result[0] = numsMap.get(o);
        result[1] = i;
        return result;
      }
      numsMap.put(nums[i], i);
    }

    return result;
  }
}
