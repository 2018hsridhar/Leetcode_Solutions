/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

/*

URL = https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
708. Insert into a Sorted Circular Linked List

Is a circular linked list node, sorted in ascending order
Insert a value "insertVal" s.t. list still remains SORTED and circular in nature
Given node may be a REFERENCE/PTR to any list node  not always the smallest 
Choose wherever insertion mkes sense
If list empty : create a new SCL

SCL = Single Circular List
#-nodes is reasonable
Node vals reasonably boudned too

We need iteration BUT unlike SLL -> can not terminate at a null node
Values not unique ( but addresses are ) 
We can terminate if we hit same element again : maintain the set of addresses OR maintain a pointer to first address encountered?


Complexity
Let N := len(SCL)
Time = O(N)
Space = O(N) implicit  O(1) explicit

*/

class Solution 
{
    public Node insert(Node head, int insertVal) 
    {
        // Special empty case
       if(head == null)
       {
           Node scl = new Node(insertVal);
           scl.next = scl;
           return scl;
       }
        else
        {
            // Same pointer trick : generally folks think of a while(next != head) type of reasoning
            // Can we experiment with a different approach here?
            // Can perform a BREAK operation after each new single node insertion as well
            // Find out if we do insertion after head though ( before means some other element )
            Node cur = head;
            Node next = head.next;
            int visitHeadCount = 0;
            while(true)
            {
                if(visitHeadCount == 2)
                    break;
                if(cur == head)
                    visitHeadCount++;
                int left = cur.val;
                int right = next.val;
                
                // Common conditional statements can be evaluated as a same boolean anyways
                boolean dropInsert = ( left>right && (insertVal >= left || insertVal <= right));
                boolean openIntervalInsert = ( left < right && (insertVal >= left && insertVal < right));
                boolean constantListInsert = ( left == right && next == head);
                if(dropInsert || openIntervalInsert || constantListInsert)
                {
                        Node novel = new Node(insertVal);
                        cur.next = novel;
                        novel.next = next;
                        break;
                }
                cur = next;
                next = cur.next;
            }
            // Case of next = head here
            return head;
        }
    }
}




