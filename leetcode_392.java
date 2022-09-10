class Solution {
    public boolean isSubsequence(String s, String t) 
    {
        int sLen = s.length();
        int tLen = t.length();
        if(s.length() > t.length())
            return false;
        int sPtr = 0;
        int tPtr = 0;
        while(tPtr < tLen ) {
            if(sPtr == sLen)
                break;
            if(s.charAt(sPtr) == t.charAt(tPtr))
            {
                ++tPtr;
                ++sPtr;
            }
            else
            {
                ++tPtr;    
            }
        }
        return sPtr == sLen;
    }
}
