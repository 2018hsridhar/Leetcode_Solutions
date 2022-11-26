/*
https://leetcode.com/problems/kth-distinct-string-in-an-array/submissions/850242165/
2053. Kth Distinct String in an Array

*/
class Solution {
public:
    string kthDistinct(vector<string>& arr, int k) {
        map<string,int> freqString;
        for(const string& x : arr){
            if(freqString.find(x) == freqString.end()){
                freqString[x] = 0;
            }
            freqString[x]++;
        }
        int n = arr.size();
        for(int i = 0; i < n; ++i){
            if(freqString.at(arr.at(i)) == 1){
                k--;
                if(k == 0){
                    return arr.at(i);
                }
            }
        }
        return "";
    }
};
