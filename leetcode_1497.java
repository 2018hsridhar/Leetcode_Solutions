


/* 

    1497. Check If Array Pairs Are Divisible by k
    URL = https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/

    Your modulus trick works when all the numbers are positive or 0
    But may fail in the case of negatives, so take very close note there
    We want to avoid testing all pairs here though




*/

class Solution 
{
    public boolean canArrange(int[] arr, int k)
    {
        boolean canArrange = false;
        int n = arr.length;
        HashMap<Integer,Integer> modFreqs = new HashMap<Integer,Integer>();
        for(int i = 0; i < n; ++i)
        {
            int elem = arr[i];
            int remainder = (int)((elem % k) + k)%k;    // must be proper modulo here as well
            if(!modFreqs.containsKey(remainder))
            {
                modFreqs.put(remainder,1);   
            }
            else
            {
                modFreqs.put(remainder, modFreqs.get(remainder) + 1);
            }
        }
        
        // Iterate over hashamps and check matching frequencies here
        // Specifically from (1,k-1) ... (k/2,k/2) type of thing
        // Check the zero case for being even as well ( does matter here ) 
        if(modFreqs.containsKey(0) && modFreqs.get(0) % 2 == 1)
        {
            return false;
        }
        // (0,k), (1,k-1),(2,k-2),... and so on
        for(int i = 1; i < k; ++i)
        {
            int first = i;
            int second = (k-i);
            // Special null pointer checks too
            boolean hasFirst = modFreqs.containsKey(first);
            boolean hasSecond = modFreqs.containsKey(second);
            if(hasFirst && hasSecond)
            {
                int freqFirst = modFreqs.get(first);
                int freqSecond = modFreqs.get(second);
                if(freqFirst != freqSecond)
                {
                    return false;
                }
            }
            else if ( !hasFirst && !hasSecond )
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        
        // int x = -97;
        // int y = x % 10;
        // Or maybe just take the abslute value ( seems correct here ) 
        // System.out.printf("y = %d \t actual = %d \n", y, (y + 3)%3);
        return true;
    }
}
