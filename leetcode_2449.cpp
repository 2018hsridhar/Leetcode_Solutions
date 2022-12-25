/*
URL = https://leetcode.com/problems/minimum-number-of-operations-to-make-arrays-similar/
2449. Minimum Number of Operations to Make Arrays Similar

Complexity
Let N := len(target)
Time = O(nlgn)
Space = O(1) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:

    std::pair<long long, long long> computeDeltas(vector<int>& subsetNums, vector<int>& subsetTarget){
        using ll = long long;
        std::pair<ll,ll> myDeltas;
        for(int ptr = 0; ptr < subsetNums.size(); ptr++){
            int subsetEl = subsetNums.at(ptr);
            int targetEl = subsetTarget.at(ptr);
            if(targetEl > subsetEl){ // + delta
                myDeltas.first += abs((targetEl - subsetEl) / 2);
            } else { // neg delta
                myDeltas.second += abs((targetEl - subsetEl) / 2);
            }
        }
        return myDeltas;
    }

    long long makeSimilar(vector<int>& nums, vector<int>& target) {
        using ll = long long;
        ll myNumNeededOps = 0;
        int n = nums.size();
        std::sort(nums.begin(), nums.end());
        std::sort(target.begin(), target.end());
        std::vector<int> evenListNums;
        std::vector<int> evenListTarget;
        std::vector<int> oddListNums;
        std::vector<int> oddListTarget;
        for(int el : nums){
            if(el % 2 == 0){
                evenListNums.push_back(el);
            } else if ( el % 2 == 1){
                oddListNums.push_back(el);
            }
        }
        for(int el : target){
            if(el % 2 == 0){
                evenListTarget.push_back(el);
            } else if ( el % 2 == 1){
                oddListTarget.push_back(el);
            }
        }
        // [2] Compute the ( add, subtract )  deltas and add as we go!
        std::pair<ll,ll> deltaOne = computeDeltas(evenListNums, evenListTarget);
        std::pair<ll,ll> deltaTwo = computeDeltas(oddListNums, oddListTarget);
        std::pair<ll,ll> deltaTotal = make_pair<ll,ll>(deltaOne.first + deltaTwo.first,
                                                            deltaOne.second + deltaTwo.second);
        myNumNeededOps = deltaTotal.first;
        return myNumNeededOps;
    }
};
