/*
1109. Corporate Flight Bookings

URL = https://leetcode.com/problems/corporate-flight-bookings/description/
*/
class Solution {
public:
    vector<int> corpFlightBookings(vector<vector<int>>& bookings, int n) {
        vector<int> answer(n,0);
        // [1] Sort in increasing left ( right no matter ) 
        std::sort(bookings.begin(), bookings.end(), [](const vector<int>& one, const vector<int>& two) -> bool {
            if(one.at(0) < two.at(0)){
                return true;
            } else if ( one.at(0) > two.at(0)){
                return false;
            } else {
                if(one.at(1) < two.at(1)) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        // for(auto x : bookings){
            // printf("[%d,%d,%d],", x.at(0), x.at(1), x.at(2));
        // }

        // [2] set up priority queue with custom comparator for vector
    	auto comp = [&](vector<int>& left, vector<int>& right)
            {
                if(left.at(1) < right.at(1)){
                    return false;
                } else if ( left.at(1) > right.at(1)) {
                    return true;
                } else {
                    if(left.at(0) < right.at(0)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
        std::priority_queue<vector<int>, vector<vector<int>>, decltype(comp)> pq(comp); 
        int myRunSum = 0;
        int bookPtr = 0;
        // handle 1-off issue
        for(int i = 1; i <= n; ++i){
            while(bookPtr < bookings.size() && bookings.at(bookPtr).at(0) == i){ // add booking
                myRunSum += bookings.at(bookPtr).at(2);  
                pq.emplace(bookings.at(bookPtr));
                ++bookPtr;
            }
            while(pq.size() > 0){ // GUARD condition
                const vector<int> topEl = pq.top();
                if(topEl.at(1) < i){
                    myRunSum -= topEl.at(2);
                    pq.pop();
                } else {
                    break;
                }
            }
            answer.at(i-1) = myRunSum;
        }


        return answer;
    }
};
