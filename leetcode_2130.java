/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/*
URL = https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/
2130. Maximum Twin Sum of a Linked List

Complexity
Let N := len(SLL)
Time = O(N)
Space = O(1) ( EXP & IMP )
*/

class Solution {
    public int pairSum(ListNode head) {
        int maxPairSum = 0;
        if(head == null)
            return 0;
        int sllLen = getLen(head);
        ListNode headTwoPrev = head;
        for(int i = 0; i < ( sllLen / 2 ) - 1; ++i ){
            headTwoPrev = headTwoPrev.next;
        }
        ListNode headTwo = headTwoPrev.next;
        headTwoPrev.next = null;
        ListNode revHead = reverse(head);
        maxPairSum = getMaxPairSum(revHead,headTwo);
        return maxPairSum;
    }
    
    // Method assumes both SLLs are of the same length too
    private int getMaxPairSum(ListNode headOne, ListNode headTwo){
        int maxPairSum = 0;
        while(headOne != null && headTwo != null){
            // System.out.printf("%d,%d\n", headOne.val, headTwo.val);
            maxPairSum = Math.max(maxPairSum, headOne.val + headTwo.val);
            headOne = headOne.next;
            headTwo = headTwo.next;
        }
        return maxPairSum;
    }
    
    private int getLen(ListNode head){
        int len = 0;
        while(head != null){
            len += 1;
            head = head.next;
        }
        return len;
    }
    
    // Typically return the head of the reversed SLL
    private ListNode reverse(ListNode head){
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = head.next;
        while(next != null){
            // System.out.printf("cur = %d\n", cur.val);
            cur.next = prev;
            prev = cur;
            cur = next;
            next = cur.next;
        }
        cur.next = prev;
        ListNode temp = cur;
        return cur;
    }
    
    
}
