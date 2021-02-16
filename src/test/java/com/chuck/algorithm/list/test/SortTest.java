package com.chuck.algorithm.list.test;

import com.chuck.algorithm.Sort;
import com.chuck.algorithm.sort.MergeSort;
import com.chuck.algorithm.sort.SelectSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortTest {

  @Test
  void testMergeSort() {
    Sort mergeSort = new MergeSort();
    doTest(mergeSort);
  }

  @Test
  void testSelectSort() {
    Sort selectSort = new SelectSort();
    doTest(selectSort);
  }

  private void doTest(Sort sort) {

    //Even number of element
    Comparable[] arr = {12, 15, 23, 4, 6, 10, 35, 28};
    Assertions.assertArrayEquals(new Comparable[]{4, 6, 10, 12, 15, 23, 28, 35}, sort.sort(arr),
        "Even number of element sort failed");

    //Empty list
    arr = new Comparable[]{};
    Assertions.assertArrayEquals(new Comparable[]{}, sort.sort(arr), "Empty list sort failed");

    //Null pointer
    arr = null;
    Assertions.assertArrayEquals(null, sort.sort(arr), "Null pointer sort failed");

    //already sorted array
    arr = new Comparable[]{4, 6, 10, 12, 15, 23, 28, 35};
    Assertions.assertArrayEquals(new Comparable[]{4, 6, 10, 12, 15, 23, 28, 35}, sort.sort(arr),
        "already sorted array sort failed");

    //odd length array
    arr = new Comparable[]{12, 15, 23, 4, 6, 10, 35};
    Assertions.assertArrayEquals(new Comparable[]{4, 6, 10, 12, 15, 23, 35}, sort.sort(arr),
        "odd length array sort failed");

    //descending sorted array input
    arr = new Comparable[]{35, 28, 23, 15, 12, 10, 6, 4};
    Assertions.assertArrayEquals(new Comparable[]{4, 6, 10, 12, 15, 23, 28, 35}, sort.sort(arr),
        "descending sorted array input sort failed");

    //one element
    arr = new Comparable[]{12};
    Assertions.assertArrayEquals(new Comparable[]{12}, sort.sort(arr), "one element sort failed");

    // two elements
    arr = new Comparable[]{12, 4};
    Assertions
        .assertArrayEquals(new Comparable[]{4, 12}, sort.sort(arr), "two elements sort failed");

    //large list of elements
    arr = new Comparable[]{12, 15, 23, 4, 6, 10, 35, 28, 100, 130, 500, 1000, 235, 554, 75, 345,
        800, 222, 38, 10, 34, 0, 1, 123, 456, 789, 11, 22, 33, 44, 55, 66, 99, 100, 1000, 101,
        333, 888, 444, 111, 666, 777, 60};
    Assertions.assertArrayEquals(
        new Comparable[]{0, 1, 4, 6, 10, 10, 11, 12, 15, 22, 23, 28, 33, 34, 35, 38, 44, 55, 60, 66,
            75, 99, 100, 100, 101, 111, 123, 130, 222, 235, 333, 345, 444, 456, 500, 554, 666, 777,
            789, 800, 888, 1000, 1000}, sort.sort(arr),
        "large list of elements sort failed");

    //negative elements
    arr = new Comparable[]{12, 15, -23, -4, 6, 10, -35, 28};
    Assertions.assertArrayEquals(new Comparable[]{-35, -23, -4, 6, 10, 12, 15, 28}, sort.sort(arr),
        "negative elements sort failed");

    //duplicate elements
    arr = new Comparable[]{12, 12, 23, 4, 6, 6, 10, -35, 28};
    Assertions.assertArrayEquals(new Comparable[]{-35, 4, 6, 6, 10, 12, 12, 23, 28}, sort.sort(arr),
        "duplicate elements sort failed");

    //Same element
    arr = new Comparable[]{12, 12, 12, 12, 12};
    Assertions.assertArrayEquals(new Comparable[]{12, 12, 12, 12, 12}, sort.sort(arr),
        "Same element sort failed");
  }

//  public static void main(String[] args) {
//    Integer[] arr = new Integer[]{12, 15, 23, 4, 6, 10, 35, 28, 100, 130, 500, 1000, 235, 554, 75,
//        345,
//        800, 222, 38, 10, 34, 0, 1, 123, 456, 789, 11, 22, 33, 44, 55, 66, 99, 100, 1000, 101,
//        333, 888, 444, 111, 666, 777, 60};
//    List<Integer> list = new ArrayList(Arrays.asList(arr));
//    Collections.sort(list);
//    for (Integer i : list) {
//      System.out.print(i + ", ");
//    }
//  }
}
