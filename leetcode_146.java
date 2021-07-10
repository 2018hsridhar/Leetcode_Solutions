/*
146. LRU Cache

THOUGHT PROCESS : 
Most cachues require the use of hashmaps/arrays as get/put operations must be O(1) constant-time operations
(K,V) datatypes = (Integer, Integer) 

Computational complexity : 

Edge case testing : 



*/

class LRUCache 
{
    HashMap<Integer,Integer> cacheLines;
    int capacity;
    
    public LRUCache(int capacity) 
    {
        cacheLines = new HashMap<Integer,Integer>();   
        this.capacity = capacity;
    }
    
    // Get operations will impact cache-lines in their LRU policy
    public int get(int key) 
    {
        
    }
    
    public void put(int key, int value) 
    {
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
