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

147. Insertion Sort List

Sort using the head of the SLL 
Consider a recursive approach ( head OR tail recursive ) 
Is insertion sort : must do pairwise element comparison as well too till either the beginning of the end!
Is an ITERATABLE algorithm 
Iteratively grows the sorted output list as well
    -> I see : we are growing the sorted sublist ( we insert into an already sorted list here ) 
    

Computational Complexity : 
Time = O(N^2)
Space = O(1)

Must be able to handle duplicates as well
And data in the range of [MIN_INT, MAX_INT]
Edge Case Testing : 
(A) [1,2,3,4,5]
(B) [5,4,3,2,1]
(C) [2,2,2]
(D) [1,0,5,3,7,10,8,4]
(E) [2,2,2,2,4,4,5]
(F) 

Pseudocode : 
Might not need a size variable here
Can start at head.next ( head by itself is always valid )

Canot not just return the original head, due to the potential for an insertion sort @ beginning of SLL
Must always keep track of the beginning of this list too
Two pointers - left and right -> keep track of the insertion sorted list as it grows

*/


class Solution 
{
    public ListNode insertionSortList(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        ListNode cur = head.next;
        ListNode left = head;
        ListNode right = head;
        // O(N) try out each node here
        while(cur != null)
        {
            // O(1) end insert
            if(cur.val >= right.val)
            {
                right = cur;
                cur = cur.next;
            }
            // O(1) beginning insert
            else if ( cur.val <= left.val)
            {
                right.next = cur.next;
                cur.next = left;
                left = cur;
                cur = right.next;
            }
            // O(N) middle insertion
            else // Guaranteed that ( left.val < cur.val < right.val )
            {
                ListNode fwd = cur.next;
                right.next = fwd;
                
                // insert node into right place ( problem reduction here ) 
                // Highly akin to a strictly increasing monotonic sequence here!
                ListNode start = left;
                while(cur.val > start.val)
                {
                    if(cur.val > start.next.val)
                        start = start.next;
                    else
                    {
                        cur.next = start.next;
                        start.next = cur;
                        break;
                    }
                }
                
                // at the end, reset the cur val
                // but not the right as that did not really grow here
                // Also not the left ptr which changes here as well!
                cur = fwd;
            }
        }
        return left;
    }
}
