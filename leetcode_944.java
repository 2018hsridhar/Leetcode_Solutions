class Solution {
    public int minDeletionSize(String[] strs) 
    {
        int minDels = 0;
        int cLen = strs[0].length();
        for(int c = 0; c < cLen; ++c)
        {
            for(int r = 0; r < strs.length - 1; r++)
            {
                int lPtr = r;
                int rPtr = r + 1;
                char topLet = strs[lPtr].charAt(c);
                char botLet = strs[rPtr].charAt(c);
                if(topLet > botLet) {
                    minDels++;
                    break;
                }
                ++lPtr;
                ++rPtr;
            }
        }
        return minDels;
        
    }
}
