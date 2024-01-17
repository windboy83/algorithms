package com.charles.solutions.leetcode.solutions;

import com.charles.solutions.leetcode.common.ListNode;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node = new ListNode(0);
        ListNode result = node;
        boolean isOverTen = false;
        while (l1 != null || l2 != null) {
            int sum = isOverTen ? 1:0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            node.next = new ListNode(sum % 10);
            isOverTen = sum > 9;

            node = node.next;
        }
        if (isOverTen) {
            node.next = new ListNode(1);
        }
        return result.next;
    }
}
