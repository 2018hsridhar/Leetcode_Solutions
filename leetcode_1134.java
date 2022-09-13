/*
URL = https://leetcode.com/problems/armstrong-number/
1134. Armstrong Number

n bounded from 1 to math.pow(10,8)

*/
class Solution {
    public boolean isArmstrong(int n) {
        int numDigits = 0;
        int temp = n;
        while(temp >= 10) {
            temp /= 10;
            numDigits++;
        }
        numDigits++;
        
        boolean isArmStrong = false;
        int origN = n;
        int sum = 0;
        while(n >= 10){
            int rem = n % 10;
            n /= 10;
            sum += (int)(Math.pow(rem,numDigits));
        }
        sum += (int)(Math.pow(n,numDigits));
        isArmStrong = (sum == origN);
        return isArmStrong;
    }
}
