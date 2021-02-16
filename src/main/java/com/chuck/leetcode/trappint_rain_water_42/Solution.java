package com.chuck.leetcode.trappint_rain_water_42;

/**
 * 1.双指针，时间复杂度O(N)
 */
public class Solution {

  public int resolve1(int[] height) {

    int minus = 0;
    int s = 0;
    for (int lo=0, hi=1; hi<height.length && lo<height.length; hi++){

      System.out.println(lo + " " + hi + " " + s);
      if (height[lo] == 0) {
        lo++;
        hi = lo;
        minus = 0;
        continue;
      }

      if (height[lo] != 0 && height[hi] >= height[lo]) {
        int w = hi - lo - 1;
        int h = height[lo];

        s += w * h - minus;
        minus = 0;
        lo = hi;
        continue;
      }

      if (hi == (height.length - 1)) {
        lo++;
        hi = lo;
        minus = 0;
        continue;
      }

      minus += height[hi];
    }

    return s;
  }
}
