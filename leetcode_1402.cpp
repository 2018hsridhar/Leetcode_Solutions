/*
URL = https://leetcode.com/problems/reducing-dishes/description/
1402. Reducing Dishes

Greedy sort algo
the all pos/all 0 case is trival to solve for
the neg case is trickier though

10 mins to solution :-)

Get a running sum?

EXP1    9*1 + -8*2 + -1*3 + 0*4 + 5*5
EXP2    -8*1 + -1*2 + 0*3 + 5*4 = > EXP1 - (9--8--1-0-5)
EXP3    -1*1 + 0*2 + 5*4 = EXP2 - (--8 --1 -0 - 5)

(A) [-9,-8,-1,0,5]
(B)
(C)
(D)
(E)

O(NlgN) or O(N) Ideal Complexity
Not O(N^2)

*/
class Solution {
public:
    int maxSatisfaction(vector<int>& satisfaction) {
        int myMax = 0;
        std::sort(satisfaction.begin(), satisfaction.end());
        int runSum = accumulate(satisfaction.begin(), satisfaction.end(), 0);
        int initMax = 0;
        size_t n = satisfaction.size();
        for(int i = 0; i < n; ++i){
            initMax += (i+1)*satisfaction.at(i);
        }        
        myMax = initMax;
        // now test the other maxes
        for(int i = 1; i < n; ++i){
            initMax -= runSum;
            myMax = std::max(myMax,initMax);
            runSum -= satisfaction.at(i-1);
        }
        myMax = std::max(myMax,0); // discard 'em all
        return myMax;
    }
};
