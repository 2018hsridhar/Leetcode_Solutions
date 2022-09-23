/*
URL = https://leetcode.com/problems/three-divisors/
1952. Three Divisors

*/
class Solution {
    public boolean isThree(int n) {
        int countDiv = 0;
        for(int i = 1; i <= n; ++i) {
            if(n%i == 0){
                countDiv++;
            }
            
        }
        return (countDiv == 3);
    }
}
