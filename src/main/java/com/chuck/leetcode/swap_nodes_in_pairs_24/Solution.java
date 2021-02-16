package com.chuck.leetcode.swap_nodes_in_pairs_24;

/**
 * 1.迭代解法，时间复杂度O(N)
 * 2.递归解法，时间复杂度O(N)
 */
public class Solution {

    public ListNode swapPairs1(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode second = head.next;
        head.next = swapPairs1(second.next);
        second.next = head;

        return second;
    }

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = head.next;
        ListNode first = head;
        ListNode second = null;

        while (first != null) {
            if (first.next == null) {
                break;
            }

            ListNode tempSecond = first.next;
            ListNode temp = tempSecond.next;

            if (second != null) {
                second.next = tempSecond;
            }
            tempSecond.next = first;
            first.next = temp;
            second = first;
            first = temp;
        }

        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
