/*
Thought processes
1880. Check if Word Equals Summation of Two Words
https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/

Luckily, characters are bounded by the closed interval of LOWERCASE English letters [a-j]

*/
class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) 
    {
        boolean isSumEqual = false;
        int firstVal = obtainNumerical(firstWord);
        int secondVal = obtainNumerical(secondWord);
        int targetVal = obtainNumerical(targetWord);
        if(firstVal + secondVal == targetVal)
            isSumEqual = true;
        return isSumEqual;
    }
    
    public int obtainNumerical(String s)
    {
        int val = 0;
        int len = s.length();
        char[] c_arr = s.toCharArray();
        for(int i = 0; i < c_arr.length; ++i)
        {
            char c = c_arr[i];
            int conv = (int)(c  -'a');
            val += conv * (int)(Math.pow(10,(len-1)-i));
        }
        return val;
    }
    
}
