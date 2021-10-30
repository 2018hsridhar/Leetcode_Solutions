/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

/*
CLL := Circular Linked List
We have next ptr and prev ptr
Also have child ptr as well ( may be its own DLL ) 
Nested structures -> recursive approaches ( multilevel data structures ) 

URL = https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
430. Flatten a Multilevel Doubly Linked List

Complexity
Let N := #-nodes total
Time = O(N)
Space = O(N) ( implicit ) O(1) explicit

TEST BENCH
(A) [1,2,3,4,5,6]
(b) [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
(c) [1,null,null,2,null,null,3,null,4]
(D) [1,2,3,4,5,6,null,null,7,8,9,10] ( *** levels = 2 *** ) 
(E) [1,null,2]
(F) [1,null,2,null,3,null] ( *** more nesting *** ) 
(G) [1,null,2,3,null,null,4,5,6,null,null,null,7,8,9,10] ( some weird diagonal thing ) 


*/

class Solution 
{
    /*
    It is a DLL question : you need to merge both of them as well! SHIT!
    Take note : the head of a DLL can have a non-null child pointer as well
    */
    public Node flatten(Node head) 
    {
        helper(head);
        return head;
    }

    private Node helper(Node head)
    {
        if(head == null)
            return null;
        
        // Check if we have a child -> recurse down there, and set end pointer all the way to there as well
        if(head.child != null)
        {
            // Previous state of the DLL
            Node cur = head;
            Node remainder = cur.next;
            
            // Sublist compute : grab head and tail of this sublist as well
            // Unset that child relationship in cur as well
            Node subListHead = flatten(head.child);
            Node subListTail = subListHead;
            while(subListTail.next != null)
            {
                subListTail = subListTail.next;
            }
                
            // Set next relationships : not set those previous relationships
            cur.child = null;
            cur.next = subListHead;
            subListHead.prev = cur;
            subListTail.next = remainder;
            if(remainder != null)
                remainder.prev = subListTail;
        }
        helper(head.next);
        
        return head;
    }
}
