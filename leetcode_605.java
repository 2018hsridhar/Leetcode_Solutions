/*

URL = https://leetcode.com/problems/can-place-flowers/
605. Can Place Flowers

THOUGHT PROCESS : 

Ideal performance : [T,S] = [O(N), O(1)] linear-scanning
Integer array : (0,1) only : valid input
Flower length can equal zero too
Ensure no-adjaent-flowers rule holds

Typical alternating sequence problem
Must check cell to the left and to the right equal zero here ( with exception of leftmost boudn and rightmost bound )

Sometimes, you can actually place the most flowers



*/


class Solution 
{
    public boolean canPlaceFlowers(int[] flowerbed, int n) 
    {
        if(n == 0) return true;
        if(flowerbed.length == 0) return false;
        else if ( flowerbed.length == 1)
        {
            if(flowerbed[0] == 0 && n == 1) return true;
            return false;
        }
        
        int count = 0;
        for(int i = 0; i < flowerbed.length; ++i)
        {
            if(i == 0 && flowerbed[0] == 0 && flowerbed[1] == 0)
            {
                flowerbed[0] = 1;
                ++count;
            }
            else if ( 1 <= i && i <= flowerbed.length - 2 ) 
            {
                if(flowerbed[i] == 0 && flowerbed[i-1] == 0 && flowerbed[i+1] == 0)
                {
                    flowerbed[i] = 1;
                    ++count;
                 }
            }
            else if ( i == flowerbed.length - 1)
            {
                if(flowerbed[i] == 0 && flowerbed[i-1] == 0 )
                {
                    flowerbed[i] = 1;
                    ++count;
                }
            }
            
        }
        
        return ( count >= n );
    }
}
