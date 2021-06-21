/*

URL = https://leetcode.com/problems/sum-of-digits-in-base-k/
1837. Sum of Digits in Base K

Remember - this is all base 10 numbers, so conversion might be quickly formulacally
One understands base_2 conversions well too!
The trick is to append ( right to left ) and to account for the remainder in each step of the division
Becuase remainders are your digits! Don't you remember this from the base formula with (%) operator?


*/
class Solution 
{
    public int sumBase(int n, int k) 
    {
        int sum = 0;
        StringBuilder sb = new StringBuilder(""); // append to string, as string is immutable :-)
        while(n >= k)
        {
            int rem = n % k;
            n /= k;
            sum += rem;
        }
        sum += n;
        return sum;
    }
}
