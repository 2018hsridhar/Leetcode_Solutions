/*
455. Assign Cookies
URL = https://leetcode.com/problems/assign-cookies/

*/
class Solution 
{
    public int findContentChildren(int[] g, int[] s) 
    {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s); // can sort this too!
        
        int i = 0;
        int j = 0;
        while(i < g.length && j < s.length)
        {
            if(s[j] >= g[i])
            {
                ++count;
                ++j;
                ++i;
            }
            else
                ++j; // increment s : perhaps next cooking be good
        }
        
        return count;
    }
}
