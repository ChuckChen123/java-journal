package com.chuck.leetcode.reverse_vowels_of_a_string_345;

import java.util.HashSet;
import java.util.Set;

/**
 * 双指针解法，时间复杂度O(N)
 */
public class Solution {

  private static final Set<Character> set = new HashSet<>();

  static {
    set.add('a');
    set.add('e');
    set.add('i');
    set.add('o');
    set.add('u');
    set.add('A');
    set.add('E');
    set.add('I');
    set.add('O');
    set.add('U');
  }

  public static String resolve1(String s) {

    int lo = 0;
    int hi = s.length() - 1;
    char[] sb = s.toCharArray();

    while (lo < hi) {

      if (set.contains(sb[lo]) && set.contains(sb[hi])){
        char t = sb[lo];
        sb[lo] = sb[hi];
        sb[hi] = t;
        lo++;hi--;
        continue;
      }

      if (!set.contains(sb[lo])) {
        lo++;
      }

      if (!set.contains(sb[hi])) {
        hi--;
      }
    }

    return new String(sb);
  }

  public static void main(String[] args) {
    System.out.println(resolve1("hello"));
  }
}
