/*
875. Koko Eating Bananas
URL = https://leetcode.com/problems/koko-eating-bananas/
Seems to be one of those double binary search problems.


Complexity
Let N := len(piles)
Time = O(NlgN)
Space = O(1) ( EXP & IMP ) ( Excluding std::sort(container<T>))

Cases
(A) [1] 8 => 1
(B) [1] 1 => 1
(C) [4,3,2,1] 2 => 6
(D)
(E)


Be careful of floating point arithmetic error.
Doubles in place of floats!
Doubles can carry more data than floats.

*/
class Solution {
public:
    int minEatingSpeed(vector<int>& piles, int h) {
        std::sort(piles.begin(), piles.end());
        int minEatSpeed = piles[piles.size() - 1];
        int left = 1;
        int right = piles[piles.size() - 1];
        while(left <= right){
            int candidK = left + (( right - left ) / 2 ); 
            // printf("CandidK = %d\n", candidK);
            int posInArr = getArrayPos(piles,candidK);
            double hoursTaken = getHoursTaken(piles,candidK,posInArr);
            // printf("Hours Taken = %d\n", hoursTaken);
            if(hoursTaken == h){ // took exact timing : but may still have a min
                minEatSpeed = std::min(minEatSpeed, candidK);
                right = candidK - 1;
            } else if ( hoursTaken < h) { // took less time, but also ok
                minEatSpeed = std::min(minEatSpeed, candidK);
                right = candidK - 1;
            } else if ( hoursTaken > h ) { // took too much time
                left = candidK + 1;
            }      
        }
        return minEatSpeed;
    }
    
    double getHoursTaken(vector<int>& piles, int k, int posInArr){
        double hoursTaken = 0;
        hoursTaken += (posInArr + 1); // 0-indexed
        for(int i = posInArr + 1; i < piles.size(); ++i){
            double addHours = (double)piles[i] / (double)k;
            hoursTaken += std::ceil(addHours);
        }
        return hoursTaken;
    }
    
    // Get value in array cloest to <k>, but <= k
    // You desire the closest pile : check > curVal too!
    int getArrayPos(vector<int>& piles, int k){
        int low = 0;
        int high = piles.size() - 1;
        int arrPos = -1;
        int minDist = INT_MAX;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(piles[mid] == k){
                return mid;
            } else if ( piles[mid] > k){
                high = mid - 1;
            } else if ( piles[mid] < k){
                int curDist = std::abs(piles[mid] - k);
                if(curDist < minDist){
                    arrPos = mid;
                    minDist = curDist;
                }
                low = mid + 1;
            }
        }
        return arrPos;
    }
    
};
