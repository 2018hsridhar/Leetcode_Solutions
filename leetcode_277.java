/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

/*

URL = https://leetcode.com/problems/find-the-celebrity/

THOUGHT PROCESS 
Let n := length of the graph ( as well as the adjacency list length for all nodes ) 
Let nodes be zero-indexed, labeled from [0,n-1]
    E.g. Suppose n := 3 nodes : nodes are labeled as [0,1,2] here then
    
N is guarantede to be bounded by the closed interval of [2,100] => will fit in memory, will at least have two nodes too
All nodes know themselves ( e.g. the diagonal matrix of a graph LMAO)

We need a (n-1:1) partition here to find a celebrity as well

Challenge questino : max-# API calls can NEVER exceed (3 * n) here

*/

// Celeb matrix : 0 => unvisited, 1 => not a celeb, 2 => is a celeb
// two pointers cuz we incremenet ( source,dest ) in a cyclical manner too 

public class Solution_ extends Relation 
{
    public int findCelebrity(int n) 
    {
        int[] isCeleb = new int[n];
        for(int i = 0; i < n; ++i)
            isCeleb[i] = 0;
            
        int s = 0;
        int d = 1;
        
        int numNonCel = 0;
        while(d < n)
        {
            if(s == d) // identity here anways : incr d
                d++;
            else
            {
                boolean hasOutEdge = knows(s,d);
                if(hasOutEdge == false)
                {
                    if(isCeleb[d] != 1)
                    {
                        isCeleb[d] = 1;
                        numNonCel++;
                    }
                    d++;
                }
                else if ( hasOutEdge == true)
                {
                    if(isCeleb[s] != 1)
                    {
                        isCeleb[s] = 1;
                        numNonCel++;
                    }
                    s++;
                }
            }
        }
        // Test backwards case too : at final node
        
        
        // iterate over celeb array and return status here
        int celebNode = -1;
        for(int i = 0; i < n; ++i)
        {
            if(isCeleb[i] == 0)
            {
                celebNode = i;
                // VERY DUMB TEST CASE HERE !
                for(int j = 0; j < n; ++j)
                {
                    if(j != i && (!knows(j,i) || knows(i,j)))
                        return -1;
                }
            }
        }
        return celebNode;
    }
}


/*

Graph_1 [ G1 ] 

Can we keep incrementing both sides at max n times though! Take note of this!
Find API call boudns of either 1*n, 2*n too ( seems most reasonably here ) 
Akin to the binary watch problem in these type of increments

K(0,1) = F
K(0,2) = T
K(2,3) = F
K(2,4) = T
K(3,4)
K(4,4)
k(4,5) = T
K(5,5)
K(5,6) = index out of bounds : return here

Graph_2 [ G2 ] 

v       0   1   2   3
C(V)    F   F   F   F


K(0,1) = T
K(1,1) -> ignore
K(1,2) = T
K(2,2) -> ignore
K(2,3) = T
K(3,3) -> ignore
K(3,4) -> index out of bounds

Assuming I already tested preceding nodes here : test merely K(3,0)
K(3,0) = F => return -1
Can track false node counts

Graph_2 [ G3 ] 

v       0   1   2   3   4
C(V)    F   F   F       F

K(0,1) = T
K(1,1) -> IGNORE
K(1,2) = T
K(2,2) -> IGNORE
K(2,3) = T
K(3,3) -> IGNORE
K(3,4) = Fn
K(3,5) -> DNE
K(3,0) = F


*/
