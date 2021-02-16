package com.chuck.leetcode.reverse_linked_list_206;

/**
 * 1.遍历每个节点，翻转指针，时间复杂度O(N)
 */
public class Solution {

    public static ListNode reverseList(ListNode head) {

        ListNode lo = null;
        ListNode hi = head;

        while (hi != null) {
            ListNode next = hi.next;
            hi.next = lo;
            lo = hi;
            hi = next;
        }

        return lo;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reverseList(head);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
