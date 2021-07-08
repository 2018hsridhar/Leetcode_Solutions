/*

URL - https://leetcode.com/problems/binary-gap/
868. Binary Gap

The shift-and-1-ampersand technique : use to grab the value of a bit at a specific radix

        // [[1] First, check we have a "1". If so, set boolean flag and march onwards

Luckily, n is positive here - no worry about signage at the MSB field :-)
n reasonably bounded too : no worry about data overflow either ( but not bad to account for ) 
*/

class Solution
{
    public:
        int binaryGap(int n) 
        {
            if(n <= 1) return 0; // base case
            int offset = 31;
            bool hitOne = false;
            unsigned int curOnePos = UINT_MAX;
            int minimalGap = 0;
            while(offset >= 0) // will break if you use "unsigned int" here!
            {
                unsigned int radix = ( n >> offset) & 0x1;
                if(radix == 0x1 && hitOne==true )
                {
                    minimalGap = std::max(minimalGap, std::abs((int)(offset - curOnePos)));
                    curOnePos = offset;
                }
                if(radix == 0x1 && hitOne==false)
                {
                    hitOne = true;
                    curOnePos = offset;                    
                }
              --offset;
            }
            
            return minimalGap;
        }
};
