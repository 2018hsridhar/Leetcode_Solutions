/*

URL = https://leetcode.com/problems/count-subtrees-with-max-distance-between-cities/
1617. Count Subtrees With Max Distance Between Cities
Cities are 1-indexed ( numbered 1-> n)
Input is MST here ( it is a tree, but #-children vary ) 
    Trees have the uniqueness path invariant
    
Let subtree := a subset of cities/nodes, where for each x,y in nodes, x is reachable from y, and y is reachable from x.
    -> if the subsets differ, then the subtrees differ

COMPLEXITY
Let V := #-nodes
Let E := #-edges ( V^2 upper bound in a K_n connected graph ) 
Time = O()
Space = O() ( explicit ) O() ( implicit ) 

TEST CASES
(A) n = 2, edges = [[1,2]] ( *** base case *** ) 
    [1]
(B) n = 5, edges = [[]]
(C)
(D)

Ideas : Recursion, BFS/DFS, Visited Sets
Luckily, n does not get too large
All edge pairs also distinct too


*/
class Solution
{
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) 
    {
        int[] countSubtrees = new int[n];
        for(int d = 1; d < n; ++d)
        {
            
        }
        return countSubtrees;
    }
}
