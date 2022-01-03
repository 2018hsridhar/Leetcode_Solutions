Write a function that takes in a Binary Tree and returns its max path sum.

A path is a collection of connected nodes in a tree, where no node is 
connected to more than two other nodes; a path sum is the sum of the 
values of the nodes in a particular path.

Each BinaryTree node has an integer value, a left child node, and a 
right child node. Children nodes can either be BinaryTree nodes themselves 
or None / null.

Collection of connnect nodes in a tree
sum of all values, of the nodes, in a specific path

Path can be just one node, no edges

Test Case:

tree = 1
    /     \
   2       3
 /   \   /   \
4     5 6     7


18 // 5 + 2 + 1 + 3 + 7



6 + 3 + 7 = 16
can't have out degree > 2 per node

Tree diameter problem
Bottom-up solution : eval subtrees, than the main node

#-children of a node := #-choices at a specific node
max choice = 2 children
4 possible configs

	lps		rps		summation at top node
	<=0		<=0					val						( no children )
	>0		<= 0			lps + val				( no right child )
	<= 0	>0				rps + val				( no left child )
	>0		>0				lps + rps + val	

Values can be negative too
Just test magntidue
Child points to null -> return a 0

TEST CASES : 
- non-empty / non-null tree
- write back max path sums to node objects
- max depth sum
- assume not data overflow 

(A)
tree = 
			
			 \/
	     0					=> 16
    /     \
   -2      3			=> 16
 /   \    / \
-3    -5 6   7

	     10					=> a max path sum from this node if it incorporates the right, uses 17 { 10 -> 10 -> 7 }
    /     \
   -2     10			=> 17 { 10,7} 
 /   \    / \
-3    -5 6   7

(B)
tree = 
			 9					=> 9
    /     \
   -2     -3
 /   \    / \
-3    -5 -1   0

(C) 
			
				-2
			 /  \
			-3	-5
			
		lps = -3, rps = -5, val = -2
		maxPathSum expected = -2
		
(D) 
		
				 -50
			   / \
			 -1	  7
			 /     \
			6      -2
		 / \		 / \
		-7	-3	10  -9
		
		
		
Overwritten tree resembles : 

 			  60
         \
				 -35
			   / \
			 5	  15
			 /     \
			6       8
		 / \		 / \
		-7	-3	10  -9		
		
		
(E) 
		
				 -50
			   /
			 -1
			 /
			-2
		 / 
		7
		=> 7 ( leaf only ) 
		
Strategy : Recursion, Bottom-up propogation

COMPLEXITY 
Let N := #-nodes in the binary tree
Let H := height of the tree ( log_2(n) balanced, (n) skew ) 
TIME = O(N)
SPACE = O(H) ( call stack ) O(1) ( explicit ) 

PSEUDOCODE :

	// Kadane's algo
	// head recursion
	// Wrapper doable 
	// {lps,rps} := like straight lines
	
	int maxPath[0]
	
	void maxPathSum(tree, maxPath[0]):
		if tree points to null
			ret INT_MIN
		maxPathSum(tree.left, maxPath)
		maxPathSum(tree.right, maxPath)
		
		lps = INT_MIN
		if tree.left is not null
			lps += tree.left.val
			
		rps = INT_MIN
		if tree.right is not null
			rps += tree.left.val
			
		// 3 different values : 2 different signs ( +- )
		curMaxPathSum = max(tree.value, max(lps,rps))	// could be a problem : null cases zero out
		
		boolean addedToTestMax = false
		testMax = tree.value // max path sum at any node
		if(lps > 0)
			testMax += lps
			addedToTestMax = true
		if(rps > 0)
			testMax += rps
			addedToTestMax = true
			
		if addedToTestMax is true 
			curMaxPathSum = max(curMaxPathSum, testMax)

		maxPath[0] = max(maxPath[0], curMaxPathSum)
		
		// overwrite existing tree value AFTER max path computations
		tree.value = max(tree.value, max(tree.value + lps, tree.value + rps))
	
	


import java.util.*;

class Program {
  public static int maxPathSum(BinaryTree tree) 
	{
		int[] maxPath = new int[]{(Integer.MIN_VALUE>>1)};
		helper(tree, maxPath);
		return maxPath[0];
  }
	
	private static void helper(BinaryTree tree, int maxPath[])
	{
		if(tree == null)
			return;
		helper(tree.left, maxPath);
		helper(tree.right, maxPath);
		
		// Bit shift by 1 here
		int lps = (Integer.MIN_VALUE>>1);
		if(tree.left != null)
		{
			lps = tree.left.value;
		}
		
		int rps = (Integer.MIN_VALUE>>1);
		if(tree.right != null)
		{
			rps = tree.right.value;
		}		
		
		// 3 different values : 2 different signs ( +- )
		int curMaxPathSum = Math.max(tree.value, Math.max(lps,rps));
		boolean addedToTestMax = false;
		int testMax = tree.value;
		if(lps > 0)
		{
			testMax = testMax + lps;
			addedToTestMax = true;
		}
		if(rps > 0)
		{
			testMax = testMax + rps;
			addedToTestMax = true;
		}
			
		if(addedToTestMax)
		{
			curMaxPathSum = Math.max(curMaxPathSum, testMax);
		}
		
		maxPath[0] = Math.max(maxPath[0], curMaxPathSum);
		
		// overwrite existing tree value AFTER max path computations
		tree.value = Math.max(tree.value, Math.max(tree.value + lps, tree.value + rps));
	}
	

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}


Integer overflow due to add to tree sum each time



