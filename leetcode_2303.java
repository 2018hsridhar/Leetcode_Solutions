/*
URL = https://leetcode.com/problems/calculate-amount-paid-in-taxes/
2303. Calculate Amount Paid in Taxes

Complexity
Let N := #-tax brackets
Time = O(N)
Space = O(1) ( explicit and implicit ) 

*/
class Solution {
    public double calculateTax(int[][] brackets, int income) 
    {
        // Note the "upper_1 - upper_0" calculations too.
        // Check if you dip negative or not too!
        // Calculate your difference too!
        double incomeTaxDue = 0.0;
        int n = brackets.length;
        for(int i = 0; i < n; ++i )
        {
            if(income <= 0)
                break;
            int upper_i = brackets[i][0];
            int bound_i = upper_i;
            if(i >= 1) {
                int upper_i_min_1 = brackets[i-1][0];
                bound_i = (upper_i) - (upper_i_min_1);
            }
            int rate_i = brackets[i][1];
            int diff = income - bound_i;
            if(diff < 0)
                incomeTaxDue += (( income * rate_i ) / 100.00 );
            else
                incomeTaxDue += (( bound_i * rate_i ) / 100.00 );
            income = diff;
        }
        return incomeTaxDue;
    }
}
