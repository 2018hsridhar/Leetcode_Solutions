/*
1962. Remove Stones to Minimize the Total
URL = https://leetcode.com/problems/remove-stones-to-minimize-the-total/
*/
// https://stackoverflow.com/questions/9025084/sorting-a-vector-in-descending-order
// C++14 : Leverage std::greater<>() here :-)
class Solution {
public:
    int minStoneSum(vector<int>& piles, int k) {
        int minSum = 0;
        // Use priority queue instead : least -> greater!
        std::priority_queue<int> pq;
        for(const int& x : piles){
            pq.push(x);
        }
        for(int a = 0; a < k; ++a){
            int topEl = pq.top();
            pq.pop(); // ok this is some bull**** : pq.pop() ideally returns a val
            int newEl = topEl - std::floor(topEl / 2);
            pq.push(newEl);
        }
        while(!pq.empty()){
            minSum += pq.top();
            pq.pop();
        }
        return minSum;
    }
};
