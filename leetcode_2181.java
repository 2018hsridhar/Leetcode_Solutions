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
URL = https://leetcode.com/problems/merge-nodes-in-between-zeros/
2181. Merge Nodes in Between Zeros
Can we do modification in place too?

Strategies
2 pointers sliding window.

Complexity
Let N := len(SLL)
TIME = O(N) + O(N) = O(N)
SPACE = O(1) ( EXP & IMP ). 

Edge Cases
(A) [0,3,1,0,4,5,2,0] => 4,11
(B) [0,3,1,0] => 4
(C) [0,3,0] => 3
(D) [0,1,2,3,4,5,6,0] => 15

*/

class Solution {
    public ListNode mergeNodes(ListNode head) {
        ListNode lPtr = head;
        ListNode rPtr = head.next;
        while(rPtr != null){
            if(rPtr.val == 0){
                int curSum = 0;
                ListNode origLPtr = lPtr;
                while(lPtr != rPtr){
                    curSum += lPtr.val;
                    lPtr = lPtr.next;
                }
                origLPtr.val = curSum;
                if(rPtr.next == null){
                    origLPtr.next = null; // hit final 0
                    return head;
                } else {
                    // Restart our sliding window.
                    origLPtr.next = rPtr;
                    lPtr = rPtr;
                }
            } 
            rPtr = rPtr.next;
        }
        return head;
    }
}
