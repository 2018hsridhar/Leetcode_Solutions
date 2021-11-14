/*

641. Design Circular Deque
URL = https://leetcode.com/problems/design-circular-deque/

Strategies : 2 pointers, SLL

So you designed a circular queue from earlier via a SLL : we can port over similar strutures
But now we can insert in front/last, or remove from front/last
Double-ended is due to the property stemming from operations available -> not due to the circular structure

is it 2000 calls max across operations -> or for a specific operation as well?

See earlier, enqueue = insertLast, dequeue = removeFront.
Now we need to support insertFront, removeLast

If designing a data structure in two dirs : let us decompose it into its single directions case instead

Complexity
Let  := 
Time = 
Space = 

TEST CASES : 
( *** provided *** ) 
(A) ["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]
    [[3],[1],[2],[3],[4],[],[],[],[4],[]]
(B) ["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getFront","getRear", "deleteFront","deleteLast","getFront","getRear", "insertFront", "insertLast", "getFront", "getRear"]
    [[4],[3],[4],[2],[1],[],[],[], [], [], [], [1],[2],[],[]]
(C)
(D)
(E)
(F)

*/

// Analogize "this" to be a reference to our object here
class Node
{
    public int val;
    public boolean filled;
    public Node next;
    public Node prev;
    
    public Node()
    {
        this.val = 0;
        this.filled = false;
        this.next = null;
        this.prev = null;
    }
    
    public Node(int val, boolean filled, Node next, Node prev)
    {
        this.val = val;
        this.filled = filled;
        this.next = next;
        this.prev = prev;
    }
}

class MyCircularDeque 
{
    
    int size;
    int numEls;
    Node head;
    Node firstPtr;
    Node lastPtr;

    // Note : we make a new element in each step : why not just DLL on the way back?
    private Node buildDLL(int k)
    {
        Node head = new Node();
        Node cur = head;
        // We may have a potential bug here! Take careful note!
        for(int i = 1; i < k; ++i)
        {
            Node nextEl = new Node();
            cur.next = nextEl;
            nextEl.prev = cur;
            cur = nextEl;
        }
        // Now at end : pointer to the head
        // works in singleton case : loop NOT evaluated here
        Node tail = cur;
        tail.next = head; 
        head.prev = tail;
        
        return head;
    }
    
    public MyCircularDeque(int k) 
    {
        numEls = 0;
        size = k;
        head = buildDLL(k);
        firstPtr = head;
        lastPtr = head;        
    }
    
    // Both "insertFront/insertLast" should work in the empty cases
    // decrement the front pointer
    public boolean insertFront(int value) 
    {
        boolean op_stat = false;
        Node prevEl = firstPtr.prev;
        // System.out.printf("Prev el value = %d\n", prevEl.val);
        if(isEmpty())
        {
            firstPtr.val = value;
            firstPtr.filled = true;
            op_stat = true;
            ++numEls;
            return op_stat;
        }
        else if(prevEl.filled == false)
        {
            prevEl.val = value;
            prevEl.filled = true;
            firstPtr = prevEl;            
            op_stat = true;
            ++numEls;
        }
        else
        {
            op_stat = false;            
        }
        return op_stat;
    }
    
    // increment the last pointer
    public boolean insertLast(int value) 
    {
        boolean op_stat = false;
        Node nextEl = lastPtr.next;
        if(isEmpty())
        {
            // System.out.printf("At empty case \n");
            lastPtr.val = value;
            lastPtr.filled = true;
            op_stat = true;
            ++numEls;
            return op_stat;
        }
        else if(nextEl.filled == false)
        {
            // System.out.printf("At non empty case \n");
            nextEl.val = value;
            nextEl.filled = true;
            lastPtr = nextEl;            
            op_stat = true;
            ++numEls;
        }
        else
        {
            op_stat = false;            
        }
        return op_stat; 
    }
    
    // increment the front pointer
    // We cannot delete from an empty DLL
    // We also can not delete from a location IF the dereferenced pointer address itself is NOT filled
    // Do special handling for size = 1 case ( do not manipulate pointers ) 
    public boolean deleteFront() 
    {
        if(isEmpty())
        {
            return false;
        }
        else
        {
            firstPtr.val = 0;
            firstPtr.filled = false;
            if (numEls > 1 )
            {
                Node nextEl = firstPtr.next;
                firstPtr = nextEl;                
            }
            --numEls;
            return true;
        }
    }
    
    // decrement the last pointer
    public boolean deleteLast() 
    {
        if(isEmpty())
        {
            return false;
        }
        else
        {
            lastPtr.val = 0;
            lastPtr.filled = false;
            if (numEls > 1 )
            {
                Node prevEl = lastPtr.prev;
                lastPtr = prevEl;                
            }
            --numEls;
            return true;
        }
    }
    
    // Yes we had an answer : you forgot to read
    // Check if queue is empty before return
    public int getFront() 
    {
        int ans = -1;
        if(isEmpty())
        {
            return -1;
        }
        if(firstPtr.filled == false)
            ans = -1;
        else
            ans = firstPtr.val;
        return ans;
    }
    
    public int getRear() 
    {
        int ans = -1;
        if(isEmpty())
        {
            return -1;
        }
        if(lastPtr.filled == false)
            ans = -1;
        else
            ans = lastPtr.val;
        return ans;
    }
    
    public boolean isEmpty() 
    {
        return (numEls == 0);
    }
    
    public boolean isFull() 
    {
        return (numEls == size);
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
