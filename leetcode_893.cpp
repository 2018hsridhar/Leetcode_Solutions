/*
URL = https://leetcode.com/problems/groups-of-special-equivalent-strings/
893. Groups of Special-Equivalent Strings

Seems to be a nested hashmap problem TBH

Let N := len(words)
TIME = O(N)
SPACE = O(N) ( EXP ) O(1) ( IMPL ) 

TBH -> nested maps not even needed : just string,map<string>
Nonetheless, investigate why the bug here with nested maps!

10;23 - 10:42 ( 19 mins to solution ) 
*/
class Solution {
public:
    int numSpecialEquivGroups(vector<string>& words) {
        int numGrps = 0;
        map<string,map<string,int>> segs;
        for(string word : words){
            std::vector<char> evenChars;
            std::vector<char> oddChars;
            for(int i = 0; i < word.size(); ++i){
                if(i % 2 == 0 ){
                    evenChars.push_back(word.at(i));                    
                } else if ( i % 2 == 1 ) {
                    oddChars.push_back(word.at(i));
                }
            }
            std::sort(evenChars.begin(), evenChars.end());
            std::sort(oddChars.begin(), oddChars.end());
            std::string evenPrefix(evenChars.begin(), evenChars.end());
            std::string oddPrefix(oddChars.begin(), oddChars.end());
            // cout << word << "\t" << evenPrefix << "\t" << oddPrefix << endl;
            // [1] First map lookup
            if(segs.find(evenPrefix) == segs.end()){
                segs[evenPrefix] = map<string,int>(); // notice default-initialization : not a trivially-constructible type here :-)
            }
            map<string,int>& evenMp = segs[evenPrefix]; // woah : copy vs reference shit going here
            // [2] second map lookup
            if(evenMp.find(oddPrefix) == evenMp.end()){
                evenMp[oddPrefix] = 0;
            }
            evenMp[oddPrefix]++;
        }
        // https://stackoverflow.com/questions/4844886/how-can-i-loop-through-a-c-map-of-maps/27344958#27344958
        // for(auto const & ent1 : segs){
            // for(auto const & ent2 : ent1.second){
                // numGrps += 1;
            // }
        // }
        for(const auto& [k,v] : segs){
            for(const auto& [k2,v2] : v){
                numGrps++;
            }
        }
        return numGrps;
    }
};
