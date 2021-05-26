
/*

    OTHER LINKS : 
    1. https://en.wikipedia.org/wiki/Gift_wrapping_algorithm
    Is known as the Jarvis March in the 2D case
    Performs well when <n> and <h> are minimized
    
    Handling case of three collinear points ( assuming they are in general position ) 
    Report extreme points or all points ? Extreme = are strictly vertexes of the convex hull
    
    Convergence is establishable inductively : we know we must hit, or not, the (n-1) points away
    Intuition : tauting a string about a set of points on a 2D-plane
    
    Any while loop requires a breakpoint : otherwise, can not escsape out of infinite iteration

    THOUGHT PROCSESES 
*/


class Solution {
    public int[][] outerTrees(int[][] trees) 
    {

        // ESTABLISH the left-most points
        int[] leftMostPoint = trees[0];
        int leftMostPointIdx = 0;
        for(int i = 1; i < trees.length; ++i )
        {
            int[] curPoint = trees[i];
            if(curPoint[0] < leftMostPoint[0])
            {
                leftMostPointIdx = i;                
                leftMostPoint = curPoint;
            }
        }
        
        
        // System.out.printf("Left Most Point = [%d,%d]\n", leftMostPoint[0], leftMostPoint[1]);
        // You may wrap aroudn an already existing point! do take note of this!
        // Difficulty in code : cross product needs 2 lines/3 points, and in first iteration, you work with only  2 points
        // first line will actually be with itself!
        
        
        // First, get pseudo code out
        // Then focus on figuring out how to code the algo!
        
        
        // We know the hull bound is the length of the trees, but still use an array list here
        ArrayList<int[]> jarvisMarchPoints = new ArrayList<int[]>();
        
        int[][] results = new int[trees.length][2];
        while(true)
        {
            int[] initPoint = leftMostPoint;
            int[] nextLMP;
            for(int i = 1; i < trees.length; ++i)
            {
                nextLMP = trees[i];
                int[] curPoint = trees[i]
                int crossProd = crossProduct(precPoint, curPoint, nextLMP);
                if(crossProd < 0)
                {
                    jarvisMarchPoints.add(nextLMP);
                    // Have a match here!
                }
                else if ( crossProd == 0 )
                {
                    
                }
                else
                    continue; // point is not to the left of the current axis
                
            }
            
            if(equalPoints(nextLMP, leftMostPoint))
                break;
            
            initPoint = nextLMP; // continue algorithm
        }
        
        for(int i = 0; i < jarvisMarchPoints.size(); ++i)
        {
            int[] myPoint = jarvisMarchPoints.get(i);
            results[i][0] = myPoint[0];
            results[i][1] = myPoint[1];
        }
        return results;
    }
    
    // \textit{ab} \cross \textit{ac}
    public int crossProduct(int[] a, int[] b, int[] c)
    {
        int x1 = a[0]-b[0];
        int x2 = a[0]-c[0];
        int x3 = a[1]-b[1];
        int x4 = a[1]-c[1];
    }
    
    // check which point is closer to point a : {b,c}
    // return the point
    public int[] distance(int[] a, int[] b, int[] c)
    {
        double dist_ab = Math.sqrt(Math.pow(a[0] - b[0],2) + Math.pow(a[1] - c[1],2));
        double dist_ac = Math.sqrt(Math.pow(a[0] - c[0],2) + Math.pow(a[1] - c[1],2));
        
        if(dist_ac >= dist_ab )
            return c;
        return b;
    }
    
    public boolean equalPoints(int[] p1, int[] p2)
    {
        if(p1[0] == p2[0] && p1[1] == p2[1])
            return true;
        return false;
    }
    
}
