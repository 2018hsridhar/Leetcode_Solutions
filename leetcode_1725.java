/*
URL = https://leetcode.com/problems/number-of-rectangles-that-can-form-the-largest-square/
1725. Number Of Rectangles That Can Form The Largest Square

*/
class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        int maxLen = Integer.MIN_VALUE;
        int maxFreq = 0;
        for(int i = 0; i < rectangles.length; ++i) {
            int[] rect = rectangles[i];
            int left = rect[0];
            int right = rect[1];
            int curLen = Math.min(left,right);
            if(curLen > maxLen) {
                maxLen = curLen;
                maxFreq = 1;
            } else if ( curLen == maxLen ) {
                maxFreq++;
            }
        }
        return maxFreq;
    }
}
