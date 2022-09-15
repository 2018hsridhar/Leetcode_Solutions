/*
826. Most Profit Assigning Work
URL = https://leetcode.com/problems/most-profit-assigning-work/

EDGE CASES
(A) [1,2,3]
[1,1,1]
[3,2,1] -> 1
(B)
(C)
(D)
(E)


*/


class Solution {
    
    public class Info
    {
        public int d;
        public int p;
        
        public Info() {
            p = 0;
            d = 0;
        }
        
        public Info(int d, int p)
        {
            this.d = d;
            this.p = p;
        }
    }
    

    public class InfoComparator implements Comparator<Info>
    {
        public int compare(Info mine, Info other)
        {
            if(mine.d < other.d)
            {
                return -1;
            }
            else if(mine.d > other.d)
            {
                return 1;
            } else {
                if(mine.p < other.p) {
                    return -1;
                } else if ( mine.p > other.p) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
     }   

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int maxProfit = 0;
        int n = difficulty.length; // and profit length too
        int m = worker.length;
        Arrays.sort(worker);
        
        // Make new data structure for sorting by top order : < difficulty, profit >
        Info[] myInfo = new Info[n];
        for(int i = 0; i < n; ++i) {
            myInfo[i] = new Info(difficulty[i], profit[i]);
        }
        Arrays.sort(myInfo, new InfoComparator()); // oh we could use a lambda function here :-)
        int dPtr = 0;
        int myCurMax = 0;
        int wPtr = 0;
        while(wPtr < m)
        {
            int curHourCap = worker[wPtr];
            while(dPtr < n) {
                if(myInfo[dPtr].d <= curHourCap){
                  myCurMax = Math.max(myInfo[dPtr].p, myCurMax);  
                  dPtr++;  
                } else {
                    break; // you are at the max difficulty
                }
            }
            maxProfit += myCurMax;
            wPtr++;
        }
        return maxProfit;
    }
    
    
    
    
}



