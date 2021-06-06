/*

706. Design HashMap
https://leetcode.com/problems/design-hashmap/

THOUGHT PROCESSES : 

Collision Prevention : Seperate Chaining with Buckets - Utilize SLL ( or self-balancing BSTs )
Instaed of a List<Integer> - not a List<int[]> 
Modulo by the base ( array size ) - which ideally, is a prime number
Can we bound the bucket / container size too ( by some <k> value )?


Time-Space Analysis
n :- number of keys

Space for Hashmap : Worst case - O(k)
Time for Hashmap : 
Get, Put, Remove : O(k) - iterate over a LL 

At most 10^4 calls to put/get/remove - set base to 10^4 then
Key bounded by closed interval [0,10^6]

Write a "contains" method - useful for testing "remove" and "add" methods quickly too!

If the array is initialized -> then we can easily access its methods too ( vs. being null )!
Is more useful to sometimes utilize the getter and check a .size() > 0 constraint instead!

*/

class MyHashMap {
    
    LinkedList<int[]>[] array;
    int base;
    int numBuckets;

    /** Initialize your data structure here. */
    public MyHashMap() 
    {
        array = new LinkedList[10000];
        base = 10000; // 10^4
        numBucket​s = 100;
        for(int i = 0; i < array.length; ++i)
        {
            array[i] = new LinkedList<int[]>();
        }
    }
    
    // Always iterates over the entirety of the bucket, if found
    public boolean contains(int key)
    {
        int hashIdx = key % base;
        LinkedList<int[]> buckets = array[hashIdx];
        if(buckets.size() == 0)
            return false;
        for(int i = 0; i < buckets.size(); ++i)
        {
            int[] curPair = buckets.get(i);
            if(curPair[0] == key)
                return true;
        }
        return false;
    }
    
    /** value will always be non-negative. */
    // Check if you contain the key or not too - if you do, it be an update of an existing value
    public void put(int key, int value) 
    {
        if(contains(key))
        {
            int hashIdx = key % base;
            LinkedList<int[]> buckets = array[hashIdx];
            for(int i = 0; i < buckets.size(); ++i)
            {
                int[] curPair = buckets.get(i);
                if(curPair[0] == key)
                {
                    curPair[1] = value;
                    break;
                }
            }   
            // Need to update existing value BTW!
        }
        else
        {
            int hashIdx = key % base;
            //  Nice! Used a reference return here for in-place modification
            LinkedList<int[]> buckets = array[hashIdx];
            int[] newPair = { key, value };
            buckets.add(newPair);
        }
        
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) 
    {
        if(!contains(key))
            return -1;
        int hashIdx = key % base;
        LinkedList<int[]> buckets = array[hashIdx];
        for(int i = 0; i < buckets.size(); ++i)
        {
            int[] curPair = buckets.get(i);
            if(curPair[0] == key)
            {
                return curPair[1];
            }
        }   
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) 
    {
        if(!contains(key))
            return;
        int hashIdx = key % base;
        LinkedList<int[]> buckets = array[hashIdx];
        for(int i = 0; i < buckets.size(); ++i)
        {
            int[] curPair = buckets.get(i);
            if(curPair[0] == key)
            {
                buckets.remove(i);
                break;
            }
        }     
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
