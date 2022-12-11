/*
URL = https://leetcode.com/problems/generalized-abbreviation/description/
320. Generalized Abbreviation

Complexity
Let N := len(word)
Time = O(2^N) ( each letter reps a choice of to or not to ) 
Space = O(N) ( EXP ) O(N) ( IMP ) 

26 mins
Had to shift around code blocks!

Test Cases
(A)
(B)
(C)
(D)
(E)
(F)

*/
class Solution {
public:
    vector<string> generateAbbreviations(string word) {
        std::vector<string> myAbbreviations;
        std::set<string> myAbbreviationsSet;
        int startIdx = 0;
        std::string seedAbbrev = "";
        internalHelper(word, startIdx, myAbbreviationsSet, seedAbbrev);
        for(const string& s : myAbbreviationsSet){
            myAbbreviations.push_back(s);
        }
        return myAbbreviations;
    }

// The substitution is the number of characters read too : nothing else!
// How to execute in the middle though?
// And avoid double counting?
// Recursive coding is not as trivial here.
private:
    void internalHelper(std::string& word, int startIdx, set<string>& myAbbreviationSet, std::string seedAbbrev){
        int n = word.size();
        if(startIdx == n){
            myAbbreviationSet.insert(seedAbbrev);
        } else if(startIdx < n){ // check your offsetting here!
            for(int i = startIdx; i < word.size() + 1; ++i){
                int myLen = (i - startIdx); // not past the string
                // Check if a number is amendable to the running string!
                if(myLen == 0){
                    std::string nextStr = seedAbbrev + word.at(startIdx);
                    internalHelper(word, startIdx + 1, myAbbreviationSet, nextStr);
                } else {
                    bool canAppendToRunningStr = true;
                    if(seedAbbrev.size() > 0){
                        if(!islower(seedAbbrev.at(seedAbbrev.size() - 1)) == true){
                            canAppendToRunningStr = false;
                        }
                    }
                    if(canAppendToRunningStr){
                        std::string nextStr = seedAbbrev + to_string(myLen);
                        // cout << nextStr << endl;
                        int nextStartIdx = startIdx + myLen;
                        internalHelper(word, nextStartIdx, myAbbreviationSet, nextStr);
                    }
                }
            }
        }
    }
};
