package com.chuck.leetcode.reverse_nodes_in_k_group_25;

/**
 * 1.递归解法，时间复杂度O(N)
 */
public class Solution {

    /**
     * 递归解法
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // 先判断链表是否还剩下k个节点，如果没有k个节点则直接返回head
        ListNode t = head;
        int kt = k;
        while (kt != 0) {
            if (t == null) {
                return head;
            }

            t = t.next;
            kt--;
        }

        // 以翻转节点数量作为判断条件，每翻转一个指针，数量加1，直到为k
        int c = 1;
        ListNode b = head;
        ListNode e = head.next;
        while (c != k) {
            ListNode temp = e.next;
            e.next = b;
            b = e;
            e = temp;
            c++;
        }

        // 翻转完成的子链表的后继节点为下一个翻转子链表的头节点
        head.next = reverseKGroup(e, k);

        // 返回翻转后的头节点
        return b;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
