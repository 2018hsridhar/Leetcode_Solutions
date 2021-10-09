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

23. Merge k Sorted Lists
URL = https://leetcode.com/problems/merge-k-sorted-lists/

Complexity : 
let n := len(longest_SLL)
let k := #-SLLs
Time = O(nk)
Space = O(k)

Edge Case Testing : 
(A)
(B)
(C)
(D)
(E)
(F)

*/

class Solution 
{
    public ListNode mergeKLists(ListNode[] lists) 
    {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(new Comparator<ListNode,ListNode>{
            @override
            public int compareTo(ListNode lhs, ListNode rhs)
            {
                if(lhs.val < rhs.val)
                    return -1;
                else if ( lhs.val > rhs.val)
                    return 1;
                return 0;
            }
        });
        ListNode result = new ListNode();
        int n = lists.length;
        for(int i = 0; i < n; ++i)
        {
            ListNode firstHead = lists[i];
            minHeap.add(firstHead);
        }
        while(!minHeap.isEmpty())
        {
            
        }
        return results;
    }
}






