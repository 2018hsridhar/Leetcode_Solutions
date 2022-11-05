/*
URL = https://leetcode.com/problems/minimize-xor/description/
2429. Minimize XOR

*/
class Solution {
public:
    int minimizeXor(int num1, int num2) {
        bitset<32> numTwoInBinary(num2);
        bitset<32> numOneInBinary(num1);
        bitset<32> numOfFutureXor(0);
        // Function on the GCC compiler
        int countOneInTwo = __builtin_popcount(num2);
        for(int a = 31; a >= 0; --a){
            if(numOneInBinary[a] == 1 && countOneInTwo > 0){
                numOfFutureXor.set(a,1);
                --countOneInTwo;
            }
        }
        // Assuming we `zeoed` out num1 here
        for(int a = 0; a <= 31; ++a){
            if(numOfFutureXor[a] == 0 && countOneInTwo > 0){
                numOfFutureXor.set(a,1);
                --countOneInTwo;
            }
        }
        // Template-style C++ cast
        int res = static_cast<int>(numOfFutureXor.to_ulong());
        return res;
    }
};
