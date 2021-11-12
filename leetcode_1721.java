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

1721. Swapping Nodes in a Linked List
URL = https://leetcode.com/problems/swapping-nodes-in-a-linked-list/

Strategies : Recursion/Iterative

Integer k is given : can k go over the size as well?
We may need to quickly grab size information as well
Finding 'kth' elem forward is easy : backwards not -> can we leverage call stack?
    We don't want to create a copy of the SLL, reversed here.

COMPLEXITY
Let N := len(list)
Time = O(N)
Space = O(1) ( explicit ) O(N) ( implicit ) 

TEST CASES : 
(A)
(B)
(C)
(D)
(E)

*/

class Solution 
{
    public ListNode swapNodes(ListNode head, int k) 
    {
        
    }
}
