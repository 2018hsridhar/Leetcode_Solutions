/*
URL = https://leetcode.com/problems/shortest-word-distance-ii/
244. Shortest Word Distance II

Complexity
Let N := len(words)
Time = calls * O(nlgn) + precompute_once * O(N) = calls * O(nlgn)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class WordDistance {
public:
    std::unordered_map<string, vector<int>> wordIndices;
    WordDistance(vector<string>& wordsDict) {
        for(int idx = 0; idx < wordsDict.size(); idx++){
            std::string myWord = wordsDict[idx];
            if(wordIndices.find(myWord) == wordIndices.end()){
                wordIndices[myWord] = vector<int>();
            }
            wordIndices[myWord].push_back(idx);
        }
    }
    
    // 5K calls to this method.
    int shortest(string word1, string word2) {
        int shortestDelta = INT_MAX;
        vector<int> indexOne = wordIndices[word1];
        vector<int> indexTwo = wordIndices[word2];
        set<int> sOne(indexOne.begin(), indexOne.end());
        set<int> sTwo(indexTwo.begin(), indexTwo.end());
        vector<int> allIndices;
        allIndices.insert(allIndices.end(), indexOne.begin(), indexOne.end());
        allIndices.insert(allIndices.end(), indexTwo.begin(), indexTwo.end());
        std::sort(allIndices.begin(), allIndices.end());
        cout << endl;
        for(int i = 0; i < allIndices.size() - 1; ++i){
            int firstEl = allIndices[i];
            int secondEl = allIndices[i+1];
            // check set memberships critera here
            int curDelta = INT_MAX;
            if(sOne.find(firstEl) != sOne.end() && sTwo.find(secondEl) != sTwo.end()){
                curDelta = abs(firstEl - secondEl);
            } else if(sTwo.find(firstEl) != sTwo.end() && sOne.find(secondEl) != sOne.end()){
                curDelta = abs(firstEl - secondEl);
            }
            shortestDelta = min(shortestDelta, curDelta);
        }
        return shortestDelta;
    }
};

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance* obj = new WordDistance(wordsDict);
 * int param_1 = obj->shortest(word1,word2);
 */
