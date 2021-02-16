package com.chuck.algorithm.list.test;

import com.chuck.algorithm.Sort;
import com.chuck.algorithm.sort.MergeSort;
import com.chuck.algorithm.sort.SelectSort;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class SortPressureTest {

  private static final int l = 1000 * 10000;
  private static final Random random = new Random();
  private static final Comparable[] arr;

  static {
    arr = new Comparable[l];
    for (int i=0; i<l; i++) {
//      arr[i] = random.nextInt(l + 1);
      arr[i] = i;
    }
  }

  @Test
  void testMergeSortPressure() {
    Sort mergeSort = new MergeSort();
    doTest(mergeSort, "Merge Sort");
  }

  @Test
  void testSelectSortPressure() {
    Sort selectSort = new SelectSort();
    doTest(selectSort, "Select Sort");
  }

  private void doTest(Sort sort, String name) {

    long begin = System.currentTimeMillis();
    sort.sort(arr);
    long end = System.currentTimeMillis();
    System.out.println(name + " cost time: " + (end - begin) + "ms");
  }
}
