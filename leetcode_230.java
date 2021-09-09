/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/*
Utiilize the min heap as we return the SMALLEST element in the tree
Different order traversal : right=>root=>left
URL = https://leetcode.com/problems/kth-smallest-element-in-a-bst/
230. Kth Smallest Element in a BST

You need a maxHeap : not a minHeap here BTW


Complexity
Time = O(Nlog(k))
Space = O(k)
N := number of nodes in the tree
k := size of the heap
Note that 1 <= k <= n, and in most practical applications, k << n
*/

class Solution 
{
    public int kthSmallest(TreeNode root, int k)
    {
	    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override 
			public int compare(Integer a, Integer b) {
				return b - a; 
			}
		}); 
        fillHeap(maxHeap, root, k);
        return maxHeap.poll();
    }
    
    /*
        Assume right has greatest and fill from there
        Then fill at parent root
        Then left, as those are STRICTLY smaller
    */
    public void fillHeap(PriorityQueue<Integer> maxHeap, TreeNode root, int k)
    {
        if(root.right != null)
            fillHeap(maxHeap, root.right, k);
        
        if(maxHeap.size() >= k)
            maxHeap.poll();
        maxHeap.add(root.val);
        
        if(root.left != null)
            fillHeap(maxHeap, root.left, k);
    }
}
