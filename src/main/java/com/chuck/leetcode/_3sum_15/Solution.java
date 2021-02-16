package com.chuck.leetcode._3sum_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1.三指针算法，时间复杂度O(N ^ 2)
 */
public class Solution {

    /**
     * 三指针算法，时间复杂度O(N ^ 2)
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> resolve1(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if (nums.length == 0) {
            return new ArrayList<>();
        }
        // 先对数组进行排序
        Arrays.sort(nums);
        // 最外层for循环来确定第一个数
        if (nums[0] <= 0 && nums[nums.length - 1] >= 0) {

            for (int i = 0; i < nums.length - 2; i++) {

                if (nums[i] > 0) {
                    return result;
                }
                // 过滤相同的元组
                if (i > 0 && nums[i - 1] == nums[i]) {
                    continue;
                }
                // 内层使用双指针来进行查找第二和第三个数
                int lo = i + 1;
                int hi = nums.length - 1;
                int target = 0 - nums[i];

                while (lo < hi) {

                    int lov = nums[lo];
                    int hiv = nums[hi];

                    if (lo != (i + 1) && nums[lo] == nums[lo - 1]) {
                        lo++;
                        continue;
                    }

                    if (lov + hiv < target) {
                        lo++;
                        continue;
                    }
                    if (lov + hiv > target) {
                        hi--;
                        continue;
                    }

                    result.add(Arrays.asList(new Integer[]{nums[i], lov, hiv}));
                    lo++;
                    hi--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(resolve1(new int[]{-2, 0, 1, 1, 2}));
    }
}
