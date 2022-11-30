/*
URL = https://leetcode.com/problems/lexicographical-numbers/
386. Lexicographical Numbers

This problem advantages folks familiar with the radix sort algorithm
But it can also be implemented recursively too
Just execute bounds checking in the process as well ( as n is known )
Exec ten shift ( multiply by 10 ) and then add again :-)

Cases
(A) 1 -> PASS
(B) 100 -> PASS
(C) 50000 -> PASS
(D)
(E)

6 mins to solution
*/
class Solution {
public:
    vector<int> lexicalOrder(int n) {
        vector<int> lexResults;
        int seed = 0;
        genNumbers(lexResults, seed, n); // ignore 0 here ( OOB ) 
        return lexResults;
    }

private:
    void genNumbers(vector<int>& results, int seed, int n){
        for(int i = 0; i <= 9; ++i){
            int nextSeed = (seed * 10)+i;
            if(1 <= nextSeed && nextSeed <= n){
                results.push_back(nextSeed);
                // make recursive call before going to next portion of the loop
                genNumbers(results,nextSeed, n);              
            }
        }
    }
};
