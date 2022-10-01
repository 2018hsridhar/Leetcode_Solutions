/*
2224. Minimum Number of Operations to Convert Time
URL = https://leetcode.com/problems/minimum-number-of-operations-to-convert-time/

Cases
(A) "02:30", "12:00" => 11
(B) "02:30", "02:30" => 0
(C) "02:30", "02:31" => 1
(D) "02:30", "02:34" => 4

*/
class Solution {
    public int convertTime(String current, String correct) {
        int timeOne = getTimeVal(current);
        int timeTwo = getTimeVal(correct);
        int diff = Math.abs(timeTwo-timeOne);
        int minOps = 0;
        while(diff >= 0){
            if(diff-60 >= 0){
                diff -= 60;
                minOps++;
            } else if ( diff - 15 >= 0){
                diff -= 15;
                minOps++;
            } else if ( diff - 5 >= 0){
                diff -= 5;
                minOps++;
            } else if ( diff - 1 >= 0){
                diff -= 1;
                minOps++;
            } else {
                break;
            }
        }
        return minOps;
    }
    
    private int getTimeVal(String current)
    {
        String[] tokens = current.split(":");
        int hour = Integer.parseInt(tokens[0]);
        int minute = Integer.parseInt(tokens[1]);
        return ((hour * 60) + minute);
    }

    
}
