/*
11 mins to solution
URL = https://leetcode.com/problems/largest-values-from-labels/description/
1090. Largest Values From Labels


Complexity
Let N := len(values)
TIME = O(NLgN)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    int largestValsFromLabels(vector<int>& values, vector<int>& labels, int numWanted, int useLimit) {
        int largestVal = 0;
        int n = values.size();
        vector<pair<int,int>> records;
        for(int i = 0; i < n; ++i){
            int myVal = values.at(i);
            int myLabel = labels.at(i);
            pair<int,int> record = make_pair(myVal,myLabel);
            records.push_back(record);
        }
        // pair : val -> label
        std::sort(records.begin(), records.end(), [](const pair<int,int>& pairOne, const pair<int,int>& pairTwo) -> bool {
            if(pairOne.first < pairTwo.first){
                return false;
            } 
            return true;
        });
        for(auto x : records){
            printf("[%d,%d]\n", x.first, x.second);
        }
        map<int,int> labelUseFreq;
        for(int i = 0; i < n; ++i){
            if(numWanted == 0){
                break;
            } else {
                int curVal = records.at(i).first;
                int curLabel = records.at(i).second;
                if(labelUseFreq.find(curLabel) == labelUseFreq.end()){
                    labelUseFreq[curLabel] = 0;
                }
                if(labelUseFreq[curLabel] < useLimit){
                    largestVal += curVal;
                    labelUseFreq[curLabel]++;
                    --numWanted;
                }
            }
        }
        return largestVal;    
    }
};
