/*
1167. Minimum Cost to Connect Sticks
URL = https://leetcode.com/problems/minimum-cost-to-connect-sticks/

Seems recursive/DP/combinatorial
We have a lot of sticks to deal with though ( 10,000 ) 
Wait is this DP -> or greedy
You can connect other any of the sticks in this problem too!
Why not sort and use a minimia type of thing?
    Not a full sort -> but more so a priority queue type of thing
    If pq sz = 1 : resolves to a base case -> do not connect!

*/
class Solution {
public:
    int connectSticks(vector<int>& sticks) {
        int minConnCost = 0;
        if(sticks.size() == 1) {
            return 0;
        }
        // Need to change underlying order of the contianer here
        std::priority_queue<int, std::vector<int>, std::greater<int>> pq;
        for(int i : sticks){
            pq.push(i);
        }
        while(pq.size() > 1){
            int firstMin = pq.top();
            pq.pop();
            int secondMin = pq.top();
            pq.pop();
            int combined = firstMin + secondMin;
            pq.push(combined);
            minConnCost += combined;
        }
        return minConnCost;
    }
};
