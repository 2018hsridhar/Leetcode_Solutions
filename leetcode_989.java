/*
989. Add to Array-Form of Integer
URL = https://leetcode.com/problems/add-to-array-form-of-integer/
This is a typical sum-curry operation type of problem
Must start from right to left of array
May have extra digit on the left -> make sure to store in the SLL

Reverse the SLL at the end too!
Uses the digit algorithm too, as usual !

A number, mod 10, is the most useful way to extra a digit and a remainder - > it's like modulo-ing by the base helps extra
So it is cool, that given any base_10 number, you can apply the modulo operator repeatedly to keep extracting the rightmost digit ( in programming, that is )
E.g. 111 % 10 = 1; 111/10 = 11; 11% 10 = 1; 11 / 10 = 1; 1 % 10 == 1

But this has me wondering - does it extend to other base numbers ? E.g. Say, I have 1101 ( in binary, denote as 11_2 ). can we keep taking the modulo by the base to extract the final digit each time, just like above?
Or say, this were FFA9 ( in Hex - base 16 ). Can we keep dividing and moduloing by "F" = 16 here to extra digits?

Q : Why are sum and carry operations asked?
A : To implement calculators/computation.

99999 + 99999 = 188888 ( notice how we add 1 digit at a maximum, even provided two same n-length digits, maximized in their value ranges ) 

No leading zeroes, thankfully -> only trailing zeroes

Luckily, in the case of addition, carry operatons are bounded by [0,1]. See - multiplicates will mess you up for sure!
This is failing since your algorithm must work when a number is larger or smaller : what if k > input or input > k cases?


*/


class Solution 
{
    public List<Integer> addToArrayForm(int[] num, int k) 
    {
        LinkedList<Integer> totalSum = new LinkedList<Integer>();
        int j = num.length - 1;
        
        int carry = 0;
        while(k >= 10)
        {
            int dig = k % 10;
            k /= 10;
            int digSum = dig + num[j] + carry;
            --j;
            if(digSum >= 10)
            {
                carry = digSum / 10;
                digSum %= 10;
            }
            else
                carry = 0;
            totalSum.add(digSum);
        }
        
        // Find out if a carry still remainings here - add to rest of sum then
        while(j >= 0)
        {
            System.out.printf("num[j] = %d carry = %d k = %d\n", num[j], carry, k);
            int digSum = num[j] + carry;
            System.out.printf("digsum = %d\n", digSum);
            if(k > 0)
            {
                digSum += k;
                k = 0;
            }
             --j;
            if(digSum >= 10)
            {
                carry = digSum / 10;
                digSum %= 10;
            }
            else
                carry = 0;
            totalSum.add(digSum);
        }
        if(carry > 0)
            totalSum.add(carry);
        
        Collections.reverse(totalSum);
        return totalSum;
    }
}
