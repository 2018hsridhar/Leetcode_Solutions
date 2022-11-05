/*
Note that the XOR bool op is commutative and associative
2433. Find The Original Array of Prefix Xor
URL = https://leetcode.com/problems/find-the-original-array-of-prefix-xor/description/

Complexity
Let N := len(pref)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 

Cases
(A) [5,2,0,3,1,345,234,67,54,12,67,3,2,45,6754] => PASS
(B) [0,1,23,54,2,0,23,23,32] => PASS

15 mins to solution
*/
class Solution {
public:
    vector<int> findArray(vector<int>& pref) {
        int n = pref.size();
        vector<int> arr;
        arr.push_back(pref.at(0));
        for(int i = 0; i < n-1; ++i){
            arr.push_back(pref.at(i) ^ pref.at(i+1));
        }
        return arr;
    }
};
