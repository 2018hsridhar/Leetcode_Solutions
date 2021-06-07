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
 https://leetcode.com/problems/linked-list-components/
 817. Linked List Components

 */
 
class Solution {
    public int numComponents(ListNode head, int[] nums) 
    {
        HashSet<Integer> vals = new HashSet<Integer>();
        for(int i : nums)
            vals.add(i); 
        
        int numComps = 0;
        boolean isConnComp = false;
        while(head != null)
        {
            ListNode firstNode = head;
            int val = firstNode.val;
            if(vals.contains(val) && isConnComp == false)
            {
                isConnComp = true;
                ++numComps;
            }
            else if ( vals.contains(val) && isConnComp == true)
            {
                isConnComp = true;
            }
            else
                isConnComp = false;
                
            head = head.next;
        }
            
        
        
        return numComps;
    }
}
