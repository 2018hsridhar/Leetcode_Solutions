/*

URL = https://leetcode.com/problems/decode-string/
394. Decode String

  Can read JAVA APIs for syntax
  k \in N
  No extra white spaces
  square bracket is well-formed : [] : ][, ]]]], [[[
  Never a null input string or empty ( has at least one character )
  
  Digits are integer data types, but can be more than one digit in length
  [a-z], [a-zA-Z&#@!]
  Assume : only lowercase English letters
  Can get a string with no brackets ( only letters )
  
  Run-length encoding idea : aaabbbbb => a3b6
  
  Complexity ( aiming for ) : 
  Let N := len(string)
  Time = O(N)
  Space = O(N)
  
  TEST BENCH : 
  (A) s = "abcde...xyz"
    returns the same string
  (B) s = "abbb" === "a3[b]"
  (C) s = "abcdab"
    return s
  (D) s = "1[2[3[4[5[a]]]]]"
  
  Guaranteed -> digits always prepend a bracket
  
  ['a','b','d','2','0','0','[','a','b','c',']','3','[','c','d',']','e','f']
    0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17
                                             |                  |       ^
                                                                              ptr
                
  String - indexOf() -> first end
    ptr = 3
    l_idx = indexOf('[') = 6
    r_idx = indexOf(']') = 10 
    
    28[ab300[ab]]waetrawerar3[3[wer]]
    ^
       
    Digit -> substring(ptr, l_idx) : parse : String -> Int
    bracket_str = substring(l_idx + 1, r_idx)
    Construct the repeated string from the bracket_str and digit
    
    ptr = right_idx + 1 = 11
    
    Nested structure -> utilize a stack/recursive call?
    push(digit) and push(string) under computation
      -> string under computation : [], when we encounter a close -> chk stk top and if it is '[' we pop
      
  ['2','8','[','d','c','3','0','0','[','a','b',']',']']
    0   1   2   3   4   5   6   7   8   9   10  11  12
                                ^                       ^           ~    ~
  Push the digit onto the stk as well
  
  bottom {"28", 'a','b',"300",'a','b'} top
  keep popping until a string containing a digit is hit
  StringBuilder for the popped characters -> reverse that -> perform repeation ( 300 * rev("ba")) -> push back onto the stack
  bottom {"28", 'd','c',"ababab....ab"} top
  ababab...cd -> dc...bababa => repeat 28 times => push back onto the stack here
  until stk size == 1 : indicates we are done
  
*/

/**

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].


Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
**/

/*
Test bench to pass : 

"2[2[y]pq4[2[jk]e1[f]]]"
"2[y]pq4[2[jk]e1[f]]"
"pq4[2[jk]e1[f]]"
"4[2[jk]e1[f]]"
"4[2[jk]e1[f]]"



*/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution 
{
  // Temp storage @ nested level
  public static String decodeString(String s) 
  {
    StringBuilder resSB = new StringBuilder("");
    Stack<String> stk = new Stack<String>();
    int level = 0;
    int ptr = 0;
    
    while( ptr < s.length())
    {
      char c = s.charAt(ptr);
      if(Character.isDigit(c))
      {
        int firstLeftBracketIndex  = s.indexOf('[', ptr);  // potential for bug : chk
        String digStr = s.substring(ptr,firstLeftBracketIndex);
        stk.push(digStr);
        ++level;
        ptr = firstLeftBracketIndex+1;
      }
      else if ( c == ']')
      {
        String top = stk.peek();
        boolean topHasDigit = top.matches("[0-9]+");
        StringBuilder nestSB = new StringBuilder("");
        while(topHasDigit == false)
        {
          nestSB.append(stk.pop());
          top = stk.peek();
          topHasDigit = top.matches("[0-9]+");
        }       
        int digit = Integer.parseInt(stk.pop());
        String nested = nestSB.reverse().toString();
        String nestedSubStr = nested.repeat(digit);
        StringBuilder temp = new StringBuilder(nestedSubStr);
        stk.push(temp.reverse().toString()); // you are pushing a nested substring, and then reversing that again. Is an issue
        --level;
        if(level == 0)
        {
          resSB.append(nestedSubStr);
        }
        ++ptr;
      }
      else
      {
        if(level == 0)
        {
          resSB.append(c);
        }
        else
        {
          String val = c + "";
          stk.push(val);
        }
        ++ptr;
      }
    }
    return resSB.toString();
  }
  
  /*
    (A) s = "abcde...xyz"
    returns the same string
  (B) s = "abbb" === "a3[b]"
  (C) s = "abcdab"
    return s
  (D) s = "1[2[3[4[5[a]]]]]"
      */

}
