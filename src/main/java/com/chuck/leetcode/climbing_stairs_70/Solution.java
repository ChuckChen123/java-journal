package com.chuck.leetcode.climbing_stairs_70;

/**
 * 1.分解子问题：
 * 思路：当有一级或者两级台阶时，分别只有一种和两种方法可以到达，
 * 而当有三级台阶时，就可以先到达一级或者二级台阶再到三级台阶，
 * 也就是三级的方法数 = 到达一级的方法数 + 到达二级的方法数，
 * 后续更高级以此类推
 * 算法设计：
 * 1.递归形式，但是时间复杂度为O(2 ^ N)
 * 2.动态规划，时间复杂度不清除
 * 3.迭代，时间复杂度为O(N)
 */
public class Solution {

    /**
     * 使用迭代的方法解决
     *
     * @param n
     * @return
     */
    public int resolve1(int n) {
        int n1 = 1, n2 = 2;
        if (n == 1) {
            return n1;
        }
        if (n == 2) {
            return n2;
        }

        for (int i = 3; i <= n; i++) {
            int newN = n1 + n2;
            n1 = n2;
            n2 = newN;
        }

        return n2;
    }

    /**
     * 使用递归解决
     * TODO 超时！不合格！
     *
     * @param n
     * @return
     */
    public int resolve2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return resolve2(n - 1) + resolve2(n - 2);
    }

    /**
     * 使用动态规划
     * TODO 待完善，暂未学习
     *
     * @param n
     * @return
     */
    public int resolve3(int n) {
        return 0;
    }
}
