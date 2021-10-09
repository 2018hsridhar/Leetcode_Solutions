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

k is reasonably boudned here [0,1e5]
list length - n - also reasonably boudned [0,500]
list values also reasonable [-1e5,1e5]
sum won't exceed 1e5

Complexity : 
let n := len(longest_SLL)
let k := #-SLLs
Time = O(nk)
Space = O(k)

Edge Case Testing : 
===> is indeed comprehensive enough <===
(A) []
    []
(B) [[]]
    []
(C) [[1]]
    [1]
(D) [[1,2]]
    [1,2]
(E) [[1],[2]]
    [1,2]
(F) [[1],[]]
    [1]
*/

class Solution 
{
    public ListNode mergeKLists(ListNode[] lists) 
    {
        // One typename -> ensures strictness of the two types under element-by-element comparison
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode lhs, ListNode rhs)
            {
                if(lhs.val < rhs.val)
                    return -1;
                else if ( lhs.val > rhs.val)
                    return 1;
                return 0;
            }
        });
        int n = lists.length;
        for(int i = 0; i < n; ++i)
        {
            ListNode firstHead = lists[i];
            if(firstHead != null)                
                minHeap.add(firstHead);
        }
        
        // [3] Iterate over the heap, and create the resultant ListNode object
        // Initial pointer itself needs a value as well! So denote as a sentinel!
        ListNode sentinel = new ListNode();
        ListNode tail = sentinel;                   // Tail is a more "apt" name for the end of a SLL
        while(!minHeap.isEmpty())
        {
            ListNode minNode = minHeap.poll();
            ListNode nextCandidate = minNode.next;
            minNode.next = null;
            tail.next = minNode;
            tail = minNode;
            if(nextCandidate != null)
                minHeap.add(nextCandidate);
        }
        return sentinel.next;
    }
}
