/*

URL = https://leetcode.com/problems/largest-multiple-of-three/
1363. Largest Multiple of Three

We note that an answer should be greedy to having as many zeroes in the end as possible : 0 % 3 = 0, has no effect at all with the algorithm too!

TEST CASES : 
(A) [1,1,1,2]
Am right, but running into a MLE exception. Now to resolve that as well too :-)
(B) [8,7,6,1,5,8,4,1,2,5,1,2,3,8,6,4,0]
    passes as expected
(C) [8,7,6,1,5,8,4,1,2,5,1,2,3,8,6,4,0,1,2,7,3,4,8,9,5,3,1,1,7,3,2]
    passes too
Do not allocate a n*k : allocate a 2*k matrix, but do an overwrite as well for the resultant :-)
*/
class Solution 
{
    public String largestMultipleOfThree(int[] digits) 
    {
        // This sort should be in reverse order BTW
        Arrays.sort(digits);

        int k = 3;
        int n = digits.length;

        // Better : why not just work with the empty string instead?
        // Initialize your memoized matrix 
        StringBuilder[][] DP = new StringBuilder[k][2];
        for(int i = 0; i < k; ++i)
        {
            for(int j = 0; j < 2; ++j)
            {
                DP[i][j] = new StringBuilder("");
            }
        }
        
        // Handle the last column ( a terminating case : for a single number as well )     
        int lastDigit = digits[0];
        int modIdx = lastDigit % 3;
        DP[modIdx][1].append(lastDigit);
        
        // Your approach may break when dealing with a bunch of zeroes btw. Take note of this!
        // First col, second col calculations
        // Overwrite second col with first col, and then clear the first column too!
        for(int j = (n-2); j >= 0; --j)
        {
            int digit = digits[(n-1)-j];
            for(int i = 0; i < 3; ++i)
            {
                DP[i][0].append(DP[i][1]);
            }
            // The run-time bug is isolated here for sure
            for(int i = 0; i < 3; ++i)
            {
                int nextIdx = (digit + i)%3;
                StringBuilder substring = new StringBuilder("");
                if(!DP[i][1].toString().equals(""))
                {
                    // Can do this due to digit property of sort, I think?
                    if(DP[i][1].length() + 1 >= DP[nextIdx][1].length())
                    {
                        DP[nextIdx][0] = new StringBuilder(DP[i][1]);
                        DP[nextIdx][0].append(digit);;                        
                    }
                }
            }
            if(DP[digit%3][0].toString().equals(""))
            {
                DP[digit%3][0].append(digit);
            }
            
            // NOw for the overwrite
            for(int i = 0; i < 3; ++i)
            {
                // System.out.printf("%s\n", DP[i][0].toString());
                DP[i][1] = new StringBuilder(DP[i][0].toString());
                DP[i][0].delete(0,DP[i][0].length());
            }
        }
        
        StringBuilder ans = DP[0][1];
        String maxInt_str = ans.reverse().toString();
        if(maxInt_str.equals(""))
        {
            return "";
        }

        // Handle leading zero
        int i = 0;
        while(i < maxInt_str.length() - 1 && maxInt_str.charAt(i) == '0')
        {
            ++i;
        }
        return maxInt_str.substring(i);
    }
}

/*


PSEUDOCODE : 

    // O(NlogN) Time, O(1) if an in-place sort
    Arrays.sort(nums);

    k = 3;
    n = nums.length;
    
    // Better : why not just work with the empty string instead?
    StringBuilder DP[n][k];
    for i in range(n)
        for j in range(k)
            DP[i][j].append("")
        
    // Handle the last column ( a terminating case : for a single number as well )     
    lastDigit = nums[n-1];
    modIdx = lastDigit % 3;
    for j in range(k)
        DP[n-1][modIdx].append(lastDigit);
    
    // Your approach may break when dealing with a bunch of zeroes btw. Take note of this!
    for i in range(n-1)
        for j in range(0,2)
            DP[i][j].append(DP[i][j+1])
        for j in range(0,2)
            int digit = nums[i];
            int nextIdx = (digit + j)%3
            StringBuilder substring = new StringBuilder();
            substring.append(DP[i][j+1]);
            if(substring.length() > DP[i][j+1].length())
                DP[nextIdx][j].delete(0,DP[nextIdx][j].size() - 1)
                DP[nextIdx][j].append(substring)
    
    StringBuilder ans = DP[0][0]
    String maxInt_str = ans.reverse().toString();
    if(maxInt_str.equals(""))
    {
        return 0;
    }
    
    // Handle leading zero
    int i = 0;
    while(i < maxInt_str.length() - 1 && maxInt_str.charAt(i) == 0)
    {
        ++i;
    }
    int maxInt = Integer.parseInt(maxInt_str.substring(i));
    return maxInt;
        
            
    
    
COMPLEXITY
Time = O(NK) + O(NLogN)
Space = O(NK) ( explicit ) O(1) ( implicit ) 

Test Cases
(A)
(B)
(C)
(D)
(E)
(F)


*/
