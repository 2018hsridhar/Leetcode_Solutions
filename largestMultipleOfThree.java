/*

URL = https://leetcode.com/problems/largest-multiple-of-three/
1363. Largest Multiple of Three

Take note : the more local variables we declare in a function, the more 
stack space we will consume as well

COMPELXITY
Let N := len(digits)
Time = 
Space = O(1) ( implicit ) ___ ( explicit ) 

TEST CASES 
(A)
(B)
(C)
(D)
(E)

Ok ... so your approach with the modulus is correct : BUT ... you must test out a powerset of combos here, sadly :-(
And digits length can go up to 10,000 ( 10 K ) in length
Now at least the digit radixes are known :-)
Let us suppose we added them all up and did not get to the maximimal ... can we start doing some subtractions of those minimums instead in a greedy manner?
E.g. [0,1,6,7,8] => @ end, modSum = 1 : subtract 0, then subtract 1, then tell if we got back to an expected mod sum?
Potential issue -> this could break some test cases though 

*/

class Solution 
{
    public String largestMultipleOfThree(int[] digits)
    {
        // A quick arrays.sort call here ( start from left to right though ) 
        // A minimization will always skip 0 as a leading digit to anyways!
        Arrays.sort(digits);
        int n = digits.length;
        StringBuilder sb = new StringBuilder("");
        int modSum = 0;
        for(int i = 0; i < n; ++i)
        {
            int digit = digits[i];
            int modRes = digit % 3;
            modSum += (modRes);
            // Optimization : avoid a repeated modulus operation here as well
            // Or leverage the modulus to prevent data overflow runtime exceptions
        }
        modSum %= 3;
        System.out.printf("Mod sum = %d\n", modSum);
        if(modSum == 0)
        {
            for(int i = 0; i < n; ++i)
            {
                int digit = digits[i];
                sb.append(digit);
            }
            sb.reverse();
        }
        else
        {
            sb.append("");
        }
        String largestMultiple = sb.toString();
        return sb.toString();
    }
}
