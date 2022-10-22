/*
URL = https://leetcode.com/problems/find-the-winner-of-the-circular-game/
1823. Find the Winner of the Circular Game


*/
class Solution {
public:
    int findTheWinner(int n, int k) {
        // Make this a circular linked list
        // Keep performing the operations on it ( SLL or DLL too ) 
        // N times you update your <k> ptr too.
        // You will exec ops only (n-1) times too : that bound is a known
        int winner = -1;
        vector<int> prev;
        vector<int> next;
        // Note : these are vector indices : must add by (1) for proper value too
        for(int i = 0; i < n; ++i){
            next.push_back((i+1)%n);
        }
        for(int i = n-1; i >= 0; --i) {
            prev.push_back(((i-1+n))%n); // careful with modulo eval to (-) too!
        }
        std::reverse(prev.begin(), prev.end());
        int cur = 0; // val = 1
        int size = n;
        for(int i = 1; i <= n; ++i){
            int startPos = cur;
            if(size == 1){
                winner = startPos + 1;
                break;
            }
            for(int a = 1; a < k; a++){ // include starter node
                cur = next.at(cur);
            }
            // at the node to remove : get its prev and its next, and set them
            int finPos = cur;
            int prevIdx = prev.at(cur);
            int nextIdx = next.at(cur);
            next.at(prevIdx) = nextIdx;
            prev.at(nextIdx) = prevIdx;
            cur = nextIdx;
            size--;
        }
        return winner;
    }
};
