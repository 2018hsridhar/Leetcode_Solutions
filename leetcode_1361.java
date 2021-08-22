/*
THOUGHT PROCESS :
1361. Validate Binary Tree Nodes
URL = https://leetcode.com/problems/validate-binary-tree-nodes/

Topological Sort approach
-> naturally works with edge lists OR with in-degrees
-> quick to implement via Kahn's Algorithm too

DFS approach : 
-> how to know which node to perform the DFS from?
-> how to ensure we count how many times a node has been hit/visited set?
-> how to scale to disjoint graphs case ( refer to example two here ) 


- Propogate parent->child relationships as we traverse the tree ( highly akin to Tarjan's SCC algo ) 
- Check that we do not have a collision
- propogate values and check if we have the same value in one of these graphs too ( using two arrays for allocated space ) 
- [-1,1] validation seems fairly easy for checking (left-child, right-child) relationships too

ASSUMPTIONS
Always given at least one node : up to 10,000 nodes for in-memory storage
Both array lengths equal each other, and correspond to valid nodes [0,n-1] OR -1 [ non-existent case ] 


COMPUTATIONAL COMPLEXITY 

EDGE CASE TESTING : 




*/
class Solution 
{
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) 
    {
        
    }
}
