package com.chuck.leetcode.min_stack_155;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  private int defaultCap = 10;
  private int cap = defaultCap;
  private int size = 0;
  private List<Integer> minList;
  private int[] arr;

  private void grow() {

    if (size == cap) {
      cap = cap << 1;
      int[] temp = new int[cap];
      System.arraycopy(arr, 0, temp, 0, size);
      arr = temp;
    }
  }

  public Solution() {
    this.arr = new int[defaultCap];
    this.minList = new ArrayList<>();
  }

  public void push(int x) {

    grow();

    if (minList.isEmpty() || x < arr[minList.get(minList.size() - 1)]) {
      minList.add(size);
    }

    arr[size] = x;
    size++;
  }

  public void pop() {

    int i = size - 1;
    int r = arr[i];
    size--;

    if (i == minList.get(minList.size() - 1)) {
      minList.remove(minList.size() - 1);
    }
  }

  public int top() {

    return arr[size - 1];
  }

  public int getMin() {

    return arr[minList.get(minList.size() - 1)];
  }
}
