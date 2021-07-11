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

State represented by pointers should be the same : but actual pointers must point within their own closed set ( and not deviate outside the set ) 
Always return HEAD of a list ( makes traversal easy ) 
Random_index guaranteed to be bounded from [0,n-1] too
Yes - start copy from the HEAD of the original list ( not from a random pointer in the original list :-) ) 



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


/*
Ok so unlike C/C++, Java does not really permit the derefernceing/addressing of objects
https://www.baeldung.com/java-object-memory-address

Addresses of objects can change easily in the JVM -> cuz of weird optimization tricks by the GC
Identity hashcode != memory address != hashcode!

Constructor already handles NULL/0-initialization for us :-)


*/

class Solution
{
    public Node copyRandomList(Node head) 
    {
        if(head == null)
            return null;
        
        // [1] Create hashmap of nodes
        HashMap<Node,Node> addrMap = new HashMap<Node,Node>();
        Node cur = head;
        while(cur != null)
        {
            Node temp = new Node(cur.val);
            addrMap.put(cur, temp);
            cur = cur.next;
        }
        
        // [2] Fill in pointers for the temp nodes too
        Node secondListHead = addrMap.get(head);
        
        Node itr2 = secondListHead;
        Node firstListHead = head;
        while(firstListHead != null)
        {
            itr2.next = addrMap.get(firstListHead.next);
            itr2.random = addrMap.get(firstListHead.random);
            firstListHead = firstListHead.next;
            itr2 = itr2.next;
        }
        
        return secondListHead;
    }
}
