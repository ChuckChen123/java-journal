package com.chuck.algorithm.list;

/**
 * 单链表
 * @author chuck
 *
 */
public class SingleLinkedList<T> {

	private static class Node<T> {
		public T data;
		public Node<T> next;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	/**
	 * 哨兵节点
	 */
	private Node<T> sentinel;
	
	/**
	 * 链表长度
	 */
	private int size;
	
	public SingleLinkedList() {
		this.sentinel = new Node<>(null);
		this.size = 0;
	}
	
	public SingleLinkedList(T data) {
		this.sentinel = new Node<>(null);
		this.size = 1;
		
		Node<T> next = new Node<>(data);
		this.sentinel.next = next;
	}
	
	/**
	 * 从头部插入
	 */
	public void addFirst(T data) {
		Node<T> first = new Node<>(data);
		first.next = sentinel.next;
		sentinel.next = first;
		size++;
	}
	
	/**
	 * 获取链表长度
	 * @return 链表长度
	 */
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		Node<T> start = this.sentinel;
		while(start.next != null) {
			sBuilder.append(start.next.data);
			sBuilder.append(", ");
			start = start.next;
		}
		
		return sBuilder.toString();
	}
}
