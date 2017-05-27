//https://leetcode.com/problems/remove-nth-node-from-end-of-list/#/submissions/1

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *  here, n is indexable from [1...5] ... handle corner cases @ positions {1,5}, TBH!
 * use a dummy node ... makes life easier, as usual :-). I think it also helps with indexing issues, and reasoning ze for loop!
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode ptr1 = dummy;
        ListNode ptr2 = dummy;
        for(int i = 0; i <= n; i++)
        {
            ptr1 = ptr1.next;
        }
        while(ptr1 != null)
        {
            ptr2 = ptr2.next; 
            ptr1 = ptr1.next;
        }

        // NOW that we have the correct node to delete! ( see @ ptr1 ) 
        ptr2.next = ptr2.next.next; // u just make progress here ... not logical GAAH! u need to set the thing to null ( set somebody's next! ) 
        return dummy.next; 
    }
}
