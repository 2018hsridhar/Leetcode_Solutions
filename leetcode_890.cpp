/*
890. Find and Replace Pattern
URL = https://leetcode.com/problems/find-and-replace-pattern/

Complexity
Let W := len(words) and P := len(pattern) = len(word[i])
TIME = O(PW)
SPACE = O(26), which could be considered O(1) ( explicit ) and O(1) ( implicit ) 

Cases : 
(A) ["aaa","bbb","ccc"], "aaa" > all
(B) ["aaa","bbb","ccc"], "abc" > none
(C) ["aba","bcb","cde"], "bab" > ["aba","bcb"]
(D) ["abbcddc","abbabba","aaaaaaa"]
"abbcddc" => ["abbcddc"]

*/
class Solution {
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        // We technically do not have to initialize all types :-O. Notice the diff from JAVA!
        // JAVA forces a lot of heap-object initialization. C++ allows for declarations.
        std::vector<string> wordsMatching; // = std::vector<string>();
        std::map<char,char> patToWordLet; // = std::map<char,char>();
        std::unordered_set<char> visited; // = std::unordered_set<char>(); 
        for(const auto& word : words){
            bool wordMatches = true; // C++ compiler common error : unknown type name
            for(int i = 0; i < pattern.size(); ++i){
                char patLet = pattern.at(i);
                char wordLet = word.at(i);
                if(patToWordLet.find(patLet) == patToWordLet.end()){
                    if(visited.find(wordLet) == visited.end()) {
                        patToWordLet[patLet] = wordLet;            
                        visited.insert(wordLet);
                    } else {
                        wordMatches = false;
                        break;
                    }
                } else {
                    if(patToWordLet[patLet] != wordLet){
                        wordMatches = false;
                        break;
                    }
                }
            }
            if(wordMatches){
                wordsMatching.push_back(word);
            }
            patToWordLet.clear(); // C++ supports clearing iterators quickly.    
            visited.clear();
        }
        return wordsMatching;
    }
};
