
class Solution {
    public int strStr(String haystack, String needle) 
    {
        if(needle == "")
            return 0;
        int idxHit = -1;
        
        char[] h_arr = haystack.toCharArray();
        char[] n_arr = needle.toCharArray();
        
        for(int i = 0; i < h_arr.length; ++i)
        {
            // a hit of equal characters.
            // now run a 2nd ptr, and see if they match
            if(h_arr[i] == n_arr[0])
            {
                idxHit = i;
                int j = i+1;
                while((j - i) < n_arr.length && j < h_arr.length) // note (2x) indexes here, too, for bounds
                {
                    if(h_arr[j] != n_arr[j - i])
                    {
                        idxHit = 0;
                        break;
                    }
                }
                if((j-i) == n_arr.length) // we finished processing the needle, and we found it
                {
                    return idxHit;
                }
            }
        }
        
        return idxHit;
    }
}



