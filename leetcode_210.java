/*

URL = https://leetcode.com/problems/course-schedule-ii/
210. Course Schedule II

THOUGHT PROCESS : 
- Utilizes standard topological sorting algorithm
- Graph may not be connected : can work with disjoint sets in a topological sort ( provided disjoint sets themselves form a DAG ) 
- 

DATA TYPES : 
- numCourses reasonable [ 1,2000]
- reasoabley num edges [ K_n bound ] 

COMPUTATIONAL COMPLEXITY : 

EDGE CASE TESTING : 
Ease to test graphs up to n = 7 nodes ( do not stray further than that ) 
Especially for generating permutations here
(0) The singleton node, zero edges - [0]
(1) n = 2 nodes graph : one edge [[1,0]] => output = [0,1]
(2) n = 3 nodes graph : [[2,1],[2,0],[1,0]] => output = [0,1,2]
(3) n = 4 nodes graph : [[3,0],[3,1],[3,2],[2,1],[2,0],[1,0]] => output = [0,1,2,3]
(4) n = 5 nodes graph : [[4,0],[4,1],[4,2],[4,3],[3,0],[3,1],[3,2],[2,0],[2,1],[1,0]] => output = [0,1,2,3,4]
(4.1) n = 6 nodes graph : [[5,0],[5,1],[5,2],[5,3],[5,4],[4,0],[4,1],[4,2],[4,3],[3,0],[3,1],[3,2],[2,0],[2,1],[1,0]] => output = [0,1,2,3,4,5]
(5) A SLL graph with n nodes, n-1 edges - [[5,4],[4,3],[3,2],[2,1],[1,0]] => [0,1,2,3,4,5,]
(6) A more randomized graph
-> generate as a subset from max edge graph relationships
-> Given n nodes, max #-of directed edges = (n-1) + (n-2) + ... + 1 = n(n-1)/2 = K_N. 
Easiest is to generate K_N graphs, and then delete random edges from those sets, to generate a test suite case barrage for this problem :-)




*/
class Solution 
{
    public int[] findOrder(int numCourses, int[][] prerequisites) 
    {
        int[] topologicalOrder = new int[10];
        return topologicalOrder;
    }
}
