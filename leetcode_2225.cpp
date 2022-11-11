/*
2225. Find Players With Zero or One Losses
URL = https://leetcode.com/problems/find-players-with-zero-or-one-losses/description/
*/
class Solution {
public:
    vector<vector<int>> findWinners(vector<vector<int>>& matches) {
        // Whom are the winners ( but not loosers )
        set<int> lostAMatch;
        for(auto match : matches){
            lostAMatch.insert(match.at(1));
        }
        // bizzare : I would have expected this to sort out
        // has to be LEXICOGRAPHICALLY HANDLED!
        // YOU CAN NOT JUST SET ON ONE DIM ONLY!
        // WOAH it is internaly broken instead!
        std::sort(matches.begin(), matches.end(), [](const vector<int>& matchOne, const vector<int>& matchTwo) -> bool {
            if(matchOne.at(0) < matchTwo.at(0)){
                return false;
            // } else {
                // return true; // this broke it
            // }
            } else if ( matchOne.at(0) > matchTwo.at(0)){
                return true;
            } else {
                return 0;
            }
        });
        cout << "HARI" << endl;
        vector<int> wonAllMatches;
        map<int,int> freqLoss;
        for(auto match : matches){
            int winnerOfMatch = match.at(0);
            int looserOfMatch = match.at(1);
            if(freqLoss.find(looserOfMatch) == freqLoss.end()){
                freqLoss[looserOfMatch] = 0;
            }
            freqLoss[looserOfMatch]++;
            if(lostAMatch.find(winnerOfMatch) == lostAMatch.end()){ // player never lost a match
                if(wonAllMatches.size() > 0){
                    // cout << "HERE1" << endl;
                    if(wonAllMatches.at(wonAllMatches.size() - 1) == winnerOfMatch){
                        continue;
                    } else {
                        wonAllMatches.push_back(winnerOfMatch);
                    }
                    // cout << "HERE2" << endl;
                } else {
                    wonAllMatches.push_back(winnerOfMatch);
                }
            }
        }
        reverse(wonAllMatches.begin(), wonAllMatches.end());
        vector<int> lostOnlyOneMatch;
        for(const auto& [k,v] : freqLoss){
            if(v == 1){
                lostOnlyOneMatch.push_back(k);
            }
        }
        vector<vector<int>> myWinners = {wonAllMatches, lostOnlyOneMatch };
        return myWinners;
    }
};
