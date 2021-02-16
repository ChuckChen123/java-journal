package com.chuck.leetcode.longest_word_in_dictionary_through_deleting_524;

import java.util.Arrays;
import java.util.List;

/**
 * 1.使用双指针解法，遍历字典，然后寻找每个词在字符串中是否存在
 * 时间复杂度O(M * N)
 */
public class Solution {

    public String resolve1(String s, List<String> d) {

        char[] sA = s.toCharArray();
        String result = "";

        for (String dictStr : d) {

            if (result.length() > dictStr.length()) {
                continue;
            }

            if (result.length() == dictStr.length() && result.compareTo(dictStr) < 0) {
                continue;
            }

            char[] dictArr = dictStr.toCharArray();
            int si = 0;
            int di = 0;
            while (si < sA.length && di < dictArr.length) {

                if ((di + 1) == dictArr.length && sA[si] == dictArr[di]) {
                    si++;
                    di++;
                    result = dictStr;
                    continue;
                }

                if (sA[si] == dictArr[di]) {
                    di++;
                }
                si++;
            }
        }
        return result;
    }

    public static String test(String s, List<String> d) {

        char[] sarr = s.toCharArray();
        String result = "";
        for (String dstr : d) {
            char[] darr = dstr.toCharArray();
            if (darr.length > sarr.length) {
                continue;
            }

            int si = 0;
            int di = 0;

            while (di < darr.length && si < sarr.length) {

                if (darr[di] == sarr[si]) {
                    di++;
                }
                si++;
            }

            if (di == darr.length && ("".equals(result)
                    || dstr.length() > result.length()
                    || (dstr.length() == result.length() && dstr.compareTo(result) < 0))) {
                result = dstr;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(test("aaa", Arrays.asList(new String[]{"aaa", "aa", "a"})));
    }
}
