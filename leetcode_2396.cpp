/*
URL = https://leetcode.com/problems/strictly-palindromic-number/
2396. Strictly Palindromic Number
8 mins to get it :-)
*/
class Solution {
public:
    bool isStrictlyPalindromic(int n) {
        bool status = true;
        for(int base = 2; base <= n-2; ++base){
            // can I even return as a reference
            // Check rules of binding values to types/exprs.
            vector<int> numInBase = getNumInBase(n,base);
            if(!isPalin(numInBase)){
                status = false;
                break;
            }
        }
        return status;
    }

private:

    bool isPalin(const vector<int>& num){
        int lPtr = 0;
        int rPtr = num.size() - 1;
        while(lPtr < rPtr){
            if(num.at(lPtr) != num.at(rPtr)){
                return false;
            }
            ++lPtr;
            --rPtr;
        }
        return true;
    }

    // Can we generalize our method to all base types?
    vector<int> getNumInBase(int num, int base){
        vector<int> numInBase;
        while(num > 1){
            numInBase.push_back(num % base);
            num /= base;
        }
        numInBase.push_back(num);
        return numInBase;
    }  
};
