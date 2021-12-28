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

CLARIFICATIONS :

Given head of a SLL
We repeatedly delete consecutive node sequences
Return head of the final SLL ( head may change if prefix of the list yields zero )
ListNode objects contain INTEGRAL values ( they may skew negative or zero as well, and skew duplicates, but they are in range [-1K, 1K] )
Always ask -> if many answers, or only one here, is acceptable.

URL = https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
1171. Remove Zero Sum Consecutive Nodes from Linked List

THOUGHT PROCESSES : 
1. Can incorporate use of a hashmap here ( running sums -> node which led to that summation )
2. Can also utilize 
3. Two pointers technique


COMPLEXITY
Let N := #-nodes in the SLL
Let K := length longest zero sum sequence in the SLL
TIME = O()
SPACE = O() ( EXP ) O() ( IMP )

TEST CASES : 
(A) [0,0,0,0,0,0]
    [] <- head points to NULL here
(B) [0,0,0,0,0,1]
    [1] <- head points to final node
(C) [1,5,2,6,2,3,9]
    [1,5,2,6,2,3,9] <- head points to start @ 1 ( no deletions took place )
(D) [-1,-2,-3,-4,-5,15]
    [] <- head points to NULL ( all elems deleted )
(E) [1,6,-5,4,-3,2,-1,10]
    [1,6,-5,4,-3,2,-1,10] <- head points to first el ( no deletions at all )
(F) [1,2,3,-6,10]
    [10] <- head points to 10 ( delete the prefix ) 
(G) [10, 1,2,3,-6]
    [10] <- suffix deletion
(H) [1,2,3,-3,-3,1,2]  
    [1,2] <- head of new SLL ( either the LHS or the RHS )
(I)
(J)


PSEUDOCODE :

    HashMap<run_sum,node> HM = ()
    runSum = 0
    ptr = head
    while(ptr != null)
        runSum += ptr.val
        if(HM contains key runSum)
        {
        
        }
        else
        {
            HM put tuple (runSum, ptr)
        }
        ptr = ptr.next
    
    

*/

class Solution 
{
    public ListNode removeZeroSumSublists(ListNode head) 
    {
        
    }
}
