/*
THOUGHT PROCESS : 

1104. Path In Zigzag Labelled Binary Tree
URL = https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/

Always ask if each node, in a binary tree, is uniquely labeled or not ( provided we know if the bTree is also labeled ) 
Factorization ( into {2,3} ) can guarantee convergence to the value of 1 here

Easiest way to think of problem : utilize 2-power sequence
len = 6 [ 1,2,4,8,16,32 ]
len = 7 [ 1,2,4,8,16,32,64]
len = 8 [ 1,2,4,8,16,32,64,128]
=> Quick to built intuition from here!


Edge cases
1
2
3
127
128
17
14
1000000

Computational Complexity : 
Time = O(log(N)) + O(N) + O(N/2) = O(N) [ break down into three segments, per loop and for list reversal ( assume optimized here ) ]
Space = O(N) [ due to array list ]

Better idea : use the reverse iterator?

*/
class Solution 
{
    public List<Integer> pathInZigZagTree(int label) 
    {
        // [1] Generate the binary sequence ( divide by 2 sequence ) until you hit 1 ( you are guaranteed to hit 1, as you hit {2,3} at some point )
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        while(label != 1)
        {
            traversal.add(label);
            label /= 2;
        }
        // Add label ( equal to 1 now! ) 
        traversal.add(label);
        
        // [2] Reverse iterate over this arrayList now : convert to array
        Collections.reverse(traversal);
        int n = traversal.size();
        
        // [3] Perform computation on array, based on parity of traversal size
        if(n % 2 == 0) // len = 6 [ 1,2,4,8,16,32 ]
        {
            for(int i = 2; i < n; i += 2)
            {
                int replVal = (((int)Math.pow(2,i) * 3 ) - 1 ) - traversal.get(i);
                traversal.set(i,replVal);
            }
        }
        else if ( n % 2 == 1)
        {
            for(int i = 1; i < n; i += 2)
            {
                int replVal = (((int)Math.pow(2,i) * 3 ) - 1 ) - traversal.get(i);
                traversal.set(i, replVal);
            }
        }
        
        return traversal;
    }
}
