


class Solution {
    
    /*
    
        Attempt a solution via the Rabin Karp Substring Algorithm
        Basic one : Uses prime operations   
        Time Complexity 
            M = Pattern Length, N = String Length
            Best case : O(M+N) - suppose only one string match max. Then O(M) iterate over single pattern, O(N) = long string iteration
            Worst case : O(MN)- supposes we match each substring in the pattern ( e.g. "a" = pattern, "aaaaaaaaaaaa" = string )
        
        Space Complexity : 
            O(1) 
            
        Links : 
        1. https://github.com/mission-peace/interview/blob/master/src/com/interview/string/RabinKarpSearch.java
        2. https://www.youtube.com/watch?v=H4VrKHVG5qI&t=908s
        3. https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
            
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
        
        int patternHash = computeInitHash(pattern);
        
        char[] sArr = s.toCharArray();
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < M;++i)
            sb.append(sArr[i]);
        int stringHash = computeInitHash(sb.toString());
        
        ArrayList<Integer> matchHits = new ArrayList<Integer>();
        for(int i = M; i < N; ++i)
        {
            int firstCharIdx = i - M;
            // System.out.printf("firstCharIdx = %d\n", firstCharIdx);
            char firstCharInSubStr = sb.charAt(0);
            char nextChar = sArr[i];
            sb.deleteCharAt(0);
            sb.append(nextChar);
            
            // COMPUTE NEW HASH
            int newX = stringHash - (firstCharInSubStr - 'a' + 1);
            newX /= prime;
            newX += Math.pow(prime, M-1) * (nextChar - 'a' + 1);
            stringHash = newX;
            
            if(stringHash == patternHash)
            {
                // check pure string equality
                String substr = sb.toString();
                if(substr.equals(pattern))
                {
                    matchHits.add(i - M + 1);
                }
            }
        }
        
        System.out.printf("Hits = %s\n", matchHits.toString());
        
        
        
        
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
            // System.out.printf("Dig = %d\n", nextDig);
            initHash += nextDig;
            ++pow;
        }    
        return initHash;
    }
    
    
    
    
}
