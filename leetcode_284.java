// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

/*
284. Peeking Iterator
URL = https://leetcode.com/problems/peeking-iterator/
Extends the Iterator<T> interface -> write impl of methods to override too.

Handle of edge cases ( e..g you had no data in the input ) 

LinkedList<T> should suffice underneath.
No need to support explicit removal.

Let N := #-elements in the iterator. Constructor is a O(N) = T, O(N) = S operation
All other operations of class PeekingIterator are O(1) Time, O(1) Space
Let us aim to avoid a lot of calls to member data ; in lieu, keep a state and update over time.

Edge Cases
(A)
(B)
(C)
(D)
(E)

*/

class PeekingIterator implements Iterator<Integer> {
    
    Iterator<Integer> originalIterator;
    private List<Integer> myData;
    private int size;
    
	public PeekingIterator(Iterator<Integer> iterator) {
        originalIterator = iterator;
        myData = new LinkedList<Integer>();
        size = 0;
        while(iterator.hasNext()){
            myData.add(iterator.next());
            size++;
        }
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        int res = (size > 0 )? myData.get(0) : -1;
        return res;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        int res;
        if(size > 0){
            res = myData.get(0);
            myData.remove(0);
            size--;
        } else {
            res = -1;
        }
        return res;
	}
	
	@Override
	public boolean hasNext() {
        return (size > 0);
	}
}
