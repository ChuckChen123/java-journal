package com.chuck.leetcode.valid_parentheses_20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 1.使用栈，入栈出栈，最后判断栈是否为空，时间复杂度为O(N)
 */
public class Solution {

  private static final Map<Character, Character> map = new HashMap<>();

  static {
//    map.put(')', '(');
//    map.put(']', '[');
//    map.put('}', '{');
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');
  }

  public static void main(String[] args) {
    System.out.println(test("()"));
  }

  public static boolean test(String s) {

    char[] sarr = s.toCharArray();
    Stack<Character> stack = new Stack<>();

    for (char c : sarr) {
      if (stack.isEmpty() || !map.containsKey(c) || !stack.peek().equals(map.get(c))) {
        stack.push(c);
        continue;
      }

      stack.pop();
    }

    return stack.isEmpty() ? true : false;
  }

  public static boolean resolve1(String s) {

    Stack<Character> stack = new Stack<>();
    char[] sA = s.toCharArray();
    for (char c : sA) {
      if (stack.isEmpty()) {
        stack.push(c);
        continue;
      }

      Character t = stack.peek();
      if (map.get(t) != null && map.get(t).equals(c)) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    if (stack.isEmpty()) {
      return true;
    }

    return false;
  }
}
