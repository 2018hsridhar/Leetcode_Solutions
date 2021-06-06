// 707. Design Linked List
// https://leetcode.com/problems/design-linked-list/

/* 
 */
 

/*
Like most other data structures, such as trees/graphs, we need a seperate node class with attributes/getters/setters
Assume nodes are zero-indexed too
Track size of SLL over time
*/

public class Node {
    public int val;
    public Node next;
    
    public Node(){}
    public Node( int val )
    {
        this.val = val;
    }
    
    public int getVal()
    {
        return this.val;
    }
    
    public Node getNext()
    {
        return this.next;
    }
    
    public boolean hasNext()
    {
        if(this.next != null)
            return true;
        return false;
    }
}


class MyLinkedList {
    
    int size;
    Node head;

    /** Initialize your data structure here. */
    public MyLinkedList() 
    {
        head = new Node();
        size = 0;
        
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) 
    {
        int valAtIndex = -1;
        int count = 0;
        Node cur = head.getNext(); // get past the sentinel node too!
        while(cur != null)
        {
            if(count == index)
            {
                valAtIndex = cur.val;
                break;
            }
            count++;
            cur = cur.next;
        }
        return valAtIndex;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) 
    {
        ++size;
        
        Node newEl = new Node(val);
        newEl.next = null;

        newEl.next = head.next;
        head.next = newEl;
        
        // System.out.printf("head.next = %d; val = %d\n", head.next.val, val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) 
    {
        ++size;
        
        Node newEl = new Node(val);
        newEl.next = null;
        
        Node cur = head;
        while(cur.next != null)
            cur = cur.next;
        
        cur.next = newEl;
        
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) 
    {
        if(index >= size)
            return; // Node can not be added anyways 
        else if ( index == size ) 
            addAtTail(val);
        else
        {
            Node newEl = new Node(val);
            newEl.next = null;
            
            // Handle 0 index case too!
            Node cur = head;
            for(int i = 0; i < index; ++i)
            {
                cur = cur.next;
            }
            
            newEl.next = cur.next;
            cur.next = newEl;
        }
        ++size;
        
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) 
    {
        if(index > size)
            return; // Node can not be added anyways 
         else
        {
            // Handle 0 index case too!
            Node cur = head;
            for(int i = 0; i < index; ++i)
            {
                cur = cur.next;
            }
             
            Node fwd = cur.next;
            if(fwd == null)
                cur.next = null;
            else
                cur.next = fwd.next;
            // fwd.next = null;
            
        }
        --size;
        
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
