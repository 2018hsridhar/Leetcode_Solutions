/*
1023. Camelcase Matching
URL = https://leetcode.com/problems/camelcase-matching/

Compleity
Let Q := len(queries)
Let P := len(pattern)
Let W :+ len(max(queries[i])) i \in 1 to n
Time = O(QW)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Unit Tests:
(A) ["ooAar","abaoA","oooo","A","uA","oA", "oAbcd", "oAbcdE"], pattern = "oA"
    => [True, True, False, False, False, True, True, False]
    -> PASS
(B) ["aaaaabbbccc","abc","ab","cba","aDEFbGHIcjkl"], pattern = "abc"
    => [True, True, False, False, True]
(C)
(D)
(E)

21 mins
*/
class Solution {
public:
    vector<bool> camelMatch(vector<string>& queries, string pattern) {
        vector<bool> matching;
        for(string& query : queries){
            bool matchesCamelCase = true;
            int queryPtr = 0;
            int patternPtr = 0;
            int qLen = query.size();
            int pLen = pattern.size();
            while(queryPtr < qLen && patternPtr < pLen){
                // islower(c) and isupper(c) methods should assist.`
                char qC = query.at(queryPtr);
                char pC = pattern.at(patternPtr);
                if(islower(qC) && islower(pC)){
                    if(qC == pC){
                        queryPtr++;
                        patternPtr++;
                    } else {
                        queryPtr++;
                    }
                } else if ( isupper(qC) && isupper(pC)){
                    if(qC == pC){
                        queryPtr++;
                        patternPtr++;
                    } else {
                        matchesCamelCase = false;
                        break;
                    }
                } else if ( islower(qC) && isupper(pC)){
                    queryPtr++;
                } else if ( isupper(qC) && islower(pC)){
                    matchesCamelCase = false;
                    break;
                }
            }
            // cout << matchesCamelCase << endl;
            if ( patternPtr < pLen){
                matchesCamelCase = false;
            }
            while (queryPtr < qLen){
                if(islower(query.at(queryPtr))){
                    queryPtr++;
                } else {
                    matchesCamelCase = false;
                    break;
                }
            }
            matching.push_back(matchesCamelCase);
        }
        return matching;
    }
};
