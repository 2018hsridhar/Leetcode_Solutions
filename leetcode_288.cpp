/*
21 mins to implement : in C++ too
URL = https://leetcode.com/problems/unique-word-abbreviation/
288. Unique Word Abbreviation

*/
class ValidWordAbbr {
private:
    // most std containers/templates are lowercase only.
    std::map<std::string,unordered_set<std::string>> myDict;
public:
    ValidWordAbbr(vector<string>& dictionary) {
        for(auto& entry: dictionary){
            std::string abbrev = getAbbrev(entry);
            // Can use `.` operator on identifiers -> not types!
            if(myDict.find(abbrev) == myDict.end()){
                // Type construction taking place here
                myDict[abbrev] = std::unordered_set<std::string>();
            }
            myDict[abbrev].insert(entry);
        }
    }
    
    bool isUnique(string word) {
        std::string wordAbbrev = getAbbrev(word);
        if(myDict.find(wordAbbrev) == myDict.end())
            return true;
        else {
            // https://stackoverflow.com/questions/9087217/how-does-one-iterate-through-an-unordered-set-in-c
            // This feels cumbersome : no Java Iterable jeez
            if(myDict[wordAbbrev].size() == 1){
                auto setItr = myDict[wordAbbrev].begin();
                while(setItr != myDict[wordAbbrev].end()){
                    std::string curEl = *setItr;
                    if(curEl.compare(word) == 0){
                        return true;
                    }
                    setItr++;
                }
            }
        }
        return false;
    }
    
    // Prefered to const-guard : need different interfaces/api methods!
    // So many std::string() instantiations! GAAAH
    std::string getAbbrev(std::string& word){
        int wordLen = word.size();
        // https://www.techiedelight.com/convert-char-to-string-cpp/
        // This is cumbersome IMHO
        string firstLet = std::string(1,word.at(0));
        string lastLet = std::string(1,word.at(wordLen-1));
        if(wordLen == 2){
            return firstLet.append(lastLet);
        } else {
            // C++ dislikes `unknown types`
            std::string res_one = firstLet.append(std::to_string(wordLen-2));
            std::string res_two = res_one.append(lastLet);
            return res_two;
            // return std::string(firstLet,std::string(std::to_string(wordLen-2),lastLet));
        }
    }
};

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr* obj = new ValidWordAbbr(dictionary);
 * bool param_1 = obj->isUnique(word);
 */
