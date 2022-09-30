/*
2404. Most Frequent Even Element
URL = https://leetcode.com/problems/most-frequent-even-element/
*/
class Solution {
    public int mostFrequentEven(int[] nums) {
        int mfe = 0;
        int maxLen = 0;
        int curLen = 0;
        int curEven = Integer.MIN_VALUE;
        Arrays.sort(nums);
        int i = 0;
        int n = nums.length;
        int myLen = 0;
        while(i < n){
            int el = nums[i];
            if(el % 2 == 0){
                curLen = Math.max(curLen,1);
                maxLen = Math.max(maxLen,1);
                if(el != curEven){
                    if(curEven == Integer.MIN_VALUE)
                        mfe = el;
                    curEven = el;
                    curLen = 1;
                } else {
                    curLen++;
                    if(curLen > maxLen) {
                        maxLen = curLen;
                        mfe = el;
                    }
                }
            } else {
                myLen = 0;
            }
            ++i;
        }
        if(maxLen == 0)
            mfe = -1;
        return mfe;
    }
}
