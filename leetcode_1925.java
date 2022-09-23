/*
https://leetcode.com/problems/count-square-sum-triples/
1925. Count Square Sum Triples

*/
class Solution {
    public int countTriples(int n) {
        int count = 0;
        for(int a = 1; a <= n; a++){
            for(int b = a+1; b <= n; b++){
                for(int c = Math.max(a,b); c <= n; c++){
                    int lhs = (int)Math.pow(a,2) + (int)Math.pow(b,2);
                    int rhs = (int)Math.pow(c,2);
                    if(lhs == rhs) {
                        count += 2;
                    }
                }
            }
        }
        return count;
    }
}
