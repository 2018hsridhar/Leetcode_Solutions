/*
THOUGHT PROCESSES : 
84. Largest Rectangle in Histogram
URL = {https://leetcode.com/problems/largest-rectangle-in-histogram/}

Must iterate over all rectangles in the worst case as well
Each bar is guaranteed a ( width = 1 ) here

No 0/negative ehight bars : guarantee within range of [1,10e4] = [1,10,000] in natural numbers
Can have [1,10e6] = [1,100,000] number of rectangles under consideration here as well


CASE TESTING : 
(A) {1,2,3,4,5}
(B) {5,4,3,2,1}
(C) {5,5,5,5,5}
(D) {1,2,1,2,1,2,1,2} => alternating : multiply by (lowest_height)*(#-rects)
(E) {2,1,5,6,2,3} => the provided test case == 10
(F) {3,4} => 6
(G) {5,3,4} => valley => (4*3) = 12 > (3*2) = 6 > (5*1)=5 [ max_individual_height] : 
(H) {1,4,3} => peak => (3*2) = 6 here { no maximation of individual heights }
(I) {1,4,1} => peak, but is an individual height=4 here ( 2nd rectangle ) 
(J)
(K)
(L)
(M)

COMPUTATIONAL COMPLEXITY
LET N := number of rectangles in our histogram
TIME = 
SPACE = O(N)



*/

class Solution 
{
    // All functions declared must possess a "return" statement, or some notion of function termination
    public int largestRectangleArea(int[] heights) 
    {
        
    }
}
