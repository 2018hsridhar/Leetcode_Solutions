/*
2171. Removing Minimum Number of Magic Beans
URL = https://leetcode.com/problems/removing-minimum-number-of-magic-beans/

You can zero out a bag in its entirety
We probably want a greedy solution.
If we have a given minimum, we know the distances too ( from all other bags ) if we want to get to that specfic number.
    If we ignore and discard all from min -> get remaining num Steps too
    Process right->left or left->right?
Mathematical manipulation / 2 counters

Let N := len(beans) ~ 100,000
TIME = O(NlgN)
SPACE = O(1) ( EXP ) O(1) ( IMP ) 

5 mins spent
*/
class Solution {
public:
    long long minimumRemoval(vector<int>& beans) {
        int n = beans.size();
        using ll = long long;
        ll minSteps = std::numeric_limits<long>::max();
        std::sort(beans.begin(), beans.end());
        ll leftSum = 0;
        // printf("HERE");
        // check return of std::accumulate! for long long
        ll seed = 0;
        ll rightSum = std::accumulate(beans.begin(), beans.end(), seed);
        // printf("HERE");
        ll rightCount = n-1;
        for(int i = 0; i < n; ++i){
            ll curEl = beans.at(i);
            rightSum -= beans.at(i); // sum of els to right only
            if(i > 0){
                leftSum += beans.at(i-1);
            }
            rightCount = ((n - 1) - i);
            ll curNumSteps = leftSum + (rightSum - (rightCount*curEl));
            minSteps = std::min(minSteps, curNumSteps);
        }
        return minSteps;
    }
};
