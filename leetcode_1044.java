// 1044. Longest Duplicate Substring

/* 
    The Rabin-Karp Algorithm is an underlying component to the solution of this problem
    Attempt to first code up Rabin-Karp
    
    Hyperlinks for the Rabin-Karp Algorithm : 
    1. https://www.youtube.com/watch?v=BQ9E-2umSWc
    2. https://www.youtube.com/watch?v=oxd_Z1osgCk&t=487s
    3. https://www.tutorialcup.com/interview/string/rabin-karp-algorithm.htm
    4. https://www.youtube.com/watch?v=H4VrKHVG5qI
    - focus on Tuschar Roy's video : made sense of the hash function with primes the best
    
    Rabin-Karp : Easiest of the algorithms
    
    First, try the naive hash method for the rolling hash
    Then experiment with a more complicated hash
    
    https://leetcode.com/problems/longest-duplicate-substring/

 */
class Solution {
    
    
    /*
    
        Attempt a solution via the Rabin Karp Substring Algorithm
    
    
     */
    
    // used everywhere in program : thus make it a global variable ( not a function variable ) 
    // Note : current method does not scale to incredibly large prime numbers ( if substrings have a long length too )
    // This is why the modulus operation might be used later on
    int prime = 3;
    
    public String longestDupSubstring(String s) 
    {
        // A test string input here is "banana"
        String pattern = "ana";
        
        int M = pattern.length();
        int N = s.length();
        
        int hash = computeInitHash(pattern);
        // System.out.printf("For string pattern = %s, initHash = [%d]\n", pattern, initHash);
        
        char[] sArr = s.toCharArray();
        StringBuilder sb = new StringBuilder("");
        
        for(int i = 0; i < M;++i)
        {
            sb.append(sArr[i]);
        }
        System.out.printf("substr = [%s] \t hash = [%d]\n", sb.toString(), hash);
        
        for(int i = M; i < N; ++i)
        {
            int firstCharIdx = i - M;
            // System.out.printf("firstCharIdx = %d\n", firstCharIdx);
            char firstCharInSubStr = sb.charAt(0);
            char nextChar = sArr[i];
            sb.deleteCharAt(0);
            sb.append(nextChar);
            
            // COMPUTE NEW HASH
            int newX = hash - (firstCharInSubStr - 'a' + 1);
            newX /= prime;
            newX += Math.pow(prime, M-1) * (nextChar - 'a' + 1);
            hash = newX;
            System.out.printf("substr = [%s] \t hash = [%d]\n", sb.toString(), hash);
            
            // System.out.printf("substring = %s\n", sb.toString());
        }
        
        
        return pattern;
    }
    
    
    // Note : luckily, the string s is composed only of [a-z], so character shifting is much easier
    public int computeInitHash(String s)
    {
        int initHash = 0;
        int pow = 0;
        char[] c_arr = s.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            int myValue = (c_arr[i] - 'a' + 1);
            int nextDig = myValue * (int)Math.pow(prime,pow);
            initHash += nextDig;
            ++pow;
        }    
        return initHash;
    }
    
    
    
    
}
