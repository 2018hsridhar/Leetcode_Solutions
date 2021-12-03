/*
Let us make the problem somewhat more difficult by doing this in C ( test double-level indirection )

URL = https://leetcode.com/problems/surface-area-of-3d-shapes/
892. Surface Area of 3D Shapes

COMPLEXITY
Let N := grid dimensions ( it is a square ) 
Wait we do not easily get grid dimensions via pointers in C ... pass as a parameter!
TIME = O(8*N^2) = O(N^2)
SPACE = O(1) ( EXP & IMP ) 

The bottom S.A. and the top S.A. are easy to calculate
So too, the side S.A.
They will test you on handling of boundary indices
Interior is easiest to handle here : 8 adj elements

Grid len goes up to 50 max, but >= 1
Grid vals in closed interval [0,50]

TEST CASES 
(A) [[2]]
(B) [[1,1,1,1]]
(C)  [[2,2,2],[2,1,2],[2,2,2]]
(D) [[2,2,2],[2,4,2],[2,2,2]]
(E)

C lacks a "system.out.printf". It is just "printf" here
If it is one level, it truly is a single-dimensional array.

*/

// Wait why is gridColSize passed as a pointer here. We already have grid size ( it is nxn)
int surfaceArea(int** grid, int gridSize, int* gridColSize)
{
    int n = gridSize;
    // <i> = first level, <j> = second level
    int cardinals[4][2] = {{0,-1},{1,0},{0,1},{-1,0}};
    int sa = 0;
    // Check border cases
    for(int j = 0; j < gridSize; ++j)
    {
        int topRow = 0;
        int bottomRow = gridSize - 1;
        int topEl = *(*(grid+topRow) + j);
        int bottomEl = *(*(grid+bottomRow) + j);
        if(topEl != 0) sa+=topEl;
        if(bottomEl != 0) sa+=bottomEl;
    }
    
    // Check border cases
    for(int i = 0; i < gridSize; ++i)
    {
        int leftCol = 0;
        int rightCol = gridSize - 1;
        int leftEl = *(*(grid+i) + leftCol);
        int rightEl = *(*(grid+i) + rightCol);
        if(leftEl != 0) sa+=leftEl;
        if(rightEl != 0) sa+=rightEl;
    }
    
    // Do we have 20 here? Is easy to check
    // printf("Border surface area = %d\n", sa);

    // printf("Iterating over the grid\n");
    for(int i = 0; i < gridSize; ++i)
    {
        for(int j = 0; j < gridSize; ++j)
        {
            int* rowArray = *(grid+i);  
            // Expect confusion when they make the values the addresses here, in fact ( of elsewhere )
            int firstEl = *(rowArray+j);    // automatic conversions to word sizes take plpace here as well
            // printf("Val = %d\n",val);
            // int val = *(*(grid+i)+j); // memorizing formula does not help. Derive it!
            for(int k = 0; k < 4; ++k)
            {
                int* dir = *(cardinals+k);
                int cx = *(dir+0);  // dir[0]
                int cy = *(dir+1); // dir[1]
                // printf("firstEl = %d \t secondEl = %d\n", firstEl, secondEl);
                int childI = i + cx;
                int childJ = j + cy;
                // C is not as compiler safe as you want it to be
                if(0 <= childI && childI < n && 0 <= childJ && childJ < n)
                {
                    // printf("Have a valid child = (%d,%d)\n", childI, childJ);
                    // double dereference for a double pointer to get to the final value
                    // Number dereferences corresponds well here too ( grid *** -> three de-refs, couple of nestings )
                    int secondEl = *(*(grid+childI)+childJ);
                    if(firstEl <= secondEl)
                    {
                        continue;
                    }
                    // The run-time bug is here!
                    // Your comparison operator failing due to the semi-colon
                    else if(firstEl > secondEl)
                    {
                        sa += (firstEl - secondEl);
                    }
                }
            }
            // Value checking
            // Now check the tops, bottoms
            if(firstEl != 0)
            {
                sa += 2;
            }
        }
    }
    
    
    
    
    
    return sa;
}
