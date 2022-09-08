/*
https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/
1530. Number of Good Leaf Nodes Pairs

*/

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
 Need to wrap this, as we return both (a) the counts and (b) the distances 
 In our bottom-up traversal of the tree.
 Distances are from the top of the tree ( or the leaf node, if at the leaf node ).
 Plus packages always start at a leaf node too ; not to feel worried since created only there!
*/
public class myStats
{
    public int countPairs;
    public ArrayList<Integer> curDistances; // Java lacks "dynamic array" support
    public myStats() {}
}


/*
TEST CASES :

(A) Basic tree -> 3 nodes ( left-center-right )
    [1,2,3,null,null,null,null]
    3
    countPairs = 1
(B) [1,2,null,null]
    1
    countPairs = 0
(C) [1,null,2,null,null]
    1
    countPairs = 0
(D) [1,2,3,null,null,null,null]
1
countPairs = 0
(E) [1,2,3,4,5,null,null]
2
countPairs = 2
(E) 
[1,2,null,3,null,4,null,5,null]
1
countPairs = 0

Difficulties in problem
(1) Determine if you can avoid the sort step or not - via zipper merge.
(2) Determining how to encapsulate your data of interest as your proceed up the tree again!

*/
class Solution {
    
    // array initialization requires at least one element
    // How to initialize an array at a leaf node too?
    public int countPairs(TreeNode root, int distance) 
    {
        myStats myStats;
        int numGoodPairs = 0;
        if(root == null) { 
            return 0;
        }
        myStats = getLeavesAndDistances(root,distance);
        return myStats.countPairs;
    }
    
    // Helper functoin is meant to obtain leaf information.
    // Assume we add left to right as well
    // Check if something is a leaf expedites the processing too.
    // Wait . . . is this easier if I just write distance(leaf, itself) = 0 too?
    // But do not get tripped up on the single node case as well! CAUTION!
    public myStats getLeavesAndDistances(TreeNode root, int distance)
    {
        // Step #1 : GET INFORMATION ABOUT THE SUBTREES
        // The real diff is whether we have distances data or not, TBH -> simplify the codebase later!
        if(root == null || isLeaf(root)) {
            myStats data = new myStats();
            ArrayList<Integer> distances = new ArrayList<Integer>();
            if(isLeaf(root))
                distances.add(0);
            data.countPairs = 0;
            data.curDistances = distances;
            return data;
        } else if ( root.left != null && root.right == null) {
            myStats lstData = getLeavesAndDistances(root.left, distance);
            for(int i = 0; i < lstData.curDistances.size(); ++i) {
                lstData.curDistances.set(i, lstData.curDistances.get(i) +1);
            }
            return lstData;
        } else if ( root.left == null && root.right != null) { 
            myStats rstData = getLeavesAndDistances(root.right, distance);
            for(int i = 0; i < rstData.curDistances.size(); ++i) {
                rstData.curDistances.set(i, rstData.curDistances.get(i)+1);
            }
            return rstData;
        } else {
            // Provided the merge is done correctly, at a given level, we are good to go!
            myStats data = new myStats();
            myStats lstData = getLeavesAndDistances(root.left, distance);
            myStats rstData = getLeavesAndDistances(root.right, distance);
            
            // (+1) On the incoming data sets here before zipper merge step too
            for(int i = 0; i < lstData.curDistances.size(); ++i) {
                lstData.curDistances.set(i, lstData.curDistances.get(i)  +1 );
            }            
            for(int i = 0; i < rstData.curDistances.size(); ++i) {
                rstData.curDistances.set(i, rstData.curDistances.get(i)  +1 );
            }

            // Step #2 : Compute information and propogate back up the ancestors.
            // Exec the two sum algorithm on the sets ( but will the sets be sorted here )?
            data.countPairs += ( lstData.countPairs + rstData.countPairs);
            data.countPairs += twoSumPairings(lstData.curDistances, rstData.curDistances, distance);
            data.curDistances = merge(lstData.curDistances, rstData.curDistances);
            return data;        
       }   
    }

    public ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right)
    {
        ArrayList<Integer> merged = new ArrayList<Integer>();
        int lPtr = 0;
        int rPtr = 0;
        while(lPtr < left.size() && rPtr < right.size()) {
            if(left.get(lPtr) <= right.get(rPtr)) {
                merged.add(left.get(lPtr));
                ++lPtr;
            }
            else {
                merged.add(right.get(rPtr));
                ++rPtr;
            }
        }
        while(lPtr < left.size()) {
            merged.add(left.get(lPtr));
            ++lPtr;
        }
        while(rPtr < right.size()) {
            merged.add(right.get(rPtr));
            rPtr++;
        }
        return merged;
    }
    
    // two sum method on array lists : compute number of good pairings
    public int twoSumPairings(ArrayList<Integer> left, ArrayList<Integer> right, int distance)
    {
        int twoSumCount = 0;
        int i = 0;
        while(i < left.size())
        {
            for(int j = 0; j < right.size(); ++j)
            {
                int testSum = left.get(i) + right.get(j);
                if( testSum <= distance) {
                    twoSumCount += 1;
                } else {
                    break;
                }
            }
            i += 1;
        }
        return twoSumCount;
    }
    
    // Easy to write up a boolean function to test if a node is a leaf or not a leaf.
    // Such a node has at least one child : left child or right child.
    public boolean isLeaf(TreeNode root)
    {
        if(root == null) return false;
        return ( root.left == null && root.right == null );
    }
    
}
