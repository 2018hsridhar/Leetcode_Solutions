/*

705. Design HashSet
https://leetcode.com/problems/design-hashset/

THOUGHT PROCESSES : 

When building a data structure, note performance times and edge cases

Add : [T,S] = [O(1),O(1)]
Remove : [T,S] = [O(1),O(1)]
Contains : [T,S] = [O(1),O(1)]

Does support for the size have to be static or be dynamic?
Sometimes, to support sort() operatinos quickly, we want a BST - Binary Sorted Tree!
Ordering of values will not matter here! 

Dynamic resizing may entail a type of "amortized" performance too
key type to a bucket index

At most 10^4=10,0000 calls made to add/remove/contains
But why initialize an array of size 10,000? Seems inefficient too?

How to know when to resize our array though?
Keys are in range [0,10^6]. Use (-1) to denote an empty area!

So range is [0,10^6] and we make 10^4 calls max.
Why not make an array of considerably large memory anyways, to avoid resizing issues?
If made to x, we will have to set up buckets, then for 10 elements in each hashmap index
But the max iteration here is O(10), not O(N) -> hence, it remains constant anyways

*/


class MyHashSet {

    LinkedList<Integer>[] values;
    int numBuckets;
    /** Initialize your data structure here. */
    public MyHashSet() 
    {
        values = new LinkedList[100];
        numBuckets = values.length; // 10 here
        for(int i = 0; i < values.length; ++i)
        {
            values[i] = new LinkedList<Integer>();    
        }
    }
    
    // hashIndex = key % noOfBuckets
    // We may have a range which key maps onto [ 0, x ]. Ideally it fits within our desired data type
    // "add" should not actually modify if an element already exists here
    public void add(int key) 
    {
        if(contains(key))
            return; // do not perform any operations
        
        int hashIdx = key % numBuckets;
        LinkedList<Integer> buckets = values[hashIdx];
        buckets.add(key);
    }
    
    public void remove(int key) 
    {
        if(!contains(key))
            return; 
        
        int hashIdx = key % numBuckets;
        LinkedList<Integer> buckets = values[hashIdx];
        for(int i = 0; i < buckets.size(); ++i)
        {
            int x = buckets.get(i);
            if(x == key)
            {
                buckets.remove(i);
                break;
            }
        }
        
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) 
    {
        boolean containsStatus = false;
        int hashIdx = key % numBuckets;
        LinkedList<Integer> buckets = values[hashIdx];
        if(buckets.size() == 0) // no elements here - return null
            return false;
        for(int i : buckets)
        {
            if(i == key)
                return true;
        }
        return false;
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
