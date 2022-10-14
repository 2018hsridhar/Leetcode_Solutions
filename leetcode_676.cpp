/*
URL = https://leetcode.com/problems/implement-magic-dictionary/
*/
class MagicDictionary {
private:
    std::set<std::string> dict;
public:
    MagicDictionary() {
    }
    
    void buildDict(vector<string> dictionary) {
        for(const auto& word : dictionary){
            dict.insert(word); // insert preferable for readability
        }
    }
    
    bool search(string searchWord) {
        for(const auto& word : dict){
            if(isPalinOneOff(word,searchWord)){
                return true;
            }
        }
        return false;
    }
    
    bool isPalinOneOff(std::string a, std::string b){
        if(a.size() != b.size()){
            return false;
        }
        int i = 0;
        int countOff = 0;
        // printf("%s\t%s\n", a[i],b[b.size() - 1]);
        // printf("%c\t%c\n", a[i],b[b.size() - 1]);
        while(i < a.size()){
            if(a[i] != b[i]){
                // printf("%c\t%c\n", a[i],b[j]);
                countOff++;
                if(countOff == 2){
                    return false;
                }
            }
            i++;
        }
        return (countOff == 1);
    }
    
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary* obj = new MagicDictionary();
 * obj->buildDict(dictionary);
 * bool param_2 = obj->search(searchWord);
 */
