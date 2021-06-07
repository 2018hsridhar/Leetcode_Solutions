
/*

THOUGHT PROCESSES : 
1474. Delete N Nodes After M Nodes of a Linked List

https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/
- Can we modify the list in place, or need we copy the list over again?

*/

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
class Solution {
    public ListNode deleteNodes(ListNode head, int m, int n) 
    {
        ListNode result = head;
        ListNode cur = head;
        int i = 1;
        while(cur != null)
        {
            if(i == m)
            {
                ListNode newHead = cur.next;
                for(int x = 0; x < n; ++x)
                {
                    if(newHead == null)
                        break;
                   newHead = newHead.next;
                 }
                cur.next = newHead;
                i= 0; // reset again here
            }
            cur = cur.next;
            ++i;
        }
        return result; // we start at the head anyways
    }
}
