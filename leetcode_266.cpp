#import <bitset>
#import <valarray>

/*

266. Palindrome Permutation
URL = https://leetcode.com/problems/palindrome-permutation/


Given a string s, of length = n here
Check if a permutation can form a palindrome
Palindrome : frequency of each digit is even EXCEPT for one digit, which is allowed to be odd
OR they are all evens
#-odd freq digits <= 1

Lowercase letters = [a-z], so 26 len string max
String len max = 5K too




*/

class Solution {
public:
    bool canPermutePalindrome(string s) 
    {
        bitset<26> alphabet;
        alphabet.reset();   // zero it all out
        bool isPalin = true;
        for(char c : s)
        {
            int index = (int)(c - 'a');
            alphabet.flip(index);
        }
        // do a pop_count
        int count = alphabet.count();
        if(count > 1)
        {
            isPalin = false;
        }
        return isPalin;
    }
};
