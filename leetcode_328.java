// https://leetcode.com/problems/odd-even-linked-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // ... so am I donem by the time one of these guys is null ... hmm
 // 1->2->3->4-> null ...
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        // ... wait ... if odd = null, how do I progress ... hmm ... need to also prevent myself from progressing, right?
        while(odd != null && even != null)
        //while(odd.next != null && even.next != null) // this might be more right though ! ... but what if even = null ( singleton node case )
        {
            odd.next = even.next;
            // ... early  break, if odd.next = null, @ this point?
            if(odd.next == null)
            {
                break;
            }
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead;
        return head;
    }
}
