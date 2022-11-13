/*
URL = https://leetcode.com/problems/rabbits-in-forest/
781. Rabbits in Forest

Complexity
Let N := len(answers)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

Case
(A) [4,4,4,4,4,4,4,4,4,4,4,4,4,4,4] => PASS
(B) [1,2,3,4,5,6] => PASS
(C) [6,12,15,1,1,6,6,6,6,6] => PASS
(D) [1,1,2,2,2,2] => PASS
(E)


25 mins to solve 

*/
class Solution {
public:
    int numRabbits(vector<int>& answers) {
        int popSize = 0;
        int n = answers.size();
        std::unordered_map<int,int> freq;
        for(int el : answers){
            if(freq.find(el) == freq.end()){
                freq[el] = 0;
            }            
            freq[el]++;
        }
        for(auto& [report,freqReport] : freq){
            int delta = report + 1;
            while(freqReport > 0){
                freqReport -= delta;
                popSize += delta;
            }
            // ok : say {10,10,10} and 3 here
            // add only once now
            // popSize += delta;
        }

        return popSize;
    }
};
