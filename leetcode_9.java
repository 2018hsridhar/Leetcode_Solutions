/*

9. Palindrome Number
URL = https://leetcode.com/problems/palindrome-number/

Easier to just compare strings 
Utilize stringbuilder objects for support of trailing zeroes and leading zeroes too

Quick hacks
1. Anything less than 0 = false
2. Anything divisible by 10 engenders leading zeroes : hence is false
3. Be careful of data overflow : hence use strings -> not ints - for comparison

Current solution : converts integer to a string

*/
class Solution 
{
    public boolean isPalindrome(int x) 
    {
        int init  = x;
        if(x == 0) return true;
        if(x < 0 || x % 10 == 0) return false;
        StringBuilder sb = new StringBuilder("");
        while(x >= 10)
        {
            sb.append(x % 10);
            x /= 10;
        }
        sb.append(x);
        String cur = init + "";
        return (cur.equals(sb.toString()));
        
    }
}
