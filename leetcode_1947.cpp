/*
1947. Maximum Compatibility Score Sum
URL = https://leetcode.com/problems/maximum-compatibility-score-sum/

Oh no one of those dreaded bitmask questions
but the good news is that m,n goes only up to 8
And we have to choose a mentor for each student ( everyone gets paired up ) 

Suppose following : [111111] for 6 possible mentors. We have 6 cases here
[011111],[101111],[110111],..[111110] to select from <- check if any of these substrings have been solved for in a DP HM cache. Substr fixed size ( 8 ops only ) 
Set up seperate method for getting compatibility
2^16 <= 100K : not atrocious

START = 0
END = 

TIME TAKEN = 25 MINUTES TO SOLUTION
No runtime data overflow exp


*/
class Solution {
public:
    int maxCompatibilitySum(vector<vector<int>>& students, vector<vector<int>>& mentors) {
        int n = students.size();
        int i = n-1; // preferred not to keep var in scope of loop here : safety of scope.
        std::string myInitConfig(students.size(),'1');
        map<string,int> memo;
        int initIdx = 0;
        // 1. closer to use for local vars : more readability
        // 2. Avoid decl + assign : prefered straight initialization of variables !
        int glblMaxCompSum = internalMemo(memo,myInitConfig,initIdx, students,mentors);
        return glblMaxCompSum;
    }
    
private:
    // Leverage mutability of C++-style strings :-)
    int internalMemo(map<string,int>& memo, std::string& myConfig, int curIdx,
                     vector<vector<int>>& students, vector<vector<int>>& mentors){
        if(curIdx == students.size()){
            return 0;
        } else {
            int subProblemMaxScore = INT_MIN;
            for(int mPtr = 0; mPtr < students.size(); ++mPtr){
                if(myConfig.at(mPtr) == '1'){
                    int curSMPairCost = getComp(students.at(curIdx),mentors.at(mPtr));
                    std::string childConfig(myConfig); // it is a deep-copy : desired!
                    childConfig.at(mPtr) = '0';
                    int nextProbCost = internalMemo(memo,childConfig,curIdx+1,students,mentors);
                    subProblemMaxScore = std::max(subProblemMaxScore, curSMPairCost + nextProbCost);
                }
            }
            memo[myConfig] = subProblemMaxScore;
            return memo[myConfig];
        }
    }
    
    int getComp(const vector<int>& student, const vector<int>& mentor){
        int count = 0;
        for(int i = 0; i < student.size(); ++i){
            if(student.at(i) == mentor.at(i)) ++count;
        }
        return count;
    }
    
};
