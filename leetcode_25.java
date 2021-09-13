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

Always guaranteed at least one node as well
Let N := length of the SLL
Let K := a positive integer, such that (1 <= k <= n)
Do not affect left-out nodes at the end too ( if n is NOT a multiple of k ) 

Node values are reasonable
Size of SLL is 5K max here

COMPLEXITY : 
Asymptotic RunTime Complexity = O(N)
Asymptotic Space Time Complexity = O(1)

Let us conjure up a recursive method here
Pass state and keep checking a counter too ( if we hit k or not ) 

k = n : swap (n-1) edges for n nodes
E.g. k = 1 : swap 0 nodes, and in ex_2, k = 3 : swap 2 edges
Can we proceed forward here? Akin to CONS
-> be careful with forward movement due to the multiple

TEST BENCH : 

(a)
[]
1

(b)
[1]
1 { or any other value } 

(c)
[1,2]
2

(d)
[1,2]
2

(e)
[1,2,3,4,5,6]
3

(f)
[1,2,3,4,5,6,7,8]
3

Think of this problem in a highly inductive sense too!
Note : we must return the head of the first reversed list here! Take close heed too!

*/

class Solution 
{
    public ListNode reverseKGroup(ListNode head, int k) 
    {
        if(head == null)
            return null;
        if(k == 1) return head; // we really do NOT modify here
        ListNode reved = helper(head, k);        
        return reved;
        
        
    }
    
    // Make sure to handle the NULLPTR case for "head" here too
    private ListNode helper(ListNode head, int k)
    {
        if(head == null)
            return null;
        int i = k;
        ListNode cur = head;
        while(i > 1)
        {
            if(cur == null)
                return head; // terminate early here : just return SLL as is!
            cur = cur.next;
            --i;
        }
        // expect i = 1 here
        // Is i = 1 ( and NULL ) or i = 1 ( and NOT NULL ) 
        ListNode cons;
        if((i > 1 && cur == null) || cur == null)
            return head;
        else
        {
            cons = cur.next;             
            cur.next = null;            
        }
        ListNode[] headTailPair = reverseSLL(head);
        ListNode subListTail = headTailPair[1];
        ListNode subListHead = headTailPair[0];
        ListNode resultant = helper(cons, k); // HEAD-RECURSION HERE
        subListTail.next = resultant;
        return subListHead;
    }
    
    private ListNode[] reverseSLL(ListNode head)
    {
        if(head == null)
            return null;
        ListNode prev = null;
        ListNode cur = head;
        ListNode tail = head; // will be the tail node eventually!
        ListNode next = cur.next;
        while(cur != null)
        {
            cur.next = prev;
            prev = cur;
            cur = next;
            if(cur == null)
                next = null;
            else
                next = cur.next; // happens once cur changes from [5] to NULLPTR here
        }
        // return prev; // head of the reverse SLL ( may also return tail of SSL for this algo ) 
        ListNode[] headTail = new ListNode[2];
        headTail[0] = prev;
        headTail[1] = tail;
        return headTail;
    }
    
    
}
