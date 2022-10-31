-/*
1451. Rearrange Words in a Sentence
URL = https://leetcode.com/problems/rearrange-words-in-a-sentence/

Ripped apart from radix sort
Leverage unordered maps here too
Trim the final space of the returned output
Lower case every word -> capitalize the first word on your return output though!

Leverage c++ built-in ordered maps!
https://stackoverflow.com/questions/14463853/how-to-ensure-that-a-stdmap-is-ordered

*/
class Solution {
public:
    string arrangeWords(string text) {
        stringstream oss;
        std::map<int, vector<string>> mp;
        istringstream iss(text);
        string word;
        while( iss >> word ) {
            int curWordLen = word.size();
            if(mp.find(curWordLen) == mp.end()){
                mp[curWordLen] = std::vector<string>();
            }
            if(isupper(word.at(0))) {
                word.at(0) = word.at(0) - 'A' + 'a'; // adjustment
            }
            mp[curWordLen].push_back(word);
            // cout << word << endl;
        }
        // Radix-sort esque append operation now!
        for(const auto& [k,v] : mp){
            for(const auto& word : v){
                oss << word;
                oss << " ";
            }
        }
        // string result(oss); // can not initialize a c++ string directly froma stream stream!
        string result(oss.str()); // this initialization works though
        result.at(0) = result.at(0) - 'a' + 'A';
        return result.substr(0,result.size() - 1);
    }
};
