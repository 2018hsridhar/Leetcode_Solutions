/*
2264. Largest 3-Same-Digit Number in String
URL = https://leetcode.com/problems/largest-3-same-digit-number-in-string/
Nums is only digits
Nums len at least = 3 here
*/
class Solution {
    public String largestGoodInteger(String num) {
        int largestVal = -1;
        int i = 0;
        while(i < nums.length() - 2){
            if(num.charAt(i) == num.charAt(i+1) && num.charAt(i+1) == num.charAt(i+2)){
                largestVal = Math.max(largestVal, (int)(num.charAt(i) - '0'));
            }
            ++i;
        }
        String result = (largestVal == -1) ? "" : String.format("%d%d%d",largestVal,largestVal,largestVal);
        return result;
    }
}
