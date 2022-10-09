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

725. Split Linked List in Parts
URL = https://leetcode.com/problems/split-linked-list-in-parts/

Complexity
Let N := len(SLL)
Time = O(N)
Space = O(N) ( in worst case -> explicit ) O(1) ( call stack )
Even in dividend case = 0, we can still just add based on the remainder.


Edge Cases
(A) [1,2,3] k = 5
    PASS
(B) [1,2,3,4,5] k = 5
    PASS
(C) [1,2,3,4,5] k = 3
    PASS
(D) [1,2,3,4,5] k = 1
    PASS
(E) [1,2,3,4,5,6,7,8,9] k = 3
    PASS
(F) 

*/

class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] subLists = new ListNode[k];
        int listLen = getLen(head);
        int quotient = listLen / k;
        int rem = listLen % k;
        // guaranteed <k> parts here too
        // Start from head node
        ListNode newHead = head;
        ListNode newTail = newHead;
        for(int a = 0; a < k; ++a){
            int subLen = quotient;
            if(rem > 0){
                subLen++;
            }
            for(int b = 0; b < subLen-1; b++) // if subLen = 1 , render a NOOP
            {
                newTail = newTail.next;
            }
            if(newTail != null){
                ListNode nextHead = newTail.next;
                newTail.next = null;
                subLists[a] = newHead;
                newHead = nextHead;
                newTail = newHead;
                rem--;
            } else {
                subLists[a] = null;
            }
        }
        return subLists;
    }
    
    private int getLen(ListNode head)
    {
        int listLen = 0;
        while(head != null){
            listLen++;
            head = head.next;
        }
        return listLen;
    }
    
}
