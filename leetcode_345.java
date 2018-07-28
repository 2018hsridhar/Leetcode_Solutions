// https://leetcode.com/problems/reverse-vowels-of-a-string/

public class Solution {
    // handle capital letters to!
    public String reverseVowels(String s)
    {
        if(s == null)
        {
            return null;
        }
        char[] cArr = s.toCharArray();
        int ptr1 = 0;
        int ptr2 = cArr.length - 1;
        // handle case when stuck @ a vowel!
        while(ptr1 <= ptr2)
        {
            char c1 = cArr[ptr1];
            char c2 = cArr[ptr2];
            if(isVowel(c1) && isVowel(c2))
            {
                swap(cArr,ptr1,ptr2);
                ptr1++;
                ptr2--;
            }
            else
            {
                if(!isVowel(c1)) // coudl be a while, but it really should not matter, tbh !
                {
                    ptr1++;
                }
                if(!isVowel(c2))
                {
                    ptr2--;
                }
            }
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < cArr.length; i++)
        {
            sb.append(cArr[i]);
        }
        return sb.toString();
        
    }
    
    public void swap(char[] c, int i, int j)
    {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
    
    
    public boolean isVowel(char cIn)
    {
        char c = Character.toLowerCase(cIn);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
        {
            return true;
        }
        return false;
    }
    
}
