// 1669. Merge In Between Linked Lists

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

No need to clear memory via free() call since we can just reroute the pointers of each node to another node
In effect, leaving dangling nodes, but lets assume a resource cleaner exists for us

Need to identify the ath node and the bth node - can race along the LL, two seperate pointers
What if a=b? Let us assume b>=a, at least. Yes : a and b can equal each other
Explot O(1) insertion and removal of LL nodes property too

Can assume that list lengths are at least greater than or equal to one : no empty list case
Race the <b> pointer first, then, the <a> pointer.
Can also obtain size of linked lists, in O(n) amount of time
Merge takes place in only <list1> : NOT in <list2>, and b <= list1.length - 2
"Nth-node" expression is 0-indexed : NOT, 1-indexed ( please offer feedback to question developer ) 
Due to constraint imposed on value of b - guaranteed to obtain next node, without an OOB - Out of Boudns - issue, since next node is bounded by the closed inclusive interval [a+1, list1.length - 1]
Since a is strictly bounded by "1", guaranteed a lookup to next node must HIT the <a> node at some point of time, as the preceding node will be bounded by [0,a-1]

IDEAL : [T,S] = [O(n), O(1)], where n = number of elements in either max length LL or the sum of both LL lengths

EDGE CASES : 
- no need to worry about edge case where insertion in front or back must be done
- What if really long lists? - need not worry : max length = 10^4 = 10K

*/

class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2)
    {
        ListNode merged = list1; 
        
        // Obtain (bth+1) node
        ListNode newTailList = list1;
        for(int i = 0; i <= b; ++i)
        {
            newTailList = newTailList.next;
        }
        
        
        // Obtain (ath - 1) node
        ListNode subHeadList = list1;
        for(int i = 0; i <= (a-2); ++i)
        {
            subHeadList = subHeadList.next;
        }
        
        // System.out.printf("nTL = [%d]\t sHL = [%d]\n", newTailList.val, subHeadList.val);
        subHeadList.next = list2;
        
        // iterate over entirety of lit2
        ListNode cur = list2;
        while(cur.next != null)
            cur = cur.next;
        cur.next = newTailList;
        
        return merged;
    }
}
