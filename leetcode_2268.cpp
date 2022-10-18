/*
2268. Minimum Number of Keypresses
URL = https://leetcode.com/problems/minimum-number-of-keypresses/
*/
class Solution {
public:
    int minimumKeypresses(string s) {
        int minPress = 0;
        int freq[26] = {0}; // 0-initialize your data : C++ will throw you bad stack mem :-)
        for(char& c : s){ // not a c-style string : how to quickly iterate?
            freq[c-'a']++; 
        }
        // std::sort(freq.begin(), freq.end());
        std::sort(freq,freq+26);
        int count = 0;
        for(int i = 25; i >= 0; --i){
            // cout << freq[i] << endl;
            if(freq[i] > 0){
                if(count >= 0 && count < 9){
                    minPress += freq[i];
                } else if ( count >= 9 && count < 18 ) {
                    minPress += freq[i]*2;
                } else if ( count >= 18 ){
                    minPress += freq[i]*3;
                }
                count++;
            }
        }
        return minPress;
    }
};
