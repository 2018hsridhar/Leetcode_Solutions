/*

URL = https://leetcode.com/problems/longest-substring-without-repeating-characters/
3. Longest Substring Without Repeating Characters

Strategies : sliding window, maps, two pointers?

Complexity
Let N := len(s)
Time = O(N)
Space = O(1) ( implicit ) O(N) (explicit) 

TEST BENCH
(A) "abcdefghijkl" ( all unique )
(B) "aaaaaaaaaaaa" ( all same ) 
(C) "aaaaaaaaaabc" ( must go to end ) 
(D)
(E)
(F)
(G)

Character class : [a-z0-9\\s+{symbols}]

*/
class Solution 
{
    public int lengthOfLongestSubstring(String s) 
    {
        if(s.length() <= 0 || s == null)
            return 0;
        String retVal = longestSubstringWithoutDuplication(s);
        return retVal.length();
    }
    
    public static String longestSubstringWithoutDuplication(String str) 
	{
		String lswd = "";
		int n = str.length();
		int longest_len = 1;
		int longest_begin = n-1;
		int longest_end = n;
		int s = n-1; // default of first being a valid case
		int e = n;	// Must be n for the full string here to print out in substr operation
		Set<Character> encount = new HashSet<Character>();
		encount.add(str.charAt(s));
		s = n - 2;
		// System.out.printf("%s\n", str.substring(s,e));
		
		while(s >= 0)
		{
			char prefix = str.charAt(s);
			while(encount.contains(prefix) && e > (s+1))
			{
				encount.remove(str.charAt(e-1));
				e -= 1;
			}
			// You always add the new key anyways. This logic seldom changes
			encount.add(prefix);
			int substr_len = (int)Math.abs(e-s);
			if(substr_len > longest_len)
			{
				longest_len = substr_len;
				longest_end = e;
				longest_begin = s;
			}
			s -= 1;
		}
		lswd = str.substring(longest_begin,longest_end);
    return lswd;
  }
}
