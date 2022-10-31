class Solution {
public:
    vector<long long> sumOfThree(long long num) {
        long long resNum = num - 3;
        vector<long long> myNums;
        if(resNum % 3 != 0){
            return {};
        } else {
            long long base = resNum / 3;
            myNums = { base, base + 1, base + 2 };
        }
        return myNums;
    }
};
