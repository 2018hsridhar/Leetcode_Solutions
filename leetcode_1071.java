// PROBLEM : 1071. Greatest Common Divisor of Strings
// NOTE : READ THE STATEMENT CORRECTLY! DO NOT JUST GO NAIVE AND GENERATE LARGEST PREFIX!
// THINK OF THE EDGE CASES CORRECTLY!

class Solution {
    public String gcdOfStrings(String str1, String str2) 
    {
        String gcd = "";
        // no need to check empty string case
        // easy base case : str1.len == str2.len
        // Work off the smaller string
        // Can obtain the prefixes, of the smaller string, and test on the larger string for sure
        // HINT : Need all prefixes actually be checked here? Ideally solvable in [T,S] = [O(n), O(1)], where n = largest str len
        
        char[] str1_cArr = str1.toCharArray();
        char[] str2_cArr = str2.toCharArray();
        // min of either arrahy here :-)
        int len = Math.min(str1_cArr.length, str2_cArr.length);
        
        int r = 0;
        for(int i = 0; i < len; ++i)
        {
            if(str1_cArr[i] == str2_cArr[i])
            {
                ++r;
            }
            else
                break;
        }
        // now test if common substring prefix works on both strings
        // is gcd algo : can you divide here?
        String comLargePref = str1.substring(0, r);
        int clp_len = comLargePref.size();
        int s1_len = str1.size();
        int s2_len = str2.size();
        
        
        // System.out.printf("Substring = [%s]\n", comLargePref);
        

        
        return gcd;
    }
} 
