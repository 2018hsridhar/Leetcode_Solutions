/*
1043. Partition Array for Maximum Sum
URL = https://leetcode.com/problems/partition-array-for-maximum-sum/

Edge Cases
(A) [2,5,10],3 => 30
(B) [9,2,5,10] => 

*/
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int maxSum = 0;
        int n = arr.length;
        int[] subArrMaxes = new int[n];
        int i = n-1;
        int count = 0;
        int runMax = Integer.MIN_VALUE;
        // O(NK) poly time algorithm.
        while(i >= 0){
            if(count < k){
                runMax = Math.max(runMax, arr[i]);
                subArrMaxes[i] = runMax*(count+1);
                maxSum = Math.max(maxSum, subArrMaxes[i]);
             } else {
                runMax = 0;
                // Length from 1 to k here
                for(int j = 1; j <= k; ++j){
                    runMax = Math.max(runMax, arr[i+j-1]);
                    subArrMaxes[i] = Math.max(subArrMaxes[i],(runMax * j) + subArrMaxes[i+j]);
                    maxSum = Math.max(maxSum,subArrMaxes[i]);
                }
            }
            count++;
            --i;
        }
        return maxSum;
    }
}
