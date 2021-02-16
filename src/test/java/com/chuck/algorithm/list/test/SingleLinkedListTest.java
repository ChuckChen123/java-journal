package com.chuck.algorithm.list.test;

import org.junit.jupiter.api.Test;

import com.chuck.algorithm.list.SingleLinkedList;

/**
 * 单链表测试
 * @author chuck
 *
 */
class SingleLinkedListTest {

	@Test
	void testSingleLinkedList() {
		SingleLinkedList<String> singleLinkedList = new SingleLinkedList<String>();
		singleLinkedList.addFirst("a");
		singleLinkedList.addFirst("b");
		singleLinkedList.addFirst("c");
		singleLinkedList.addFirst("d");
		
		System.out.println(singleLinkedList.size());
		System.out.println(singleLinkedList.toString());
	}
}
