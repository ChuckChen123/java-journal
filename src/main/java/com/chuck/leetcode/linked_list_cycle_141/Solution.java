package com.chuck.leetcode.linked_list_cycle_141;

/**
 * 1.双指针解法，lo指针一次前进一格，hi指针一次前进两格， 如果相遇了，则有环，时间复杂度O(N)
 */
public class Solution {

    public boolean resolve1(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }

        ListNode lo = head.next;
        ListNode hi = head.next.next;

        while (true) {

            if (lo == hi) {
                return true;
            }

            if (lo.next == null || hi.next == null || hi.next.next == null) {
                return false;
            }

            lo = lo.next;
            hi = hi.next.next;
        }
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
