/*

URL = https://leetcode.com/problems/copy-list-with-random-pointer/
138. Copy List with Random Pointer

Idea : 
Utilize Hashmap data structure to store each unique object created
Luckily, addresses of pointers/objects are unique
- do need to handle the null pointer/zero pointer case ( luckily, null maps to 0 in VM, but not in physical memory - that would be bad for our kernel/OS ) 
- map addreses < old_pointers, new_pointers> from <OLD_LIST, NEW_LIST>
- We need to allocate O(N) space, in lists of graphs, where N := number of Nodes
Iterate over data structures the second time and then fill out pointers, based on the mapping established earlier





Computational Complexity desired : 
Time = O(N) + O(N) : two-pass : copy Nodes -> copy pointers
Space = O(N) for hashmap 

*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution
{
    public Node copyRandomList(Node head) 
    {
        
    }
}
