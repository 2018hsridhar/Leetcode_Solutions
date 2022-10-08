/*
URL= https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
1689. Partitioning Into Minimum Number Of Deci-Binary Numbers

Is honestly a LC easy classifiable problem :-)
*/
class Solution {
    public int minPartitions(String n) {
        int minNum = 0;
        for(char c : n.toCharArray()){
            int val = (int)(c - '0');
            minNum = Math.max(minNum,val);
        }
        return minNum;
    }
}
