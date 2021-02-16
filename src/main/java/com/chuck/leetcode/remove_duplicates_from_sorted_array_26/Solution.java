package com.chuck.leetcode.remove_duplicates_from_sorted_array_26;

/**
 * 1.双指针法，不需要保证数据完整性，只用去重即可，时间复杂度O(N)
 */
public class Solution {

    /**
     * 双指针法
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int lo = 0;
        int hi = 1;

        // lo指针在原地等待hi指针找到与lo指针不同的数之后
        // 将lo指针后一个元素赋值为hi指针对应的值
        // 直到hi指针到达数组末尾
        while (hi < nums.length) {

            if (nums[lo] == nums[hi]) {
                hi++;
                continue;
            }

            // 避免hi指针原地赋值
            if (lo + 1 != hi) {
                nums[lo + 1] = nums[hi];
            }

            lo++;
            hi++;
        }

        return lo + 1;
    }
}
