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
    -> nope : k is bounded by [1,n] here inclusive :-)
    -> node values also reasonable as well, and so too <n>
List is never null or empty length either
    
We may need to quickly grab size information as well
Finding 'kth' elem forward is easy : backwards not -> can we leverage call stack?
    We don't want to create a copy of the SLL, reversed here.


Values swap : NOT a node swap :-) -> we just need two local variables here 

COMPLEXITY
Let N := len(list)
Time = O(N)
Space = O(1) ( explicit ) O(N) ( implicit ) 

TEST CASES : 
(A) [1,2,3,4,5] k = 3
    [1,2,3,4,5] : leave list as is
(B) [1,2,3,4] k = 3
    [1,3,2,4]
(C) [1,2,3,4,5] k = 2
    [1,4,3,2,5]
(D) [1,2,3,4,5] k = 1
    [5,2,3,4,1]
(E)

Modularize -> grab forward, grab backward, execValueSwitch : 3 funcs
Idea : leverage a wrapper class ( get ListNode, depth ) information both ways

*/

class Solution 
{
    
    static class Wrapper
    {
        int depth;
        ListNode node;
        
        public Wrapper(){}
        public Wrapper(ListNode node, int depth)
        {
            this.node = node;
            this.depth = depth;
        }
    }
    
    public ListNode swapNodes(ListNode head, int k) 
    {
        ListNode forward = grabForward(head,k);
        Wrapper backwardsInfo = grabBackward(head,k);
        ListNode backward = backwardsInfo.node;
        swapNodeValues(forward,backward);
        return head;
    }
    
    // k-1 as a SLL is 1 indexed as well ( or start at one )
    private ListNode grabForward(ListNode head, int k)
    {
        ListNode res = head;
        for(int i = 1; i < k; ++i)
            res = res.next;
        return res;
        
    }
    
    // Note : in a non-void func return, we can NEVER return a local variable which never undergoes initialization!
    // Compilers care a lot about non-initialized values!
    private Wrapper grabBackward(ListNode head, int k)
    {
        if(head.next == null)
        {
            return new Wrapper(head, 1);
        }
        else
        {
            Wrapper childInfo = grabBackward(head.next, k);
            if(childInfo.depth == k)
            {
                return childInfo;
            }
            else if ( childInfo.depth < k)
            {
                return new Wrapper(head,childInfo.depth + 1);
            }
        }
        return null;    // We still need this return statement, even if all the conditional logic evaluated as expected!
    }
    
    private void swapNodeValues(ListNode forward, ListNode backward)
    {
        int temp = forward.val;
        forward.val = backward.val;
        backward.val = temp;
    }
    
}


