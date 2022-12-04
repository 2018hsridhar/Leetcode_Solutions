/*
URL = https://leetcode.com/problems/meeting-scheduler/description/
1229. Meeting Scheduler

Complexity
Let M = len(slots1), N = len(slots2)
Time = O(MlgM) + O(NlgN) + O(M+N)
Space = O(1)( EXP ) O(1)( IMP ) 

18.5 mins 

*/
class Solution {
public:
    vector<int> minAvailableDuration(vector<vector<int>>& slots1, vector<vector<int>>& slots2, int duration) {
        vector<int> earliestSlot = {-1,-1};
        bool haveHit = false;
        int m = slots1.size();
        int n = slots2.size();
        std::sort(slots1.begin(), slots1.end(), [](const vector<int>& s1, const vector<int>& s2) -> bool {
            if(s1.at(0) < s2.at(0)){
                return true;
            } else {
                if(s1.at(1) < s2.at(1)){
                    return true;
                } else {
                    return false;
                }
            }
        });
        std::sort(slots2.begin(), slots2.end(), [](const vector<int>& s1, const vector<int>& s2) -> bool {
            if(s1.at(0) < s2.at(0)){
                return true;
            } else {
                if(s1.at(1) < s2.at(1)){
                    return true;
                } else {
                    return false;
                }
            }
        });
        int ptr1 = 0;
        int ptr2 = 0;
        while(ptr1 < m && ptr2 < n){
            vector<int> intOne = slots1.at(ptr1);
            vector<int> intTwo = slots2.at(ptr2);
            int slotsIntersect = hasIntersection(intOne,intTwo); // use for statusCoding
            if(slotsIntersect == -1){
                if(intOne.at(1) < intTwo.at(1)){
                    ptr1++;
                } else {
                    ptr2++;
                }
            } else {
                int maxLeftTime = std::max(intOne.at(0), intTwo.at(0));
                int minRightTime = std::min(intOne.at(1), intTwo.at(1));
                int delta = minRightTime - maxLeftTime;
                if(delta >= duration){
                    earliestSlot.at(0) = maxLeftTime;
                    earliestSlot.at(1) = maxLeftTime + duration;     
                    haveHit = true;
                    break;
                } else {
                    if(intOne.at(1) < intTwo.at(1)){
                        ptr1++;
                    } else {
                        ptr2++;
                    }   
                }
            }
        }
        if(!haveHit){
            return {};
        }
        return earliestSlot;
    }

// Does candidate understand the the types of intersection testing?
private:
    int hasIntersection(const vector<int> intOne, const vector<int> intTwo){
        int statusCode = -1;
        if(intOne.at(0) <= intTwo.at(0) && intTwo.at(0) <= intOne.at(1)){
            statusCode = 0; // intTwo in intOne
        } else if ( intTwo.at(0) <= intOne.at(0) && intOne.at(0) <= intTwo.at(1)){
            statusCode = 1; // intOne in intTwo
        }
        return statusCode;
    }
};
