/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
};
*/

/*

URL = https://leetcode.com/problems/construct-quad-tree/
427. Construct Quad Tree

Represent our grid with a Quad-Tree : return the root of it as well
Can assign node value to True/False when isLeaf = false -> both are valid answers, provided, consistency be maintained

Internal nodes have exactly four children and two atributes
    val
    isLeaf ( true -> is a leaf node :: false -> internal node has four children ) 
    
Constructed from a 2D area
based on grid having the same or different values
Divide grid into four subgrids and recurse

Complexity
Let M,N := dimensions of our array ( set to N, due to square property)
let L := number of levels = log_2(max(M,N) + 1)
Time = O(N^2*4^L)
Space = O(L) (implicit) O(1) ( explicit ) 

TEST BENCH
(A) [[0,1],[1,0]]
(B) ( *** base cases *** ) [[0]], [[1]]
(C) ( *** worst case : alt seq *** ) [[0,1,0,1],[1,0,1,0],[0,1,0,1],[1,0,1,0]]
(D) ( *** best case : consts *** ) [[0,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,0]]
(E) ( *** special case of non-perfect QUAD tree *** ) [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1]]

^ passing these 5 test cases well

Quad splits are definitely guaranteed : but we are guaranteed a square input here
n == 2^x [ 0 <= x <= 6 ] : meaning n can take on 7 values here 
{1,2,4,8,16,32,64}

*/


class Solution 
{
    public Node construct(int[][] grid) 
    {
        if(grid == null)
            return null;    // generally NOT liked!
        int m = grid.length;
        int n = grid[0].length;
        int lowR = 0;
        int highR = m - 1;
        int lowC = 0;
        int highC = n - 1;
        Node root = dac(grid, lowR, highR, lowC, highC);
        return root;
    }
    
    // Recursive calls create ONLY node objects ... nothing else
    public Node dac(int[][] grid, int lowR, int highR, int lowC, int highC)
    {
        Node root = new Node();
        if(lowR == highR && lowC == highC)
        {
            // base case of a root 
            // root.val = (boolean)grid[lowR][lowC]; // int not convertible to a boolean BTW
            if(grid[lowR][lowC] == 1)
                root.val = true;
            else
                root.val = false;
            root.isLeaf = true;
        }
        else
        {
            boolean quadPartition = false;
            int firstEl = grid[lowR][lowC];         // can always select ANY el in this algo
            // O(N^2), O(1) algo
            for(int i = lowR; i <= highR; ++i)
            {
                for(int j = lowC; j <= highC; ++j)
                {
                    int val = grid[i][j];
                    if(val != firstEl)
                    {
                        quadPartition = true;
                        break;
                    }
                }
            }
            
            // Leverage square grid property
            if(quadPartition == true)
            {
                // Expect a trip up @ these assertions as well
                int midR = lowR + (highR - lowR)/2;
                int midC = lowC + (highC - lowC)/2; 
                Node topLeft = dac(grid, lowR, midR, lowC, midC);
                Node topRight = dac(grid, lowR, midR, midC+1, highC);
                Node bottomLeft = dac(grid, midR+1, highR, lowC, midC);
                Node bottomRight = dac(grid, midR+1, highR, midC+1, highC);
                
                root.isLeaf = false;
                root.val = true;   // can set to any value ( in our case, we set to 1 here ) 
                root.topLeft = topLeft;
                root.topRight = topRight;
                root.bottomLeft = bottomLeft;
                root.bottomRight = bottomRight;
            }
            else    // No quad partition, BUT, is leaf, BUT, is not a single element
            {
                root.isLeaf = true;
                // value of grid ( always the first elem ) 
                if(firstEl == 1)
                    root.val = true;
                else
                    root.val = false;
            }
        }
        return root;
    }

}






