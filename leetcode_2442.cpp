/*
URL = https://leetcode.com/problems/count-number-of-distinct-integers-after-reverse-operations/
2442. Count Number of Distinct Integers After Reverse Operations
*/
class Solution {
public:
    int countDistinctIntegers(vector<int>& nums) {
        std::set<int> myInts;
        // printf("Rev dig = %d\n", revDigit(dig)); // can pass in funcs as params to funcs :-O
        for(int& x : nums){
            myInts.insert(x);
            myInts.insert(revDigit(x));
        }
        return myInts.size();
    }
    
private: 
    // Can not do an assignment, of a val, to a type which is const-qualified.
    // This helps us avoid bad mutations to input
    // Do we need to pass in `ints` as references?
    int revDigit(int& num){
        int res = 0;
        int curPow = log10(num);
        while(num >= 10){
            res += (pow(10,curPow) * (num % 10));
            num /= 10;
            curPow--;
        }
        res += (pow(10,curPow) * num);
        return res;
    }
    
};
