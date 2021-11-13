/*

622. Design Circular Queue
URL = https://leetcode.com/problems/design-circular-queue/

Ring buffer data structure
Make use of spaces in front of queue

Either implement as an array ( contiguous memory block : resizing difficult ) 
or as a SLL ( dynamic memory : resizing easier )
If as a SLL : need a pointer back to the front now

Complexity
Let _ := _____
Time = 
Space = 

Test Cases : 
(A) ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
    [[3],[1],[2],[3],[4],[],[],[],[4],[]]
(B) no enques : just executed .Front() or .Rear()
    ["MyCircularQueue","Front"]
    [[3], []]
(C) ["MyCircularQueue","Rear"]
    [[3], []]
(D) ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue", "Front","Front","enQueue", "Rear","Rear"]
    [[3],[1],[2],[3],[4],[],[],[4],[],[]]
    [null,true,true,true, false, 1, 1, false, 3, 3]
    enQueue works as expected
(E) ["MyCircularQueue","enQueue","enQueue","deQueue", "deQueue","deQueue","deQueue"]
    [[3],[1],[2],[],[],[],[]]
(F) ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "isFull", "deQueue", "deQueue", "deQueue", "isEmpty", "enQueue", "enQueue", "enQueue", "isFull"]
[[3], [1], [2], [3], [],[],[],[],[],[1],[2],[3],[]]
(G) ["MyCircularQueue", "enQueue", "Front","Rear", "enQueue", "enQueue", "Front", "Rear", "deQueue", "enQueue", "Front", "Rear", "deQueue", "deQueue", "Front", "Rear"]
[[3], [1], [], [], [2], [3], [],[],[],[4],[],[], [], [], [], []]

Question to ask : what data am I storing here? Do we support duplicates as well?
Answer : Yes, we support duplicate integers as well.

They need to ask if front() and rear() is an operation akin to peek() or pop() ?
    -> enque()/dequeue() : akin to pop
    -> is it a read-only operation?

Closely observe the number of external calls made to each operation : comes in useful for APIs


*/

// Analogize "this" to be a reference to our object here
class Node
{
    public int val;
    public boolean filled;
    public Node next;
    
    public Node()
    {
        this.val = 0;
        this.filled = false;
        this.next = null;
    }
    
    public Node(int val, boolean filled, Node next)
    {
        this.val = val;
        this.filled = filled;
        this.next = next;
    }
}
    
class MyCircularQueue 
{
    int size;
    int numEls;
    Node head;
    Node firstPtr;
    Node lastPtr;
 
    private Node buildList(int k)
    {
        Node head = new Node();
        Node cur = head;
        for(int i = 1; i < k; ++i)
        {
            Node nextEl = new Node();
            cur.next = nextEl;
            cur = nextEl;
        }
        // Now at end : pointer to the head
        cur.next = head; // works in singleton case : loop NOT evaluated here
        return head;
    }
    
    // Constructors initialize class/object variables!
    public MyCircularQueue(int k) 
    {
        numEls = 0;
        size = k;
        head = buildList(k);
        firstPtr = head;
        lastPtr = head;
    }
    
    // Logic not correct : what if the first case in fact for enqueue?
    // Didn't this trip you up a bit earlier? 
    public boolean enQueue(int value) 
    {
        boolean opStat = true;
        Node nextEl = lastPtr.next;
        if(isEmpty()) // special case
        {
            lastPtr.val = value;
            lastPtr.filled = true;
            opStat = true;
            numEls++;
            return opStat;
        }
        else if(nextEl.filled == false)
        {
            nextEl.val = value;
            nextEl.filled = true;
            lastPtr = nextEl;
            opStat = true;
            numEls++;
        }
        else
        {
            opStat = false;            
        }
        return opStat;
    }
    
    // Is dequeue for the single el case special though?
    public boolean deQueue() 
    {
        boolean opStat = true;
        if(isEmpty())
        {
            opStat = false;
        }
        else
        {
            // Can we leverage size field as well?
            // Careful : firstPtr can equal lastPtr, but never supersede it!
            Node nextEl = firstPtr.next;
            firstPtr.val = 0;
            firstPtr.filled = false;
            if(numEls > 1)
            {
                firstPtr = nextEl;
            }
            --numEls;
            opStat = true;
        }
        return opStat;        
    }
    
    // Yes we had an answer : you forgot to read
    // Check if queue is empty before return
    public int Front() 
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
    
    public int Rear() 
    {
        int ans = 0;
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
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
