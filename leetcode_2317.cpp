/*
2317. Maximum XOR After Operations 
URL = https://leetcode.com/problems/maximum-xor-after-operations/
No worry on data overflow

https://stackoverflow.com/questions/19583720/convert-bitset-to-int-in-c

Cases
(A) [123,53,76,3,98,6] => PASS
(B) [1,2,4,8,16,32] => PASS

*/
class Solution {
public:
    int maximumXOR(vector<int>& nums) {
        int myMaxXor = 0;
        int n = nums.size();
        std::bitset<32> finalRes(0);
        // finalRes.set(); // set to all 1's
        for(int i = 0; i < n; ++i){
            bitset<32> myBitwiseRep(nums.at(i));
            for(int a = 0; a < 31; ++a){
                if(myBitwiseRep[a] == 1){
                    finalRes[a] = 1;
                }
            }
        }
        myMaxXor = static_cast<int>(finalRes.to_ulong());
        return myMaxXor;
    }
};
