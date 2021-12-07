/*
567. Permutation in String


*/
class Solution 
{
    
    // Could sort strings as well, but let us aim to avoid a sort operation
    // Auxillary space leveraged be constant as well too
    // And thus, sorting will NOT work here as well too
    
    public boolean checkInclusion(String s1, String s2) 
    {
        boolean status = true;
        // So s2 can NEVER contain a permutation of s1, is sz(s1) > sz(s2)
        if(s1.length() > s2.length())
        {
            return false;
        }
        // Leverage fact of (s1,s2) consisting of ONLY lowercase English letters
        // Leverage fact that we must check ONLY s1's letters as well too
        int[] freqMapOne = new int[26];
        int[] freqMapTwo = new int[26];
        
        makeFreqMap(s1,freqMapOne);
        makeFreqMap(s2,freqMapTwo);
        // Oh it's substring ... NOT a subsequence. Subsequence def seemed WAY too easy here
        // Well we may be able to use a sliding window technique instead?
        for(int i = 0; i < 26; ++i)
        {
            int s1_freq = freqMapOne[i];
            int s2_freq = freqMapTwo[i];
            if(s2_freq < s1_freq)
            {
                status = false;
                break;
            }
        }
        
        return status;
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
