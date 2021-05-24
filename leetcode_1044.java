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
        String pattern = "abe";
        int initHash = computeInitHash(pattern);
        System.out.printf("For string pattern = %s, initHash = [%d]\n", pattern, initHash);
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
