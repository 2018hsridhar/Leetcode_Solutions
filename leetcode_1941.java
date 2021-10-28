class Solution {
    public boolean areOccurrencesEqual(String s) 
    {
        int n = s.length();
        int[] freq = new int[26];
        for(int i = 0; i < n; ++i)
        {
            char c = s.charAt(i);
            int idx = (int)(c - 'a');
            freq[idx]++;
        }
        
        // Now check equality across all
        int modeFreq = -1;
        for(int i = 0; i < 26; ++i)
        {
            if(freq[i] > 0)
            {
                if(modeFreq != -1)
                {
                    if(freq[i] != modeFreq)
                        return false;
                }
                else
                {
                    modeFreq = freq[i];
                }
            }
        }
        return true;
    }
}
