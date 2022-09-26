/*
978. Longest Turbulent Subarray
URL = https://leetcode.com/problems/longest-turbulent-subarray/

Can we avoid additional space, knowing we always compare to 
our adjacent element too?
But we need a reset mechanism along the way too!

Test Cases
(A) [5,5,5,5,5] => 1
(B) [1,2,1,2,1] => 5
(C) [9,4,2,10,7,8,8,1,9] => 5
(D) [12,35,321,43,65,879,12,43,78,90,12,42,64,8,23,65,98,76,32,45,21,781,121] => 6


COMPLEXITY
TIME = O(N)
SPACE = O(1)


*/
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int maxSize = 1;
        int turbGreat = 1; // startEl > el_to_right
        int turbLess = 1; // startEl < el_to_right
        int n = arr.length;
        int i = n - 2;
        // Always compare to the adjacent element!
        while(i >= 0){
            if(arr[i] > arr[i+1]){
                int curLen = 1 + turbLess;
                if(curLen > maxSize) {
                    maxSize = curLen;
                }
                turbGreat = curLen;
                turbLess = 1;                
            }
            else if ( arr[i] < arr[i+1]){
                int curLen = 1 + turbGreat;
                if(curLen > maxSize) {
                    maxSize = curLen;
                }
                turbLess = curLen;
                turbGreat = 1;
            } else {
                turbLess = 1;
                turbGreat = 1;
            }
            --i;
        }
        return maxSize;
    }
}
