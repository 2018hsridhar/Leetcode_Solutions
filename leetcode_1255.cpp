/*
URL = https://leetcode.com/problems/maximum-score-words-formed-by-letters/
1255. Maximum Score Words Formed by Letters

Classification : Combinatorial, Recursion, DP, Greedy 

Complexity
Let N := len(words)
TIME = O(2^n)
SPACE = O(N) ( IMP ) O(1) ( EXP ) 

28 minutes to a working solution :-)
*/
class Solution {
public:
    int maxScoreWords(vector<string>& words, vector<char>& letters, vector<int>& score) {
        int myMaxScore = 0;
        // create freq Map here
        vector<int> curLettersMap(26,0);
        for(char c : letters){
            curLettersMap.at(static_cast<int>(c-'a')) += 1;
        }
        myMaxScore = maxScoreWords(words,curLettersMap,score,0);
        return myMaxScore;
    }

private:
// return score = 0 if not possible to create word
// otherwise, take max of both sides
 int maxScoreWords(vector<string>& words, vector<int>& letterFreq, vector<int>& score, int idx) {
     int n = words.size();
     if(idx == n){
         return 0;
     } else {
         std::string curWord = words.at(idx);
         int curProblemScore = 0;
         vector<int> nextLetters(letterFreq.begin(), letterFreq.end());
         bool canUseWord = true;
         for(char curLet : curWord){
             int curIdx = static_cast<int>(curLet - 'a');
             nextLetters.at(curIdx) -= 1;
             if(nextLetters.at(curIdx) < 0){
                 canUseWord = false;
                 break;
             }
         }
         if(canUseWord){
            int scoreFromWord = 0;
            for(char x : curWord){
                scoreFromWord += score.at(static_cast<int>(x-'a'));
            }
            curProblemScore = std::max(
                maxScoreWords(words,letterFreq,score,idx+1),
                scoreFromWord + maxScoreWords(words,nextLetters,score,idx+1));
         } else if ( !canUseWord) {
            curProblemScore = maxScoreWords(words,letterFreq,score,idx+1);
             
         }
        return curProblemScore;
     }
 }
};
