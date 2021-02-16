package com.chuck.algorithm.sort;

import com.chuck.algorithm.Sort;

/**
 * 1.普通归并排序
 * 2.分解的小数组使用插入排序，大数组使用归并排序
 * 3.合并前判断是否已经是有序
 * 4.直接将合并的数据放入到辅助数组，避免一次数据迁移消耗
 */
public class MergeSort implements Sort {

  private void merge(Comparable[] arr, int pB, int pE, int rB, int rE) {

    // 如果已经有序，则直接返回
    if (arr[pE].compareTo(arr[rB]) <= 0) {
      return;
    }

    int pLength = pE - pB + 1;
    int rLength = rE - rB + 1;
    Comparable[] temp = new Comparable[pLength + rLength];
    int lo = pB;
    int hi = rB;
    int i = 0;
    int pEnd = pE + 1;
    int rEnd = rE + 1;
    while(lo < pEnd && hi < rEnd) {
      Comparable v = arr[lo].compareTo(arr[hi]) <= 0 ? arr[lo++] : arr[hi++];
      temp[i++] = v;
    }

    if (lo < pEnd) {
      System.arraycopy(arr, lo, temp, i, pEnd - lo);
    }

    if (hi < rEnd) {
      System.arraycopy(arr, hi, temp, i, rEnd - hi);
    }

    System.arraycopy(temp, 0, arr, pB, temp.length);
  }

  private void insertSort(Comparable[] arr, int p, int r) {

    for (int i=p; i<=r; i++) {
      int j = i - 1;
      while (j >= p) {
        if (arr[j].compareTo(arr[j + 1]) > 0) {
          Comparable temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
        j--;
      }
    }
  }

  private void sort(Comparable[] arr, int p, int r) {

//    if (p >= r) {
//      return;
//    }
    if ((r - p) <= 4) {
      insertSort(arr, p, r);
      return;
    }

    int q = (p + r) / 2;
    sort(arr, p, q);
    sort(arr, q+1, r);

    merge(arr, p, q, q+1, r);
  }

  public Comparable[] sort(Comparable[] arr) {

    if (arr == null) {
      return null;
    }

    sort(arr, 0, arr.length - 1);
    return arr;
  }
}
