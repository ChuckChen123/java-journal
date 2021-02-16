package com.chuck.algorithm.sort;

import com.chuck.algorithm.Sort;

/**
 * 选择排序 1.找到剩余数组中的最小/大的元素 2.进行元素交换
 *
 * @author chuck
 */
public class SelectSort implements Sort {

  /**
   * 选择排序
   *
   * @param arr
   * @return
   * @throws Exception
   */
  public Comparable[] sort(Comparable[] arr) {

    if (arr == null) {
      return null;
    }

    for (int i = 0; i < arr.length; i++) {
      int x = findSmallest(arr, i);
      swap(arr, i, x);
    }

    return arr;
  }

  /**
   * 进行元素交换
   *
   * @param arr
   * @param from
   * @param to
   * @throws Exception
   */
  public void swap(Comparable[] arr, int from, int to) {

    Comparable temp = arr[from];
    arr[from] = arr[to];
    arr[to] = temp;
  }

  /**
   * 获取从start开始到数组结尾的最小元素
   *
   * @param arr
   * @param start
   * @return
   * @throws Exception
   */
  public int findSmallest(Comparable[] arr, int start) {

    int index = start;
    for (int i = start; i < arr.length; i++) {

      Comparable tCurrent = arr[i];
      if (tCurrent.compareTo(arr[index]) < 0) {
        index = i;
      }
    }
    return index;
  }
}
