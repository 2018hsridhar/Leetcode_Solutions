/*
1647. Minimum Deletions to Make Character Frequencies Unique
URL = https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/

Most containers are available under the C++ std namesapce

Cases :

Complexity :
Let N := len(s)
TIME = O(N)
SPACE = O(1) ( EXP & IMP ) 
*/
class Solution {
public:
    int minDeletions(string s) {
        int minDel = 0;
        vector<int> freqMp(26,0);
        for(char c : s){
            int idx = static_cast<int>(c - 'a'); // wrap your type in paren for static_cast<T> op
            freqMp[idx]++;
        }
        vector<int> nonZeroFreqMap;
        for(int i : freqMp){
            if(i != 0) {
                nonZeroFreqMap.push_back(i);
            }
        }
        std::sort(nonZeroFreqMap.begin(), nonZeroFreqMap.end());
        std::set<int> visited;
        visited.insert(nonZeroFreqMap.at(0));
        int ptr = 1;
        // actually this may be inefficient -> O(N^2) as a count could go rather high
        while(ptr < nonZeroFreqMap.size()){
            int firstCount = nonZeroFreqMap.at(ptr-1);
            int secondCount = nonZeroFreqMap.at(ptr);
            if(firstCount == secondCount) {
                int curNumOps = 0;
                while(secondCount > 0){
                    secondCount--;
                    curNumOps++;
                    if(visited.find(secondCount) == visited.end()){
                        visited.insert(secondCount);
                        break;
                    }
                }
               minDel += curNumOps; 
            } else {
                visited.insert(secondCount);
            }
            ++ptr;
        }
        return minDel;
    }
};
