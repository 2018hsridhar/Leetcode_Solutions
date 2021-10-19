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

URL = https://leetcode.com/problems/plus-one-linked-list/
369. Plus One Linked List

We know that the input is guaranteed to be a non-negative integer represented as a SLL
Most significant digit / MSB is stored at the "head" of this list as well
Return the list after adding 1

We know the only way to increase the # of digits is when we are at (10e^n-1), for n \in N ( e.g. 9, 99, 999 )
We need to account for a constant carry over operation as well ( from LSB to MSB )
Definitely seems tail recursive in nature as well
Consider allocate a sentinel node to the head : we know # of output nodes to return as well
No leading zeroes in the input as well

Complexity 
Let N := # of digits in the input
Time = O(N)
Space = (Implicit) O(N) (Explicit) O(1)

Test Bench
Carry must propogate all the way up ( in the case of 999 ) for an extra digit to be added
Test if this sentinel is a non-zero value as well!
(A) 9,99,999
    [1,0], [1,0,0], [1,0,0,0]
(B) [3,2,1]
    [3,2,2]
(C) [1,9]
    [2,0]
(D) [1,9,9]
    [2,0,0]
(E)  []
    []    
(F) [0]
    [1]

Not passed trivial test cases : [1] and [9] here

9->9->9->NIL

tailRecurse(9)
    tailRecurse(9)
        tailRecurse(9)
            tailRecurse(NULL)
            <- ret 0
        <- ret 1 [9->9->0]
    <- ret 1 [9->0->0]
<- ret 1 [0->0->0]

*/

class Solution 
{
    public ListNode plusOne(ListNode head) 
    {
        if(head == null) 
        {
            return null;
        }
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        // We can still recurse from this sentinel node ( even though it is a trailing zero )
        int carry = tailRecurse(head);
        if(carry != 0)
        {
            sentinel.val = carry;
            return sentinel;
        }
        else
        {
            return head;
        }
    }
    
    // Always identify base cases in recurse calls
    // C1 : node is null
    // C2 : node is not NULL -> work off a smaller SLL here
    // Do you recall Novak's "car/cdr" notation? Well we'll use "head and rest" here
    private int tailRecurse(ListNode node)
    {
        int head_carry = 0;
        if(node == null)
        {
            return 1;
        }
        else
        {
            int rest_carry = tailRecurse(node.next);
            int head_sum = rest_carry + node.val;
            if(head_sum >= 10)   // caught a bug
            {
                head_carry = 1; 
                // head_carry = head_sum % 10;
                head_sum -= 10;
            }
            node.val = head_sum;
            // System.out.printf("head sum = %d\n", head_sum);
            // System.out.printf("head carry = %d\n", head_carry);
        }
        return head_carry; 
    }
    
}
