/*

URL = https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/
1013. Partition Array Into Three Parts With Equal Sum

Desired optimal Computational Complexity : 
Time = O(N)
Space = O(1)

Data type testing : 
1. Inputs guaranteed within closed range interval of [-10,000, 10,000]. May be negative or repeated too


Approach - utilize two pointers and make sure they never overlap
Guaranteed valid array lengths, at min, for each array must equal to 1 heere too


Is the middle > sum on either side, or < sum, on either side. If less than either side - break : invalid
If > ... we can still go dive deep within!


Quick testing 
(1) Solve for aggregate sum : check if divisible by 3, for rapid testing! ( x * 3 = total_sum here anyways! ) \
(2) Solve for sum in O(N) time and O(1) space
No degenerate array lengths : guaranteed from [3,50,000]

Edge case testing : 
(1) Minimal imputs - [2,2,2]
(2) Input with all elements equal - [3,3,3,3,3,3,3,3,3]
(3) Input wth different sizes - [1,2,3,6,4,2]
(4) A failing input  [1,5,2,4] : not able to partition at all!
(5) An input where indices do not meet up - [4,5,8,1,2,7] : each partition sum = 9 : total sum = 27
(6) Yes - sum can be 0 too [-1,1,-2,2,-5,5] : break with an alternating sequence!
(7) [-1,1,0,0,-5,5]
(8) [-87,94,14,-13,50,99,-56,86,-99,-84,-43,43,22,-81,-82,8,-69,92,-81,3,-84,50,73,-15,-74,30,75,62,-15,-28,29,78,-30,54,58,-84,87,-55,-81,62,9,-31,-88,-44,74,16,-75,32,40,-28,5,-44,-91,18,68,89,59,-16,46,2,-98,16,71,-14,-7,-39,-78,-48,78,-39,0,70,-28,45,-25,-82,19,99,57,-26,25,25,86,48,90,-61,-56,-81,89,-61,-68,88,82,-92,-19,-61,-14,35,92,-95,47,-20,-32,47,-89,50,-1,-57,68,30,25,0,-17,34,56,58,-20,72,-4,-36,-36,-63,46,76,36,42,-94,-89,-4,-9,12,-64,11,-52,46,-39,-66,-36,0]

Test case broke this : check out the negations properly for sure
Do not just test lhsSUM = 0 or rhsSUM = 0 : the "0" case will trip yee up!
Be greedy here with extending the array partitions too
*/


class Solution 
{
    public boolean canThreePartsEqualSum(int[] arr) 
    {
        int i = 0;
        int j = arr.length - 1;
        int totalSum = 0;
        for(int x : arr ) 
            totalSum += x;
        if(totalSum % 3 != 0)
            return false;
        
        // System.out.printf("Total sum = %d\n", totalSum);
        
        int sumLHS = arr[0];
        int sumRHS = arr[j];
        ++i;
        --j;
        int sumDiv3 = totalSum / 3;
        int sumMiddle = totalSum;
        while(i <= (arr.length - 2))
        {
            if(sumLHS == sumDiv3)
                break;
            sumLHS += arr[i];
            ++i;
        }
        --i;
        while(j >= 2)
        {
            if(sumRHS == sumDiv3)
                break;
            sumRHS += arr[j];                
            --j;
        }
        ++j;
        // Check indices even updated here
        
        if((j-i) <= 1)
            return false;
        return (sumLHS == sumDiv3 && sumRHS == sumDiv3);
    }
}
