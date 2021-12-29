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
    <- algo needs remedying: plz fix again
    <- esp for the singleton case!
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
(I) [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
    -> not as slow

Seems a bit slow?
You could write this all out into a seperate array, and do deletions accordingly -> but is kinda lousy practice
to go recreating your list and NOT modifying it in place as well!
Yes the algo requires constant addition and removal of keys here

PSEUDOCODE :

    Create sentinel node with zero val
    sentinel next = head
    HashMap<run_sum,node_address> HM = ()
    runSum = 0
    ptr = head
    while(ptr != null)
        runSum += ptr.val
        if(HM contains key runSum)
            Node prev = HM getVal(runSum)
            Node temp = prev // preserve pointer here, after deleting the old keys here
            int prevSum = HM getVal(prev)
            while(prev != ptr)
                if(HM containsKey prevSum)
                    HM.remove(prevSum)
                prev = prev.next
                prevSum = HM getVal(prev)
            temp.next = ptr.next    //  delete ptr node here as well
            // Make sure runSum still maps to temp here
            ptr = temp.next
        else if ( HM does not contain key runSum )
            HM put tuple (runSum, ptr)
        ptr = ptr.next
        
    Node newHead = sentinel.next
    return newHead


Debug tactic : index the nodes as we traverse

With an all 'zeroes' case : we could run a risk of duplicates -> take note how the '0' node leads to a NOP effectively!

*/

class Solution 
{
    public ListNode removeZeroSumSublists(ListNode head) 
    {
        // Forgot to add sentinel to the hashmap too!
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        HashMap<Integer, ListNode> HM = new HashMap<Integer, ListNode>();
        HM.put(0,sentinel);
        
        int nodeId = 0;
        int runSum = 0;
        ListNode ptr = head; // set to sentinel; not to head ( or other way )?
        while(ptr != null)
        {
            runSum += ptr.val;
            if(HM.containsKey(runSum) == true )
            {
                // System.out.printf("At a true case at node = [%d] \t runSum = [%d]\n", nodeId, runSum);
                ListNode prev = HM.get(runSum);
                ListNode temp = prev; // preserve pointer here, after deleting the old keys here
                int prevSum = runSum;   // must be equal to this anyways!
                while(prev != ptr)
                {
                    if(HM.containsKey(prevSum))
                    {
                        HM.remove(prevSum);
                    }
                    prev = prev.next;
                    prevSum = prevSum + prev.val;
                }
                temp.next = ptr.next;    //  delete ptr node here as well
                HM.put(prevSum, temp);  // You forgot to add back the key here!
                // Make sure runSum still maps to temp here
                ptr = temp.next;
                ++nodeId;
                // we still need to check here BTW : supose we keep on closin this list. We were able to do a skip, at least!
                // But we also need to do incrementing, which makes this slightly more difficutt as well?
            }
            else if ( HM.containsKey(runSum) == false)
            {
                HM.put(runSum, ptr);
                ptr = ptr.next;
                ++nodeId;
            }
        }
        ListNode newHead = sentinel.next;
        return newHead;
    }
}
