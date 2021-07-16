/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/*

339. Nested List Weight Sum
URL = https://leetcode.com/problems/nested-list-weight-sum/

THOUGHT PROCESSES : 


Data type values can range from [-100,100]
Max depth of tree/any integer <= 50 ( reasonable for a level-order BFS/DFS traversal)
Nested list lengths bounded by [1,50] -> no degenerate/0-len/null nested lists :-)

Why are nested sets/nested lists common place asa  data structure
Utilize recursive method, and pass in depth as state needing during each computation,
-> utilizies implict call-stack space for function calls  : but no additional space too

COMPUTATIONAL COMPLEXITY ANALYSIS : 
Time = O(n)*O(b^d) = O(nb^d) for (time_per_call)*(#-calls_max_at_each_recursive_level)
Space = O(N) = number of recursive calls placed on the call stack!
-> Recursive calls placed on the call stack!

Better : let N = number of nested elements. Time = O(N)
- recursive function called ONCE per each nested list
- iteratives over all nested elements directly inside the list : N loop iterations
O(N)+O(N) = O(2*N) = O(N)



EDGE CASES : 
(1) No real values - [0]
(2) All values at first level - [ 1, 2, 3 ] : product = 6
(3) All valuees at last level - [[[ 1, 2, 3 ]]] : depth = 3 per value, so product=18
(4) Variations in values and nesting - [1,[9],[[8]],[[[6,3,9]]]]
(5) Max nested - [[[[[[[[[[[[[[[[[[[[20]]]]]]]]]]]]]]]]]]]] ( 20 * 20 = 400 ) !
(6) Negative values case - [-1, -2, -3] : make sure signage stays on as expected too

*/


class Solution {
    public int depthSum(List<NestedInteger> nestedList) 
    {
        int dSum = 0;
        dSum = helper(nestedList, 1);
        return dSum;    
    }
    
    public int helper(List<NestedInteger> nestedList, int depth)
    {
        int prod_of_level = 0;
        // Iterates on all entires of DFS func = O(N)
        for(NestedInteger x : nestedList)
        {
            if(x.isInteger())
                prod_of_level += (depth * x.getInteger());
            else
                prod_of_level += helper(x.getList(), depth + 1); 
            // do not prefix ( ++depth ) and change across scope of parent caller. Set value for child callee though
        }
        return prod_of_level;
    }
    
}
