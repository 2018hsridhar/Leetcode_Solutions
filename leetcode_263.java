/*
263. Ugly Number
URL = https://leetcode.com/problems/ugly-number/

*/
class Solution {
    public boolean isUgly(int n) 
    {
        boolean isUgly = true;
        if(n <= 0)
            return false;
        while(n % 2 == 0) {
            n /= 2;
        }
        while(n % 3 == 0) { 
            n /= 3;     
        }
        while(n % 5 == 0) {
            n /= 5;
        }
        if(n > 1)
            isUgly = false;
        return isUgly;
    }
}  
