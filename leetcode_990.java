/*

THOUGHT PROCESS ::

990. Satisfiability of Equality Equations
URL = https://leetcode.com/problems/satisfiability-of-equality-equations/
1. Consider using the union find or DFS algorithm ( common sets approach : seen in Tarjan's SCC algorithm ) 
2. Do we need to account for self loops here too? YES!
Equations guaranteed to be in count of [1,500] - no need for production ENV null/0-length arrays handling :-)

Make sure to build up graph structure ( accounting for all nodes ) before performing the algorithm!
May need to account for a VISITED set here too
Model graph connections based on whether there is an equation! 
Then iterate over non-equation cases, and perform DFS/Union-Find and assert that value in that node does not match too

Utilize negative or 0 values to denote a lack of a value
Natural numbers are an infinite set : keep incrementing thy counter variable too



COMPUTATIONAL COMPLEXITY : 
TIME = O(|V^2|)
SPACE = O(|V^2|) for the adjacency matrix
Let V := number of Vertices and let E := number of edges total ( worst case is that E = V^2 ) 
Let N := total number of equations ( where E := number of working equations too ) 


EDGE CASE TESTING : 
1. Singletons { a==b, a != b} - TRUE
2. Isolated sets { a!=b, b!=c,c!=d,d!=e,e!=f} - TRUE
3. One fully connected set { a==b,b==c,c==d,d==e,e==f} - TRUE
4. A mix of connected and isolated sets { a==b,b==c,d!=e} - TRUE
5. A test that breaks {"a==b","b!=c","c==a"} = FALSE
6. A self loop test { c==c } = TRUE
7. A self-loop not equals case { a!=a } AND { a==a, a != a } 


1. ["a==b","a!=b"]
2. ["a!=b","b!=c","c!=d","d!=e","e!=f"]
3. ["a==b","b==c","c==d","d==e"]
4. ["a==b","b==c","d!=e"]
5. ["a==b","b!=c","c==a"]
6. ["c==c"]
7. ["a!=a"], ["a==a","a!=a"]

8. ["a==b","b!=c","c==a"]
9. ["a==b","c==d","a==c","a!=d"]

Equations [i][0|3] guaranteed to be a lower-letter only : a (26x26) at maximum element matrix is possible only!
Each time you perform DFS, a visited set is desired ( to avoid any self-loops ) -> but make sure to update this, for each equation executed

*/



class Solution 
{
    int[][] adjMat = new int[26][26];

    public boolean equationsPossible(String[] equations) 
    {
        // [0] Set up space for adjacency matrix here
        // Technically a constant operation ( O(1)) space 
        for(int i = 0; i < adjMat.length; ++i)
            for(int j = 0; j < adjMat[0].length; ++j)
                adjMat[i][j] = -1;

        // [1] Iterate over all equations where EQUALITY holds true 
        for(int i = 0; i < equations.length; ++i)
        {
            String eq = equations[i];
            char[] eqCh = eq.toCharArray();
            int src = (int)(eqCh[0] - 'a');
            int dst = (int)(eqCh[3] - 'a');
            if(eqCh[1] == '=')
            {
                adjMat[src][dst] = 1;
                adjMat[dst][src] = 1;
            }
        }
        
        
        // [2] Iterate over all equations where INEQUALITY holds true 
        for(int i = 0; i < equations.length; ++i)
        {
            String eq = equations[i];
            char[] eqCh = eq.toCharArray();
            int src = (int)(eqCh[0] - 'a');
            int dst = (int)(eqCh[3] - 'a');
            if(eqCh[1] == '!')
            {
                if(dfs(src,dst) == true)
                {
                    return false;                    
                }
            }
        }
        
        return true;
    }

    // Runs DFS only when equation does not hold true ( inequality case ) 
    public boolean dfs(int src, int dst)
    {
        if(dst == src)
            return true; // already known : can't have a != a anyways ( even if a==a exist ) !
        Set<Integer> visited = new HashSet<Integer>(); // always make a fresh "deep" copy here
        helper(src,dst,visited);
        if(visited.contains(dst))
            return true;
        return false;
    }
    
    // DFS backtracking -> dfs here returns a boolean : how to ensure each case is under consideration too!
    // Booleans really don't work that well in a recursive DFS !
    /*
    public boolean dfsHelper(int src, int dst, Set<Integer> visited)
    {
        System.out.printf("(src,dst) = (%s,%s)\n", (char)(src+'a'), (char)(dst+'a'));
        visited.add(src); // CONSIDER "parent" visited before exploring children here      
        for(int i = 0; i < 26; ++i)
        {
            if(adjMat[src][i] == 1)
            {
                System.out.printf("(src,i) = (%s,%s)\n", (char)(src+'a'), (char)(i+'a'));
                if(i == dst)
                {
                    System.out.printf("i = %d\tdst=%d\n", i, dst);
                    return true;
                }
                if(!visited.contains(i))
                {
                    return dfsHelper(i, dst, visited);                    
                }
            }
        }
        return false;
    }
    */

    public void helper(int src, int dst, Set<Integer> visited)
    {
        visited.add(src); 
        for(int i = 0; i < 26; ++i)
        {
            if(adjMat[src][i] == 1)
            {
                if(!visited.contains(i))
                    helper(i, dst, visited);                    
            }
        }
    }
    

}
