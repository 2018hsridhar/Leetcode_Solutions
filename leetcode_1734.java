/*

1734. Decode XORed Permutation
https://leetcode.com/problems/decode-xored-permutation/

COMPLEXITY :
TIME = O()
SPACE = O()

TEST CASES : 
(A)
(B)
(C)
(D)
(E)

<--- start from small, well-known test cases. Enlargen as we proceed too!

*/

class Solution 
{
    public int[] decode(int[] encoded) 
    {
        if(encoded == null || encoded.length == 0)
            return new int[]{};
        int n = encoded.length + 1;
        int[] results = new int[n];
        int bitOffset = (int)(Math.ceil(Math.log(n) / Math.log(2)));    // Check if ceil function or NOT as well!
        
        // Must check for both starter values as well, and get 1's count as we go
        // Specifically, ones count, NOT from the visited too
        // We are trying two toggle code paths -> evaluate the better one, and STICK with that as well!
        int[] visited = new int[n]; // Default 0-initialized!
        int remLen = n;
        for(int i = bitOffset - 1; i >= 0; --i)  // constant : goes decreasing way too!
        {
                int starterVal = 0;
                int numLeadingOnes = countOnes(starterVal, i, encoded, visited);
                if(numLeadingOnes % 2 == 1 && remLen > 1)
                {
                    starterVal = 1;
                    numLeadingOnes = remLen - numLeadingOnes;   // complement idea here
                }
                if(remLen == 1 && numLeadingOnes != 1)  // Caution here as well too!
                {
                    starterVal = 1;
                    numLeadingOnes = remLen - numLeadingOnes;
                }
                remLen -= numLeadingOnes;
                writeToResultsAtOffset(starterVal, i, encoded, results, visited);
        }

        return results;        
    }
    
    // Notice that starterVal = 0, in all cases. Be wary for the count, with regards to visited elements too!
    // Careful here with visited set idea : check arrays for correctness
    public int countOnes(int starterVal, int offset, int[] encoded, int[] visited)
    {
        int onesCount = 0;
        int n = encoded.length;
        int precVal = starterVal;
        int mask = (1 << offset);
        for(int i = 0; i < n; ++i)
        {
            int curVal = precVal ^ ( encoded[i] & mask);
            if((curVal>>offset) == 1 && visited[i+1] != 1)
            {
                onesCount += 1;
            }
            precVal = curVal;
        }
        return onesCount;
    }
    
    // Check for correctness
    // WRiting to each offset : if an index is written with a one, then set that visited to true
    // CAUTION with your bitset offsets here as well!
    
    public void writeToResultsAtOffset(int starterVal, int offset, int[] encoded, int[] results, int[] visited)
    {
        int n = encoded.length;
        int precVal = starterVal;
        int mask = (1 << offset);
        results[0] = results[0] | (starterVal << offset) ;    // always supposed to write starter val here
        if((results[0] & mask) >> offset == 1)
            visited[0] = 1;
        
        
        for(int i = 0; i < n; ++i)
        {
            int curVal = precVal ^ (( encoded[i] & mask) >> offset);
            results[i + 1] = ( results[i + 1] | (curVal << offset) );
            precVal = curVal;
            if(((results[i + 1] & (curVal<<offset)) >> offset) == 1)
            {
               visited[i + 1] = 1; 
            }
        }
    }
    
    
    
    
    
}
