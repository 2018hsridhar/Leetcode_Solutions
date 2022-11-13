/*
825. Friends Of Appropriate Ages
URL = https://leetcode.com/problems/friends-of-appropriate-ages/description/

Careful on low val cases such as [1] : take a min of ( val, purpoted bound )
30 mins ish here

Edge cases
(A) [16,16,16,16] => 2
    ^ PASS
(B) [100,80,40,20,1] 
    ^ PASS
(C) [1,2,4,4,6,6,6,7,7,7,9,10,10,10,14,14,14]
    ^ PASS
(D) [1,2,4,4,6,6,6,7]
    ^ PASS
(E) [1,2,4,4,6,6,6,7,7,7,9,10,10,10,14,14,14,15,16,17,18]   
    ^ PASS
(F) [100,99,98,66,66,66,69,69,70,1,2,3,5,4,12,12]
    ^ PASS
(G) [100,99,98,66,66,66,69,69,70,70,70,1,89,89,2,3,5,4,12,12]
    ^  PASS
(H) [13,13,13,14,14,14,15,15,15,16,16,16,17,17,18,18,19,19,20,20]
    ^ PASS
(I)
(J)
1 hour
but went thru stress-tests
cuaght bugs 
learnt unit test cases :-)

*/
class Solution {
public:
    int numFriendRequests(vector<int>& ages) {
        int numFR = 0;
        std::sort(ages.begin(), ages.end());
        int i = 0;
        int n = ages.size();
        int numFRPrevEl = 0;
        int curRunLen = 0;
        // TBH, our boudn makes sense only at age = 15 : 15/2 = 7 + 7 = 14
        // below that, we can never befriend anyone anyways!!
        // why not just do this in place instead?
        while(i < n){
            int ageX = ages.at(i);
            if(ageX < 15){
                ++i;
                continue;
            }
            int lowerBound = static_cast<int>((( 0.5 * ageX ) + 7 ) + 1);
            // can this trip up somewhere?
            if(i >= 1){
                if(ages.at(i) == ages.at(i-1)){
                    curRunLen++;
                    numFR += numFRPrevEl;
                    // and check boudn here too!
                    if(ages.at(i-1) >= lowerBound){
                        numFR += 2 * curRunLen;
                    }
                } else {
                    curRunLen = 0;
                    // respect inequality : att to lowerBound here
                    int lowerBoundIndex = binarySearchLowerBound(ages, lowerBound);
                    if(lowerBoundIndex != -1){
                        // printf("Lower bound index = %d\n", lowerBoundIndex);
                        // printf("Delta = %d\n", i - lowerBoundIndex);
                        numFR += (i - lowerBoundIndex);
                        numFRPrevEl = (i - lowerBoundIndex);
                        // printf("For num = %d \t numFRPREvEl = %d\n", ageX, numFRPrevEl);
                    }
                }
            } else if ( i == 0) {
                int lowerBoundIndex = binarySearchLowerBound(ages, lowerBound);
                // printf("Lower bound index = %d\n", lowerBoundIndex);
                if(lowerBoundIndex != -1){
                    numFR += (i - lowerBoundIndex);
                    numFRPrevEl = numFR;
                }
            }
            ++i;
        }
        return numFR;
    }

private:
    // this returns an index into the `ages` vector
    // or returns `-1` in event of lower bound not found
    int binarySearchLowerBound(vector<int>& ages, int lowerBound){
        int low = 0;
        int high = ages.size() - 1;
        int bestIndex = INT_MAX;
        int bestDist = INT_MAX;
        while(low <= high){
            int mid = low + (high-low)/2;
            // if(ages.at(mid) == lowerBound){ // this may be your bug !
                // return mid;
            if ( ages.at(mid) >= lowerBound) { // this is a bug !
                high = mid - 1;
                if(ages.at(mid) - lowerBound <= bestDist){
                    bestIndex = min(bestIndex,mid);
                    bestDist = min(bestDist,ages.at(mid) - lowerBound);
                }
            } else { // ages.at(mid) < lowerBound
                low = mid + 1;
            }
        }
        if(bestIndex == INT_MAX) {
            bestIndex = -1;
        }
        return bestIndex; // either closest or neg 1 at this point
    }

};
