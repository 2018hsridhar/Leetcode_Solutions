/*
URL = {https://leetcode.com/problems/24-game/}
679. 24 Game

THOUGHT PROCESS : 

PEMDAS order here for sure during eval steps
Operator is binary : not unary ( ok ) 
Do not concatenate numbers :- each number stands alone as is too
Do not have to return the actual expression => just whether we can arrive unto 24 here

1. Division operator is NOT integer division -> it is REAL division here
2. Evaluation order also matters as well too -> take note of this!

Let us set forth an upper boudn on number of nodes as well
4 * 24 = 96 total nodes in our expression eval tree
L1 => 4*3 = 12 nodes ( 3 ops left )
L2 => 4*2 = 8 nodes ( 2 ops left ) 
L3 => 4*1 = 4 nodes ( 1 op left)

It is recursion / backtracking as usual :-)
Card values bouded [0-9] and num_cards = 4 as well

   // Fractions are allowed here -> .not pure integers boyo 

TEST CASES
(A) [6,1,3,4] => (6, (1, (3,4,/), -), /)
    Take note : (a-b) != (b-a) AND (a/b) != (b/a)
(B) [4,1,2,3] => (4, (1, (2, 3, /), -), /)
(C) [1,1,2,9] => ((9,1,-), (2,1,+), *) => not just a single rooted tree here
(D)
(E)
(F)
(G)
(H)

Backtracking EXPRS is not intuitive here
*/

class Solution
{
    // */-+ = "operator string"?
    boolean canGet24 = false;
    public boolean judgePoint24(int[] cards)
    {
        canGet24 = false;
        double[] myCards = new double[4];
        for(int i = 0; i < cards.length; ++i)
            myCards[i] = (double)cards[i];
        for(int i = 0; i < cards.length; ++i)
            solveFromRooted(myCards, myCards[i], i);

        
        // Suppose everything is a double => I think math eval should still work here?
        // double x = 3;
        // double y = x/(double)4;
        // double z = (double)6/(1-y);
        // System.out.printf("x = %f\ty=%f\tz=%f\n", x, y, z);
        
        return canGet24;
    }
    
    // Why not just use a set in lieu of a visited boolean array instead? 
    // Lookup there is O(1) as well
    public void solveFromRooted(double[] myCards, double rootCard, int visitIdx)
    {
        int rootDepth = 1;
        boolean[] parent_visited = new boolean[4];
        for(int i = 0; i < parent_visited.length; ++i)
            parent_visited[i] = false;
        parent_visited[visitIdx] = true;
        double curComp = rootCard;
        
        // ADD
        for(int i = 0; i < 4; ++i)
        {
            if(parent_visited[i] == false)
            {
                // make child visited
                boolean[] child_visited = new boolean[4];
                 for(int j = 0; j < parent_visited.length; ++j)
                     child_visited[j] = parent_visited[j];
                child_visited[i] = true;
                double nextComp = curComp + myCards[i];
                dfs(myCards, child_visited, nextComp, rootDepth + 1);
            }
        }
        
        // SUB
        for(int i = 0; i < 4; ++i)
        {
            if(parent_visited[i] == false)
            {
                // make child visited
                boolean[] child_visited = new boolean[4];
                 for(int j = 0; j < parent_visited.length; ++j)
                     child_visited[j] = parent_visited[j];
                child_visited[i] = true;
                double nextComp = curComp - myCards[i];
                double otherComp = myCards[i] - curComp;
                dfs(myCards, child_visited, nextComp, rootDepth + 1);
                dfs(myCards, child_visited, otherComp, rootDepth + 1);
            }
        }
        
        // MULT
        for(int i = 0; i < 4; ++i)
        {
            if(parent_visited[i] == false)
            {
                // make child visited
                boolean[] child_visited = new boolean[4];
                 for(int j = 0; j < parent_visited.length; ++j)
                     child_visited[j] = parent_visited[j];
                child_visited[i] = true;
                double nextComp = curComp * myCards[i];
                dfs(myCards, child_visited, nextComp, rootDepth + 1);
            }
        }
        
        // DIV
        for(int i = 0; i < 4; ++i)
        {
            if(parent_visited[i] == false)
            {
                // make child visited
                boolean[] child_visited = new boolean[4];
                 for(int j = 0; j < parent_visited.length; ++j)
                     child_visited[j] = parent_visited[j];
                child_visited[i] = true;
                double nextComp = curComp / myCards[i];
                double otherComp = myCards[i] / curComp;
                dfs(myCards, child_visited, nextComp, rootDepth + 1);
                dfs(myCards, child_visited, otherComp, rootDepth + 1);
            }
        }
    }
    
    // Recursive signature : track the following in the local of each function call as well
    // 1. Current computed value 2. Visited cards 3. cards available 4. depth level ( must be 4 here )
    // visited cards must be local, BUT nonetheless, this is just 4 cards at max, so in-mem consumption should skew nominal!
        // Bound the depth = 3 count : should not be equal to 48 => should be far far less here!
    // Need to track visited cards in 
    // How the heck are we getting a factor of (x8) from d=2 TO d=3
    // Or a factor of (x24) from d=2 to d=3? the heck!
    
    // 6/(1-(3/4)) needs to happen here
    // But how does this even happen, is the actual issue too!
    // We're not just restricted to one type of operation ( can have all operations be divisors here too ) 
    // 1 - 3  = -2 : -2/4 = ???
    // 3/4 = 0.25, but not 1 - {} will ever occur! ( operand can be on both sides of an operator ) 
    // a+b=b+a, but (a-b) != (b-a)
    // Similariyl, a*b = b*a, but (a/b) != (b/a)
    // Can have 8 children per subtraction/per div operation as well!
    
    public void dfs(double[] myCards, boolean[] parent_visited, double curComp, int depth)
    {
        // if(depth == 4)
            // System.out.printf("At depth = %d, candid = curComp = %f\n", depth, curComp);
        if(depth == 4 && curComp == (double)24)
        {
            canGet24 = true;
            return;
        }
        // Is failure due to this terminating condition?
        else if ( depth == 4 && curComp != (double)24 ) 
            return;
        else
        {
            // ADD
            for(int i = 0; i < 4; ++i)
            {
                if(parent_visited[i] == false)
                {
                    // make child visited
                    boolean[] child_visited = new boolean[4];
                     for(int j = 0; j < parent_visited.length; ++j)
                         child_visited[j] = parent_visited[j];
                    child_visited[i] = true;
                    double nextComp = curComp + myCards[i];
                    dfs(myCards, child_visited, nextComp, depth + 1);
                }
            }

            // SUB
            for(int i = 0; i < 4; ++i)
            {
                if(parent_visited[i] == false)
                {
                    // make child visited
                    boolean[] child_visited = new boolean[4];
                     for(int j = 0; j < parent_visited.length; ++j)
                         child_visited[j] = parent_visited[j];
                    child_visited[i] = true;
                    double nextComp = curComp - myCards[i];
                    double otherComp = myCards[i] - curComp;
                    dfs(myCards, child_visited, nextComp, depth + 1);
                    dfs(myCards, child_visited, otherComp, depth + 1);
                }
            }

            // MULT
            for(int i = 0; i < 4; ++i)
            {
                if(parent_visited[i] == false)
                {
                    // make child visited
                    boolean[] child_visited = new boolean[4];
                     for(int j = 0; j < parent_visited.length; ++j)
                         child_visited[j] = parent_visited[j];
                    child_visited[i] = true;
                    double nextComp = curComp * myCards[i];
                    dfs(myCards, child_visited, nextComp, depth + 1);
                }
            }

            // DIV
            for(int i = 0; i < 4; ++i)
            {
                if(parent_visited[i] == false)
                {
                    // make child visited
                    boolean[] child_visited = new boolean[4];
                     for(int j = 0; j < parent_visited.length; ++j)
                         child_visited[j] = parent_visited[j];
                    child_visited[i] = true;
                    double nextComp = curComp / myCards[i];
                    double otherComp = myCards[i] / curComp;
                    dfs(myCards, child_visited, nextComp, depth + 1);
                    dfs(myCards, child_visited, otherComp, depth + 1);
                }
            }
        }
    }
    
    
}
