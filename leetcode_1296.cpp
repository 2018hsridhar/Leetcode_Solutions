/*
1296. Divide Array in Sets of K Consecutive Numbers
URL = https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
*/
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if(nums.size() % k != 0){
            return false;
        }
        bool stat = true;
        std::sort(nums.begin(), nums.end());
        std::unordered_map<int,int> freqNums;
        for(int x : nums){
            if(freqNums.find(x) == freqNums.end()){
                freqNums[x] = 0;
            }
            freqNums[x]++;
        }
        int n = nums.size();
        for(int i = 0; i < n; ++i){
            int el = nums[i];
            if(freqNums[el] > 0){ // used up 
                // printf("For num = %d \t freq = %d\n", el, freqNums[el]);
                while(freqNums[el] > 0){ // check sequence count here
                    if(haveSeq(freqNums,el,k)){
                        freqNums[el]--;
                    } else {
                        stat = false;
                        return false;
                    }
                }
            }
        }
        return stat;
    }

private:
   // copy op is shallow
     bool haveSeq(std::unordered_map<int,int>& freqNums, int seed, int k){
         bool stat = true;
         for(int delta = 1; delta < k; ++delta){
             int nextEl = delta + seed;
             if(freqNums.find(nextEl) != freqNums.end()){
                //  cout << nextEl << '\t' << freqNums[nextEl] << endl;
                 if(freqNums[nextEl] <= 0){
                     stat = false;
                     break;
                 } else {
                     freqNums[nextEl]--;
                    //  cout << "DECR el" << nextEl << endl;
                 }
             } else {
                 stat = false;
                 break;
             }
         }
        return stat;
     }
};
