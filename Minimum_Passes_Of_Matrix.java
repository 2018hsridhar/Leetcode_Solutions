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
	
	public class triplet
	{
		public int i;
		public int j;
		public int l;
		
		public triplet(){};
		public triplet(int i, int j, int l)
		{
			this.i = i;
			this.j = j;
			this.l = l;
		}
	}
	
  public int minimumPassesOfMatrix(int[][] matrix) {
    // QUICKLY HANDLE THE BASE CASE HERE
		Queue<triplet> queue = new LinkedList<triplet>();
		if(matrix.length == 1 && matrix[0].length == 1)
			if(matrix[0][0] < 0) return -1;
			else return 0;
		
		for(int i = 0; i < matrix.length; ++i)
		{
			for(int j = 0; j < matrix[0].length; ++j)
			{
				if(matrix[i][j] < 0)
				{
					triplet newEl = new triplet(i,j,1);
					queue.add(newEl);
				}
			}
		}
		
		int numPasses = 0;
		int numUnexplorableRoots = 0;
		while(!queue.isEmpty())
		{
			System.out.printf("numUnExRoots = %d AND queue size = %d\n", numUnexplorableRoots, queue.size());
			if(numUnexplorableRoots == queue.size())
				return -1; 
			
			triplet root = queue.remove();
			boolean posN = hasPositiveNeighbor(root, matrix);
			System.out.printf("For (%d,%d), val = %d, posN = %s\n", root.i, root.j, matrix[root.i][root.j], posN);
			if(posN == true)
			{
				matrix[root.i][root.j] *= -1; // this will mess you down later : huge bug!
				// It percolates : { -4 => -5 => -6 }
				numUnexplorableRoots = 0;
			}
			else
			{
				root.l += 1;
				System.out.printf("Root.l = %d for val = %d\n", root.l, matrix[root.i][root.j]);
				numPasses = Math.max(numPasses, root.l);
				++numUnexplorableRoots;
				queue.add(root);
			}
		}
		
    return numPasses + 1;
  }
	
	public boolean hasPositiveNeighbor(triplet root, int[][] matrix)
	{
		int i = root.i;
		int j = root.j;
		if((i-1) >= 0 && matrix[i-1][j] > 0)
		{
			System.out.printf("here1\n");
			return true;
		}
		if((i+1) < matrix.length && matrix[i+1][j] > 0)
		{
			System.out.printf("here2\n");
			return true;
		}
		if((j-1) >= 0 && matrix[i][j-1] > 0)
		{
			System.out.printf("here3\n");
			return true;
		}
		if((j+1) < matrix[0].length && matrix[i][j+1] > 0)
		{
			System.out.printf("here4\n");
			return true;
		}
		
		return false;
	}
	
}
