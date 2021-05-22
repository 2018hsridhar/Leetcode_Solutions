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
  THOUGHT PROCESSES
  
  IDEAL : [T,S] = [O(N), O(1)] , where N = number of elements in the LL
  Node sequences must be consecutive in nature
  
  Notice : most LL inputs/outputs tend to be their corresponding serializations of objects. Hence why such questions of serialization/deserialization are asked in interviews.
  
  Ma length LL is 1000. Bounded by the closed interval [1,1000]
  Values of nodes are bounded by the closed interval [-1000, 1000]
  
  We can obtain the sum of node values in O(n) time and try to construct the resulting new LL backwards?
  If sum = 0 : we are effectively done
  
  Suppose every values where positive, we'd know that summation too
  
  Build up the cases too
  [1] -> [1]
  [1, 2] -> [1, 2]
  [1, 2, 3] -> [1, 2, 3 ]
  [1, 2, -3 ] -> []
  [1,2,-4] -> [1,2,-4]
  
  SPENT 30 MINUTES TRYING TO INITIALLY SOLVE!
  
  Is there a sliding window technique here?
  
  And suppose input were sorted too? Could this help with understanding?
  Can a running sum also be stored here?
  
  Does sorting really help here? Values in the LL must be consecutive too!
  
  BASE CASE : n = 1 element - just return list itself
  
  EDGE CASES : 
  [1,2,-4] -> [1,2,-4] since the sum of all elements = -1; not 0
  
  Problem seems very "stack-like" in nature : push and pop elements and see whether your current running sum = 0 or not
  
  
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        return null;
    }
}
