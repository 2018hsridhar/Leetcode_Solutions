class Solution 
{
    
    // Could sort strings as well, but let us aim to avoid a sort operation
    // Auxillary space leveraged be constant as well too
    // And thus, sorting will NOT work here as well too
    
    public boolean checkInclusion(String s1, String s2) 
    {
        boolean status = false;
        // So s2 can NEVER contain a permutation of s1, is sz(s1) > sz(s2)
        if(s1.length() > s2.length())
        {
            return false;
        }
        // Leverage fact of (s1,s2) consisting of ONLY lowercase English letters
        // Leverage fact that we must check ONLY s1's letters as well too
        int[] freqMapOne = new int[26];
        makeFreqMap(s1,freqMapOne);
        
        int[] freqMapTwo = new int[26];
        String sprime = s2.substring(0,s1.length());
        makeFreqMap(sprime,freqMapTwo);
        
        if(checkFreqMaps(freqMapTwo, freqMapOne))
        {
            return true;
        }
        // Oh it's substring ... NOT a subsequence. Subsequence def seemed WAY too easy here
        // Well we may be able to use a sliding window technique instead?
        // Since it's exactly s1, make it the length of s1 here as well
        
        // [1] Initiailize said sliding window
        // [2] Now compare the window AND check frequency maps as we go!
        int ptr1 = 0;
        int ptr2 = sprime.length();
        
        while(ptr2 < s2.length())
        {
            char nextElem = s2.charAt(ptr2);
            char toRemoveElem = s2.charAt(ptr1);
            int nextIdx = (int)(nextElem - 'a'); // so we do not know the ASCII where 'a' maps too here
            int remIdx = (int)(toRemoveElem - 'a'); // so we do not know the ASCII where 'a' maps too here
            freqMapTwo[nextIdx] += 1;
            freqMapTwo[remIdx] -= 1;
            if(checkFreqMaps(freqMapTwo, freqMapOne))
            {
                status = true;
                break;
            }
            ++ptr1;
            ++ptr2;
        }
        
        return status;
    }
    
    private boolean checkFreqMaps(int[] bigStringMap, int[] smallStringMap)
    {
        for(int i = 0; i < 26; ++i)
        {
            if(smallStringMap[i] != 0 && bigStringMap[i] < smallStringMap[i])
            {
                return false;
            }
        }        
        return true;
    }
    
    private void makeFreqMap(String s, int[] freqMap)
    {
        char[] c_arr = s.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char let = c_arr[i];
            int idx = (int)(let - 'a'); // so we do not know the ASCII where 'a' maps too here
            freqMap[idx]++;
        }
    }
}
