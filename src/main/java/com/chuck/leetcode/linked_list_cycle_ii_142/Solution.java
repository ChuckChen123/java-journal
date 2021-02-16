package com.chuck.leetcode.linked_list_cycle_ii_142;

import java.util.HashSet;
import java.util.Set;

/**
 * 1.使用哈希表，记录第一个重复的元素，环的第一个元素，时间复杂度O(N)
 * 2.双指针解法
 */
public class Solution {

    /**
     * 哈希表解法
     * @return
     */
    public ListNode detectCycle(ListNode head) {

        Set<ListNode> set = new HashSet<>();
        while (head != null) {

            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
            }

            head = head.next;
        }

        return null;
    }

    /**
     * 双指针解法，先判断链表中是否有环，然后使用数学方法找到起点
     * @param head
     * @return
     */
    public static ListNode detectCycle1(ListNode head) {

        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {

            if (slow == fast) {

                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return null;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        ListNode tail = new ListNode(-4);
        head.next.next.next = tail;
        tail.next = head.next;
        detectCycle1(head);
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
