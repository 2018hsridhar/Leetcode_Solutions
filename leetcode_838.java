/*
URL = https://leetcode.com/problems/push-dominoes/
838. Push Dominoes

TEST CASES
(A) "R...." => "RRRRR"
(B) "....L" => "LLLLL"
(C) "L...R" => "L...R"
(D) "R...L" => "RR.LL"
(E) "L.....L.....L" => "LLLLLLLLLLLL"
(F) "......R......R......R...." => "RRRRRRRRRRRRR"
(G) "R...L" => "RR.LL"
(H) "R...LR.....LR....LR......RRR....R" -> yes
(I) "L"
(J) "R"
(K) "RL"
(L) "LR"
(M) ""RLRL...RLRL..LLLRRRR....L""
(N)
(O)

Complexity
Let N := length(dominoes string)
TIME = O(N)
Space = O(1) ( EXPLICIT ) O(1) ( IMPLICIT) 

Based on iteration direction, one dir will prove easier than the other.

*/
class Solution {
    public String pushDominoes(String dominoes) {
        char[] c_arr = dominoes.toCharArray();
        int lPtr = 0;
        int rPtr = 0;
        int n = c_arr.length;
        boolean haveL = false;
        boolean haveR = false;
        while(rPtr < n){
            if(c_arr[rPtr] == 'L'){
                haveL = true;
                if(!haveR){
                    while(lPtr < rPtr){
                        c_arr[lPtr] = 'L';
                        lPtr++;
                    }
                    haveL = false;
                    lPtr = rPtr + 1;
                    rPtr = rPtr + 1;
                } else if ( haveR ){
                    int curLeft = lPtr-1;
                    int curRight = rPtr;
                    // System.out.printf("[%d,%d]\n", lPtr,rPtr);
                    while(curLeft < curRight){
                        c_arr[curLeft] = 'R';
                        c_arr[curRight] = 'L';
                        curLeft++;
                        curRight--;
                    }
                    lPtr = rPtr + 1;
                    rPtr = rPtr + 1;
                    haveL = false;
                    haveR = false;
                }
            } else if ( c_arr[rPtr] == 'R'){
                if(haveR){
                    while(lPtr <= rPtr){
                        c_arr[lPtr] = 'R';
                        lPtr++;
                    }
                    lPtr = rPtr + 1;
                    rPtr = rPtr + 1;
                }
                haveR = true;
                lPtr = rPtr;
            } else {
                rPtr++;
            }
        }
        if(haveR){
            while(lPtr < n){
                c_arr[lPtr++] = 'R';
            }
        }
        return new String(c_arr);
    }
}
