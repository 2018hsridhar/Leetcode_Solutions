/*
1010. Pairs of Songs With Total Durations Divisible by 60
URL = https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

Complexity
Let N := len(time)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
    ... make it O(1) space by storing the counts only too!
60 keys in the map only ( % 60 in rnage of [0,59] only ) 
Approach by pairs only
Note : not all pairs are unique.
    But that should be fine

huh 34/35 test caes : code seems right
15 mins to solution :-)

Cases
(A) [60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60] => PASS
*/
class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int myNumPairsDivisibleBy60 = 0;
        int n = time.size();
        // [1] Build up our map
        map<int,int> modFreq;
        for(int el : time){
            int remainder = el % 60;
            if(modFreq.find(remainder) == modFreq.end()){
                modFreq[remainder] = 0;
            }
            modFreq[remainder]++;
        }
        // [2] Iterate over keys ( with special case ofc ) 
        for(const auto [remainder, freqOfRemainder] : modFreq){
            // Guard condition to avoid double couting ( 20-40 and 40-20 ) 
            if(remainder <= 30){
                if(remainder == 0 || remainder == 30){
                    // What a weird case
                    using ll = long long;
                    ll shiftedFreq = freqOfRemainder - 1;
                    ll temp = static_cast<ll>((shiftedFreq) * (shiftedFreq + 1));
                    int delta = static_cast<int>(temp / 2);
                    myNumPairsDivisibleBy60 += delta;
                } else {
                    int remainderComplement = 60- remainder;
                    if(modFreq.find(remainderComplement) != modFreq.end()){
                        int freqRem = modFreq[remainder];
                        int freqRemComp = modFreq[remainderComplement];
                        myNumPairsDivisibleBy60 += (freqRem * freqRemComp);
                    }
                }
            }
        }
        return myNumPairsDivisibleBy60;
    }
};
