/*

URL = https://leetcode.com/problems/minimum-window-substring/
76. Minimum Window Substring

STRATEGIES : 
- minimization
- sliding window technique
- hashmaps / counting
- tracking substring indices
- [a-zA-Z] : exploit alpha numeric trick as well?

> 'A' - 'Z' : 69-90
> 'a' - 'z' : 97 - 122
May need to check for letter cases as well

COMPLEXITY
let M := len(S), N := len(T)
Perform a length check ahead of time -> reduce #-of operations here
TIME = O(S)
SPACE = O(1) ( CALL STK ) O(M) ( AUX ) ( ITERATIVE ) 

TEST CASES : 
(A) s = "", t = ""
    ""
(B) s= "ABCDEFGH", t = "XYZ"
    ""
(C) s = "AAAAA", t = "ABC"
    => ""
(D) s = "ABCDEFG", t = "ABCDEF"
=> ABCDEF
(E)
(F)
(G)


Partition strategy : 
(a) grow right ptr, keeping the left ptr fixed
(b) Then slide left ptr to right until HM condition no longer met
(c) then incr right ptr till HM condition met again, and check if candidate performs better here

52 elements to check at max per iteration => is a fixed constant as well
Incorporate a hashset here as well ( so s need not update each character )
But 52 elements seems fine anyways -> just SOME extra space
-> how to avoid a sprase data structure


*/

class Solution 
{
    public String minWindow(String s, String t) 
    {
        int minS = -1;
        int minE = -1;
        int minLen = Integer.MAX_VALUE;
        
        int m = s.length();
        int n = t.length();
        
        int i = 0;
        int j = 0;
        
        int[] sCount = new int[52];
        int[] tCount = new int[52];
        for(int k = 0; k < n; ++k)
            tCount[getCharIndex(t.charAt(k))]++;
        
        // Get the first match, if it exists -> break out of loop
        // Consider BOOL expr to reduce logic too?
        // GOAL => avoid multiple loops in your code logic too
        while(j < m)
        {
            // Get the corresponding index and increment count
            char cur = s.charAt(j);
            int index = getCharIndex(cur);
            if(tCount[index] > 0)
                sCount[index]++;
            
            // Then compare non-zero entries of sCount to tCount to confirm equality
            boolean containsT = checkMatchingCounts(sCount,tCount);
            while(containsT == true)    // Notice this loop within the while loop as well
            {
                int curLen = Math.abs(j-i+1);
                if(curLen < minLen)
                {
                    minS = i;
                    minE = j;
                    minLen = curLen;
                }
                index = getCharIndex(s.charAt(i));
                if(tCount[index] != 0)
                    sCount[index]--;
                containsT = checkMatchingCounts(sCount,tCount);
                ++i;
            }
            ++j;
        }
        
        // NOT FOUND CASE
        if(minS == -1 || minE == -1)
            return "";
        String minWind = s.substring(minS,minE + 1);    // for string offsets here :-)
        return minWind;
    }
    
    // Get index into 52 length array here!
    // Default treat as uppercase => then lowercase later
    private int getCharIndex(char x)
    {
        int index = (int)(x-'A');
        if(Character.isLowerCase(x))
            index = (int)(x - 'a') + 26;
        return index;
    }
    
    private boolean checkMatchingCounts(int[] sCount, int[] tCount)
    {
        boolean containsT = true;
        for(int i = 0; i < 52; ++i)
        {
            if(tCount[i] != 0 && sCount[i] < tCount[i])
            {
                containsT = false;
                break;
            }
        }
        return containsT;
    }
    
    
}
