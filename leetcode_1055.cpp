/*
1055. Shortest Way to Form String
URL = https://leetcode.com/problems/shortest-way-to-form-string/

Complexity
TIME = O(N^2)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

Cases:
(A) "xyz"
"xzyxyzxyz" => 4
(B) "abcd"
"abcdabcde" => -1
(C) "abcdefgh"
"abefhg" => 2
(D)
(E)

*/
class Solution {
public:
    int shortestWay(string source, string target) {
        int n = target.size();
        int glblShortest = n; // n pieces max anyways
        vector<int>DP(n,INT_MAX);
        for(int i = n-1; i >= 0; --i){
            int curGlblShortest = 10000; // stupidly high value instead
            int ptrS = 0;
            int ptrT = i;
            int deltaS = 0;
            while(ptrS < source.size() && ptrT < target.size()){
                if(source.at(ptrS) == target.at(ptrT)){
                    if(i == n-1){   
                        curGlblShortest = 1;
                        break;
                    } else {
                        deltaS++;
                        if(i + deltaS < n){
                            curGlblShortest = std::min(curGlblShortest, 1 + DP.at(i+deltaS)); // data overflow here!
                        } else if ( i + deltaS == n){
                            curGlblShortest = 1;
                        } else {
                            break;
                        }
                        ++ptrT;
                        ++ptrS;
                    }
                } else {
                    ++ptrS;
                }
            }
            DP.at(i) = curGlblShortest;
            if(DP.at(i) >=  10000) {
                return -1; // could not find at this point
            }
        }
        glblShortest = DP.at(0);
        return glblShortest;
    }
};
