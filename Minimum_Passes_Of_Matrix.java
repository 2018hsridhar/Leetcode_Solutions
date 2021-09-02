// https://www.algoexpert.io/questions/Minimum%20Passes%20Of%20Matrix

import java.util.*;

class Program {

	/*
	Seems to be a binary search algorithm for sure
	Also seems to be a level-order BFS too ( utilize queue iteration ) 
	Input matrix always guaranteed one element
	Return {-1} if negative ints CAN NOT be converted to positives
	Seems recursive in nature now : akin to Uber - > multiple starting nodes can start the BFS level-order traversal ( many root nodes problem ) 
	ADJACENT ELEMENT => Cardinal directions restricted { N,S,E,W } 
	
	But how to pass level information too?

	Let us suppose that (m,n) can get very huge, BUT, fit in-memory
	Can we check if we even have a decrement or not here ( over the queue ) ?
	
	COMPLEXITY : 
	TIME = O(MN)
	SPACE = O(1)
	
	Using a queue for recursion : 
	WORST CASE TIME-SPACE
	TIME = O((MN)^2)
	SPACE = O(MN), supposing each element is a negative value
	
	Think : in a sense, the depth of a queue, OR, the depth of a stack, grows only so large too!
	
	
	*/
  public int minimumPassesOfMatrix(int[][] matrix) {
    // Write your code here.
    return -1;
  }
}
