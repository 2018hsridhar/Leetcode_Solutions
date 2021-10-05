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

Can not modify the values in the list nodes
Only the nodes ( that is, their pointers to other nodes ) can be changed here

URL = https://leetcode.com/problems/reorder-list/
143. Reorder List

Complexity : 
Let N := number of nodes
Time = O(N)
Space = O(1)

Algorithm is an in-place modification
Handle null or singleton SLLs too

Leverage parity to thy advantage as well

*/

class Solution {
    public void reorderList(ListNode head) 
    {
        if(head == null || head.next == null)
            return;
        
        
    }
}
