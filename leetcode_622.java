/*

URL = https://leetcode.com/problems/design-circular-queue/
622. Design Circular Queue

THOUGHT PROCESS : 
Queues tend to be implemented as either arays or linked-lists underneath
A SLL has that nice property of the front being quickly accessible ( but not the rear ) 
Arrays have O(1) access across all index variables - quick front/back operations here
Do not use the "build-in" queue data structure
Most likely needs the modulus operator too

Unlike the SLL, insertion/deletion is performed in the frontend/backend.
In SLLs, insertion/deletion performed in the backend.




Circular queue = "ring buffer"
Key question to ask in a circular buffer : are inserts performed IF the buffer is full or not ( a.k.a. do we overwrite already existing data )?

Testing/Capacity Planning
(1) Queue size is reasonably bounded by the closed interval [1,1000]
(2) Values bounded by integers in closed interval [0,1000]
-> Use (-1) to denote an invalid element? 

(3) 3000 calls maximum made to each method

Oh ... if front is unfilled ( via dequeu ) - we can fill up from there
But we can not fill once array is full ( that is, when rearPtr = frontPtr ) . 

Still a linear data structure
Operations still FIFO based

Problem makes sense after consideration of the two pointers technique

EDGE CASE TESTING : 
(1) Singleton size queue
- enqueue(x) should not work twice
- deQueue() should also not work twice
sz = 1 ; idx = 0 ; (o + 1 ) % 1 = 0 => case fails quickly


(2) Queue of k elememts, not fully filled


(3) Queue of k elements, more than fully filled

*/

class MyCircularQueue 
{
    int size;
    int[] elements;
    int frontPtr;
    int rearPtr;
    
    public MyCircularQueue(int k) 
    {
        elements = new int[k];
        size = k;
        // Initialize each leement with (-1) to denote empty space
        for(int i = 0; i < k; ++i)
            elements[i] = -1;
        
        frontPtr = 0;
        rearPtr = 0;
    }
    
    // Can enque an empty ring buffer
    // As seen in diagram : front pointer should always move back
    // One ptr to use consistently here only!
    public boolean enQueue(int value) 
    {
        // SINGLETON QUEUE CASE
        if(size == 1)
        {
            if(elements[0] == -1)
            {
                elements[0] = value;
                return true;
            }
            else
                return false; // already filled : do not try out here
        }
        
        
        // NON-SINGLETON QUEUE CASE
        // Check if rear ptr can be incremented
        rearPtr = (( rearPtr + 1 ) % size );
        if(elements[rearPtr] != -1)
            return false; // a value already exists here!
        if(rearPtr == frontPtr)
            return false; // already incremented : clearly equals here!
        
        elements[rearPtr] = value;
        return true;
    }
    
    // Can not dequeue an empty ring buffer
    // Not sure about this operatino though
    // Works with frontPtr only
    // But what if going to a part of circular buffer which is empty ( e.g. 0->-1)
    public boolean deQueue() 
    {
       // Check if rear ptr can be decremented ( and not go past frontPtr )
        if(elements[frontPtr] == -1)
            return false; // No value even present here
        
        elements[frontPtr] = -1;
        frontPtr = (( frontPtr - 1 ) % size );
        return true; 
    }
    
    public int Front() 
    {
        if(elements[frontPtr] == -1)
            return -1;
        return elements[frontPtr];
    }
    
    public int Rear()
    {
        if(elements[rearPtr] == -1)
            return -1;
        return elements[rearPtr];
    }
    
    // Can not just check by the size variable here
    // Use "frontPtr", "rearPtr" approaches
    public boolean isEmpty() 
    {
        if(size == 0)
            return true;
        
        if(Math.abs(rearPtr - frontPtr) != 0)
            return true;
        
        return false;
    }
    
    // is full if rearPtr = frontPtr or ( rearPtr + 1 ) % n = frontPtr
    public boolean isFull() 
    {
        if(frontPtr == rearPtr)
            return true;
        
        int nextRearPtr = ((rearPtr + 1 ) % size );
        if(nextRearPtr == frontPtr)
            return true;
        
        return false;
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
