/*

URL = https://leetcode.com/problems/bitwise-xor-of-all-pairings/description/
2425. Bitwise XOR of All Pairings

Leverage XOR being a commutative operator :-)
Leverage length of nums2 array as well and nums1 array two
May be able to derive rapid computation here

x = x
x^x = 0
x^x^x = x
x^x^x^x = 0
...
you get the idea
*/
class Solution {
public:
    int xorAllNums(vector<int>& nums1, vector<int>& nums2) {
        int xorOne = 0;
        int lenOne = nums1.size();
        int lenTwo = nums2.size();
        if(lenTwo % 2 == 0){
            xorOne = 0;
        } else {
            for(int x : nums1){
                xorOne ^= x;
            }
        }
        int xorTwo = 0;
        if(lenOne % 2 == 0) {
            xorTwo = 0;
        } else {
            for(int x : nums2){
                xorTwo ^= x;
            }
        }
        return xorOne ^ xorTwo;
    }
};
