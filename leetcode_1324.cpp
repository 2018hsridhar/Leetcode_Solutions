/*
URL = https://leetcode.com/problems/print-words-vertically/description/
1324. Print Words Vertically
30 mins to completion :-( ( expected faster ) 

Complexity
Let N := #-words
Let M := len(longest word)
Time = O(MN)
Space = O(MN) ( EXP ) O(1) ( IMP ) 

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]
^ We have a gotcha here : do not print all empty spaces too!
^ You can get the newly generated string, but take note that you read this as if it were some bizzare tree-like structure
*/
class Solution {
public:
    vector<string> printVertically(string s) {
        vector<string> myVerticalWords;
        vector<string> myTokens;
        char space_char = ' ';
        // https://www.tutorialspoint.com/c_standard_library/c_function_strtok.htm
        // https://stackoverflow.com/questions/1919626/can-i-get-a-non-const-c-string-back-from-a-c-string
        // https://stackoverflow.com/questions/11628346/c-usage-of-strtok-on-string
        // Integrating with old code is tricky.
        // get first token
        // Is something going on in strtok?
        char* token = strtok(const_cast<char *>(s.c_str()),  &space_char);
        int numRows = 0;
        while(token != nullptr){
            myTokens.push_back(token);
            numRows = std::max(numRows, static_cast<int>(strlen(token)));
            token = strtok(NULL, &space_char); // I do not understand strtok
        }
        // iterate over input, but in reverse
        // seems to be a memory thing here!
        int numTokens = myTokens.size();

        // c ~ index of the token we consum 
        // but this goes right to left -> oh dang!
        for(int r = 0; r < numRows; ++r){
            vector<char> newStrChars;
            bool hitCharFromRight = false; // start append op once we hit a char
            for(int c = numTokens-1; c >= 0; --c){
                std::string myToken = myTokens.at(c);
                // r ~ index in the token under consumption ( the word pos itself ) 
                if(r < myToken.size()){
                    if(myToken[r] != space_char && hitCharFromRight == false){
                        hitCharFromRight = true;
                    }
                    if(hitCharFromRight == true){
                        newStrChars.push_back(myToken[r]);
                    }
                } else {
                    if(hitCharFromRight == true){
                        newStrChars.push_back(space_char);
                    }
                }
            }
            std::string newRow(newStrChars.rbegin(), newStrChars.rend());
            myVerticalWords.push_back(newRow);
        }
        return myVerticalWords;
    }
};
