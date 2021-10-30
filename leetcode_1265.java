/**
 * // This is the ImmutableListNode's API interface.
 * // You should not implement it, or speculate about its implementation.
 * interface ImmutableListNode {
 *     public void printValue(); // print the value of this node.
 *     public ImmutableListNode getNext(); // return the next node.
 * };
 */

/*
1265
URL = https://leetcode.com/problems/print-immutable-linked-list-in-reverse/

Is def a SLL problem
Let us conjure up either a head recursive OR a tail recursive approach
We are doing a print in reverse, so process the rest of list before the backtracking/call back to print parent value

Complexity
Let N := len list
Time = O(N)
Space = O(N) ( implicit ) O(1) ( explicit ) 
It can be solved in less than linear space and linear time complexity
But can not change implicit call stack space for sure ( we need some way to save computation state ) 

*/
class Solution 
{
    public void printLinkedListInReverse(ImmutableListNode head) 
    {
        if(head == null)
            return;
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }
}
