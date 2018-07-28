// https://leetcode.com/problems/add-strings/

Total time to code, test, execute = 16 minutes
Aware of solution … see n-digit ripple adder
Classification :: 2 pointers, arrays, string builder
public class Solution {
 public String addStrings(String num1, String num2) {
 String res = "";
 if(num1 == null && num2 == null || num1.length() == 0 && num2.length() == 0)
 {
 res = null;
 }
 else if ( num1 == null || num1.length() == 0)
 {
 res = num2;
 }
 else if (num2 == null || num2.length() == 0)
 {
 res = num1;
 }
 else
 {
 // 2 pointers problem
 // n-digit ripple adder problem
 // carry ( divide by 100 ... i.e. 9/100 = 0, 19/100 = 1, 27/100 = 2)
 // sum ... keep sutracting 10, till < 10 !
 // lens of strs can differ! ... take note !
 char[] cArr_1 = num1.toCharArray(); // "300" => ['3','0','0']
 char[] cArr_2 = num2.toCharArray(); // "99" => ['9','9']
 StringBuilder sb = new StringBuilder(""); // we'll reverse this @ the end

 int ptr1 = cArr_1.length - 1;
 int ptr2 = cArr_2.length - 1;
 int cIn = 0;
 int s = 0;

 while(ptr1 >= 0 || ptr2 >= 0)
 {
 int cOut = 0;
 if(ptr1 < 0)
 {
 s = (int)(cArr_2[ptr2] - '0') + cIn; // bug :: it is ( - ‘0’, not + ‘0’ ) … minor mistake
 ptr2--;
 }
 else if ( ptr2 < 0)
 {
 s = (int)(cArr_1[ptr1] - '0') + cIn;
 ptr1--;
 }
 else
 {
 s = (int)(cArr_1[ptr1] - '0') + (int)(cArr_2[ptr2] - '0')+ cIn;
 ptr1--;
 ptr2--;
 }
 cOut = s / 10; // bug ... div by 10,m not div by 100 .. accidentally typed in 2 0’s
 while(s >= 10) // bug ... s >= 10, not s > 10
 {
 s -= 10;
 }
 sb.append(s);
 cIn = cOut;
 s = 0; // former bug ... replace sum!
 }

 if(cIn != 0)
 {
 sb.append(cIn);
 }
 res = sb.reverse().toString();
 }
 return res;
 }
}
