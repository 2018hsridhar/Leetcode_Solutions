/*

12 min fix : got it !
Test Cases
(A) "III" => 1234
(B) "I" => 12
(C) "DDD" => 4321
(D) "DDDI" => 43215
(E) "DIDID" => FIX!
    
(F) "IDIDI" => FIX
(G) "DIDIIDIIIIDDDIDDIIIIDDID" => CORRECT
(H) => "IIIDIDIIDIIIIDDDIDDIIIIDDIDIII" => PASS
(I) => "IDIDI" => REMEDY THIS ONE!
(J) "IIIIIIIDDDDD" => YES

*/
class Solution {
public:
    vector<int> findPermutation(string s) {
        vector<int> foundPerm;
        char curChar = s.at(0); // strings indexable just like c++ vectors :-)
        int i = 1;
        int n = s.size();
        int curVal = 1;
        int lPtr = 1;
        int windLen = 1;
        bool hitD = s.at(0) == 'D' ? true : false;
        while(i < n){
            // printf("curChar = %c \t s.at(i) \ %c \n", curChar, s.at(i));
            if(curChar == 'I' && !hitD)
            {
                foundPerm.push_back(curVal);
                lPtr++;
            }
            if(s.at(i) == curChar){
                curVal++;
                windLen++;
            } else { // restart 
                if(curChar == 'D'){
                    curVal++;
                    for(int j = curVal; j >= lPtr; --j){
                        foundPerm.push_back(j);
                    }
                    lPtr = curVal+1;
                } else if ( curChar == 'I'){
                    hitD = true;
                    // go ignore if we have only a len = 1 here too!!!
                    if(windLen == 1){
                        curChar = s.at(i);
                        windLen = 1;
                    } else if ( windLen > 1 ) {
                        // [1,2,3] comes here
                        for(int j = lPtr; j <= curVal; ++j){
                            foundPerm.push_back(j);
                        }
                        lPtr = curVal+1;
                    }
                    curVal++;
                }
                curChar = s.at(i);
                windLen = 1;
            }
            ++i;
        }
        // printf("lptr = %d \t curVal = %d\n", lPtr, curVal);
        // check fin Len here too
        curVal++;
        if(s.at(n-1) == 'D'){
            for(int j = curVal; j >= lPtr; --j){
                foundPerm.push_back(j);
            }
        } else if ( s.at(n-1) == 'I'){
            for(int j = lPtr; j <= curVal; ++j){
                foundPerm.push_back(j);
            }
        }
        return foundPerm;
    }
};
