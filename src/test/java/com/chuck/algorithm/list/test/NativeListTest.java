package com.chuck.algorithm.list.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class NativeListTest {

  private int elementCount = 100000;
  private int loopCount = 100000;

  @Test
  void listTest() {

    List<Integer> testList = new ArrayList<>();
    initList(testList);
    testFind(testList, "ArrayList");
    testInsert(testList, "ArrayList");

    testList = new LinkedList<>();
    initList(testList);
    testFind(testList, "LinkedList");
    testInsert(testList, "LinkedList");

    arrayListGet(elementCount, loopCount);
    arrayListAdd(elementCount, loopCount);
    linkedListGet(elementCount, loopCount);
    linkedListAdd(elementCount, loopCount);
  }

  private void testFind(List<Integer> list, String name) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < loopCount; i++) {
      list.get(ThreadLocalRandom.current().nextInt(elementCount));
    }
    long end = System.currentTimeMillis();
    System.out.println(name + ", testFind times: " + (end - start) + "ms");
  }

  private void testInsert(List<Integer> list, String name) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < loopCount; i++) {
      list.add(ThreadLocalRandom.current().nextInt(elementCount), 1);
    }
    long end = System.currentTimeMillis();
    System.out.println(name + ", testInsert times: " + (end - start) + "ms");
  }

  private void initList(List<Integer> initList) {

    for (int i = 0; i < elementCount; i++) {
      initList.add(i);
    }
  }

  // LinkedList访问
  private static void linkedListGet(int elementCount, int loopCount) {
    List list = IntStream.rangeClosed(1, elementCount).boxed()
        .collect(Collectors.toCollection(LinkedList::new));
    long start = System.currentTimeMillis();
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    long end = System.currentTimeMillis();
    System.out.println("linkedListGet: " + (end - start) + "ms");
  }

  // ArrayList访问
  private static void arrayListGet(int elementCount, int loopCount) {
    List list = IntStream.rangeClosed(1, elementCount).boxed()
        .collect(Collectors.toCollection(ArrayList::new));
    long start = System.currentTimeMillis();
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    long end = System.currentTimeMillis();
    System.out.println("arrayListGet: " + (end - start) + "ms");
  }

  // LinkedList插入
  private static void linkedListAdd(int elementCount, int loopCount) {
    List list = IntStream.rangeClosed(1, elementCount).boxed()
        .collect(Collectors.toCollection(LinkedList::new));
    long start = System.currentTimeMillis();
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    long end = System.currentTimeMillis();
    System.out.println("linkedListAdd: " + (end - start) + "ms");
  }

  // ArrayList插入
  private static void arrayListAdd(int elementCount, int loopCount) {
    List list = IntStream.rangeClosed(1, elementCount).boxed()
        .collect(Collectors.toCollection(ArrayList::new));
    long start = System.currentTimeMillis();
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    long end = System.currentTimeMillis();
    System.out.println("arrayListAdd: " + (end - start) + "ms");
  }
}
