/*
URL = https://leetcode.com/problems/custom-sort-string/
791. Custom Sort String

Compelxity
Let A := len(order)
Let S := len(string)

TIME = O(S)
Space = O(A) ( EXP ) O(1) ( IMP ) 

Test Cases
(A) "abc", "ccccbbbbaaaa" => PASS
(B) "abc", "fegdjk" => PASS
(C) "abc", "defccccchjiaaaaajklbbbbb" => PASS
(D) "a", "aaaadefccccchjiaaaaajklbbbbbaaa" => PASS
(E) "bc", "bcabcdbcebcf" => PASS
(F) "zah", "zzzbcabcdhhhbcezzzbcf" => PASS
(G)



Time = 20 mins
*/
class Solution {
public:
    string customSortString(string order, string s) {
        std::stringstream ss;
        vector<int> countOrder(26,0);
        int n = s.size();
        for(int i = 0; i < n; ++i){
            char c = s.at(i);
            if(order.find(c) != std::string::npos){ // find character in order string
                int charIdx = static_cast<int>(c - 'a');
                countOrder[charIdx]++;
            }
        }
        // Go through order
        for(int j = 0; j < order.size(); ++j){
            char c = order.at(j);
            int charIdx = static_cast<int>(c - 'a');
            if(countOrder[charIdx] > 0){
                for(int k = 0; k < countOrder[charIdx]; ++k){
                    ss << c;
                }
            }
        }        
        // Go through originalString\order
        for(int i = 0; i < n; ++i){
            char c = s.at(i);
            int charIdx = static_cast<int>(c - 'a');
            if(countOrder[charIdx] == 0){
                ss << c;
            }
        }
        std::string result = ss.str();
        return result;      
    }
};
