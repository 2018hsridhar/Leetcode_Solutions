/*
URL = https://leetcode.com/problems/replace-all-digits-with-characters/submissions/
1844. Replace All Digits with Characters

Even indices -> lowercase English letters { bounded by [a-z] }
Odd indices -> digits { bounded by [0-9] }
- Check index parity is we iterate over our string

Shift operations will never exceed 'z'; worst comes to worst, utilize the modulo operator to bring us back to an element within our set! OH!!!! Ain't this group theory again?

Java strings are sadly NOT character iterable! No built-in iterator to support this!
Are built-in iterators meant to be faster than naive iteration via indices? 

Great thing about odd indices : any index preceding an odd index is guaranteed to be >= 0

*/


class Solution 
{
    public String replaceDigits(String s) 
    {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < s.length(); ++i)
        {
            
            char ch = s.charAt(i);
            if(i % 2 == 0)
            {
                sb.append(ch);
            }
            else
            {
                // int digit = (int)ch; This will NOT work - it is casted directly to UNICODE/ASCII - needs the '0' offset too!
                int digit = (int)(ch - '0');
                char preChar = s.charAt(i-1);
                char newChar = (char)(preChar + digit);
                sb.append(newChar);
            }
        }
        sb.trimToSize();
        return sb.toString();
    }
}
