/*
Classic backtracking
784. Letter Case Permutation
URL = https://leetcode.com/problems/letter-case-permutation/

*/
class Solution {
public:
    vector<string> letterCasePermutation(string s) {
        std::vector<string> allPerms;
        std::vector<char> initSeq;
        helper(s,initSeq,0,allPerms);
        return allPerms;
    }
    
// Why in ASCII does [A-Z] precede [a-z]?
// Adding dynamically to the arg too : caution!
// Can deep copy -> but append op needed!
private:
    // https://stackoverflow.com/questions/33849637/convert-a-single-character-to-lowercase-in-c-tolower-is-returning-an-integer
    // Ref : pass as a ptr ( vs. const deep copy op )
    void helper(const string& s, std::vector<char>& mySeq, const int idx, vector<string>& allPerms){
        if(idx == s.size()){
            allPerms.push_back(std::string(mySeq.begin(), mySeq.end())); // also termed `range constructor`
            return;
        } else if ( idx < s.size()){
            vector<char> lower(mySeq); // deep copying on vectors :-)
            vector<char> upper(mySeq);
            if(islower(s.at(idx)))
            {
                upper.push_back(s.at(idx) - 32);
                lower.push_back(s.at(idx));
                helper(s,lower,idx+1,allPerms);
                helper(s,upper,idx+1,allPerms);
            } else if ( isupper(s.at(idx))){
                upper.push_back(s.at(idx));
                lower.push_back(s.at(idx) + 32);
                helper(s,lower,idx+1,allPerms);
                helper(s,upper,idx+1,allPerms);
            } else {
                lower.push_back(s.at(idx));
                helper(s,lower,idx+1,allPerms);
            }
        }
    }
};
