package com.chuck.leetcode.merge_two_sorted_lists_21;

/**
 * 1.双指针实现，时间复杂度O(N)
 * 2.递归解决
 */
public class Solution {

    /**
     * 双指针实现
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode start = null;
        ListNode onen = l1;
        ListNode twon = l2;
        if (l1.val <= l2.val) {
            start = l1;
            onen = onen.next;
        } else {
            start = l2;
            twon = twon.next;
        }

        ListNode b = start;
        while (onen != null && twon != null) {

            if (onen.val <= twon.val) {
                b.next = onen;
                onen = onen.next;
            } else {
                b.next = twon;
                twon = twon.next;
            }

            b = b.next;
        }

        if (onen == null) {
            b.next = twon;
        } else {
            b.next = onen;
        }
        return start;
    }

    /**
     * 递归解决
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode current = null;
        ListNode l1next = l1;
        ListNode l2next = l2;
        if (l1 == null || (l2 != null && l2.val < l1.val)) { current = l2; }
        else if (l2 == null || (l1 != null && l1.val <= l2.val)) { current = l1; }

        if (l1 != null && current == l1) {
            l1next = l1.next;
        }

        if (l2 != null && current == l2) {
            l2next = l2.next;
        }

        current.next = mergeTwoLists(l1next, l2next);
        return current;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
