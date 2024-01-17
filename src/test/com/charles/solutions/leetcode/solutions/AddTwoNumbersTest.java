package com.charles.solutions.leetcode.solutions;

import com.charles.solutions.leetcode.common.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    @Test
    void addTwoNumbers() {
        ListNode l1 = ListNode.of(2,4,3);
        ListNode l2 = ListNode.of(5,6,4);
        ListNode expected = ListNode.of(7,0,8);

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);

        while(expected != null) {
            assertTrue(expected.equals(result));
            expected = expected.next;
            result = result.next;
        }
    }
}