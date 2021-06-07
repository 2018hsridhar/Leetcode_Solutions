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

THOUGHT PROCESSES : 
The dumb solution - count frequency of each node
Create a new SLL, traverse original list, and add those nodes whose frequency equals one onlhy
Not in-place and takes extra space - but sure is a working solution!
More efficient : set up for deletions in place instead!

https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/
1836. Remove Duplicates From an Unsorted Linked List


*/

class Solution {
    public ListNode deleteDuplicatesUnsorted(ListNode head) 
    {
        HashMap<Integer,Integer> freqs = new HashMap<Integer,Integer>();
        ListNode itr = head;
        while(itr != null)
        {
            int key = itr.val;
            if(!freqs.containsKey(key))
            {
                freqs.put(key, 1);
            }
            else
            {
                freqs.put(key, freqs.get(key) + 1);
            }
            itr = itr.next;
        }
            
        // Now make new list
        ListNode start = head;
        ListNode result = new ListNode(0,null); // set up as a sentinel dummy node here!
        ListNode sentinel = result;
        while(start != null)
        {
            if(freqs.containsKey(start.val))
            {
                if(freqs.get(start.val) == 1)
                {
                    ListNode toAppend = new ListNode(start.val, null);
                    result.next = toAppend;
                    result = result.next;
                }
            }
            start = start.next;
        }
        
        
        return sentinel.next;
    }
}
