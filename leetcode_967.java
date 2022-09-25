/*
URL = https://leetcode.com/problems/numbers-with-same-consecutive-differences/
967. Numbers With Same Consecutive Differences

n is strictly bounded inbetween 2 and 9 here :-)

*/
class Solution {
    public int[] numsSameConsecDiff(int n, int k) {
        ArrayList<Integer> sameNums = new ArrayList<Integer>();
        for(int i = 1; i <= 9; ++i){
            int curNum = i * (int)Math.pow(10,n-1);
            helper(sameNums,curNum,i,n-1,k); // exec algo recursively here
        }
        int[] results = new int[sameNums.size()];
        Iterator<Integer> itr = sameNums.iterator();
        int wIdx = 0;
        while(itr.hasNext()){
            results[wIdx++] = itr.next();
        }
        return results;
    }
    
    private void helper(List<Integer> results, int curNum, int i, int n, int k){
        if(n == 0) {
            results.add(curNum);
            return;
        } else
        {
            int lowerNum = i - k;
            int higherNum = i + k;
            if(lowerNum == higherNum) {
                    int nextI = lowerNum;
                    int nextNum = curNum + ((nextI) * (int)Math.pow(10,n-1));
                    helper(results,nextNum,nextI,n-1,k);
            } else {
                if(lowerNum >= 0) {
                    int nextI = lowerNum;
                    int nextNum = curNum + ((nextI) * (int)Math.pow(10,n-1));
                    helper(results,nextNum,nextI,n-1,k);
                } 
                if(higherNum <= 9) {
                    int nextI = higherNum;
                    int nextNum = curNum + ((nextI) * (int)Math.pow(10,n-1));
                    helper(results,nextNum,nextI,n-1,k);
                }
            }
        }
    }
    
}
