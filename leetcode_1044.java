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
        
        int patternHash = computeInitHash(pattern);
        // System.out.printf("For string pattern = %s, initHash = [%d]\n", pattern, hash);
        
        char[] sArr = s.toCharArray();
        StringBuilder sb = new StringBuilder("");
        
        for(int i = 0; i < M;++i)
        {
            sb.append(sArr[i]);
        }
        int stringHash = computeInitHash(sb.toString());
        System.out.printf("substr = [%s] \t hash = [%d]\n", sb.toString(), stringHash);
        
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
            System.out.printf("substr = [%s] \t hash = [%d]\n", sb.toString(), stringHash);
            
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
            // System.out.printf("Dig = %d\n", nextDig);
            initHash += nextDig;
            ++pow;
        }    
        return initHash;
    }
    
    
    
    
}
