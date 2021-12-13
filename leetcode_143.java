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
class Solution {
    public void reorderList(ListNode head) {
        zipLinkedList(head);
    }
    
    public ListNode zipLinkedList(ListNode linkedList) 
	{
		// Trivial base cases
		if(linkedList == null || linkedList.next == null)
		{
			return linkedList;
		}
		ListNode newHead = linkedList;
		Stack<ListNode> revOrder = new Stack<ListNode>();
		ListNode head = linkedList;
		while(head != null)
		{
			revOrder.push(head);
			head = head.next;
		}
		head = linkedList;
		while(head != null || head.next != null)
		{
			if(head == revOrder.peek())
			{
				head.next = null;
				break;
			}
			ListNode fwd = head.next;
			ListNode topMost = revOrder.pop();
			head.next = topMost;
			// Even length case
			if(topMost == fwd)
			{
				topMost.next = null;
				break;
			}
			topMost.next = fwd;
			head = fwd;
		}
		
		return newHead;
	}

}
