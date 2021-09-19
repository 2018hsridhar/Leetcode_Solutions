/*
THOUGHT PROCESSES : 
84. Largest Rectangle in Histogram
URL = {https://leetcode.com/problems/largest-rectangle-in-histogram/}

Must iterate over all rectangles in the worst case as well
Each bar is guaranteed a ( width = 1 ) here

No 0/negative ehight bars : guarantee within range of [1,10e4] = [1,10,000] in natural numbers
Can have [1,10e6] = [1,100,000] number of rectangles under consideration here as well


CASE TESTING : 
(A) [1,2,3,4,5]
(B) [5,4,3,2,1]
(C) [5,5,5,5,5]
(D) [1,2,1,2,1,2,1,2] => alternating : multiply by (lowest_height)*(#-rects)
(E) [2,1,5,6,2,3] => the provided test case == 10
(F) [3,4] => 6
(G) [5,3,4] => valley => (4*3) = 12 > (3*2) = 6 > (5*1)=5 [ max_individual_height] : 
(H) [1,4,3] => peak => (3*2) = 6 here { no maximation of individual heights }
(I) [1,4,1] => peak, but is an individual height=4 here ( 2nd rectangle ) 
(J) [6,2,1,4,3,2,4] => maximum is 8, but notice how 3 different rectangles are constructable from the min_height={4} @ the final index
(K)
(L)
(M)

COMPUTATIONAL COMPLEXITY
You got the better brute force solution here for sure
LET N := number of rectangles in our histogram
TIME = O(N*2)
SPACE = O(1)

Get a skeleton of this algorithm working, BUT, for smaller test cases. Not for the largest of test cases at least.
Can we get a BFS - Brute Force Solution - working here instead?


*/

class Solution 
{
    // All functions declared must possess a "return" statement, or some notion of function termination
    public int largestRectangleArea(int[] heights) 
    {
        int maxRectArea = heights[0]; // is "de-facto" value if heights array is a singleton element
        int n = heights.length;
        for(int i = 1; i < n; ++i)
        {
            int curHeight = heights[i];
            maxRectArea = Math.max(maxRectArea, curHeight); // check if current rectangle is greatest rect
            // Then check all other rectangles formed from current rect, minus a distance element as well
            int minRectH = heights[i];
            // We already have the j = i case here anyways ( minRectH ) 
            int width = 1;
            for(int j = i-1; j >= 0; --j)
            {
                minRectH = Math.min(minRectH, heights[j]);
                width++;
                int mySubRectArea = (minRectH * width);
                maxRectArea = Math.max(maxRectArea, mySubRectArea);                
            }
        }
        return maxRectArea;
            
    }
}
