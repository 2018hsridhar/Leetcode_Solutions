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

x Need NOT be in the SLL as well
N is reasonably bounded in [0,200]
Node vals also reasonably boudned [ -100,100 ]
Yes 'X' can eb outside the range
Yes SLL can have duplicate values as well

86. Partition List
URL = https://leetcode.com/problems/partition-list/

Complexity
Let N := len(list)
Time = O(N) + O(N) = O(N)
Space = O(1)

Edge Case Testing
(A) [3,1,2] 4
    [3,1,2]
(B) [2,6,4 5] x = 5
    [2,5,6,5]
(C) [] or null ( x = any value ) 
    []
(D) [1]  x = any value ( never matters here ) 
    [1] 
(E) [1,2] x = 2
    [1,2]
(F) [3,1] x = 2
    [1,3]
    
Goal : aim from explicit -> implicit solution : yes it is O(1) still, BUT, ... we have to allocate new nodes as a result


*/

class Solution {
    // in-place modification based solution ( but still allocates one SLL elsewhere ) 
    public ListNode partition(ListNode head, int x) 
    {
        if(head == null || head.next == null)
            return head;
        
        ListNode sentinel = new ListNode(Integer.MAX_VALUE);
        sentinel.next = head;
        ListNode cur = sentinel;
        ListNode fwd = sentinel.next;   // start of the list here
        ListNode lessX = new ListNode(Integer.MIN_VALUE);
        ListNode lessX_tail = lessX;
        while(fwd != null)
        {
            // System.out.printf("cur = %d \t fwd = %d\n", cur.val, fwd.val);
            if(fwd.val < x)
            {
                // Append to lessThanX list
                ListNode temp = fwd.next;
                fwd.next = null;
                lessX_tail.next = fwd;
                lessX_tail = lessX_tail.next;
                // Remodify greaterThanX list
                
                fwd = temp;
                cur.next = fwd;
            }
            else
            {
                cur = fwd;
                fwd = cur.next;
            }
        }
        
        lessX_tail.next = sentinel.next;
        return lessX.next;
    }
        
        
    // NOT an in-place modification ( allocates two distinct SLLs ) 
    public ListNode explicit(ListNode head, int x) 
    {
        if(head == null || head.next == null)
            return head;
        ListNode cur = head;
        ListNode lessX = new ListNode();        // can denote as the sentinels here
        ListNode x_cur = lessX;
        ListNode lessY = new ListNode();
        ListNode y_cur = lessY;
        while(cur != null)
        {
            if(cur.val < x)
            {
                x_cur.next = new ListNode(cur.val);
                x_cur = x_cur.next;
            }
            else
            {
                y_cur.next = new ListNode(cur.val);
                y_cur = y_cur.next;                
            }
            cur = cur.next;
        }

        // check singleton cases as well too!
        if(lessX.next == null)
            return lessY.next;
        else if ( lessY.next == null)
            return lessX.next;
        ListNode res = lessX.next;
        x_cur.next = lessY.next;
        return res;
    }
}
