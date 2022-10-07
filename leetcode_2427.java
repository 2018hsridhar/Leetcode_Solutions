/*
2427. Number of Common Factors
URL = https://leetcode.com/problems/number-of-common-factors/

Use strictness of inequality too
We could take an iterative approach ( from 1 to a 1000 ) 
But shouldn't Euclid's GCD algo render expeditious here
-> no : Euclid's approach will tank in some cases too

Time = O(1000) only test up to this!
Space = O(1) ( EXP & IMP ) 

Test Cases
(A) 1,1 => 1
(B) 1,3 => 1
(C) 3,3 => 1
(D) 3,9 => 2
(E) 9,9 => 3 { 1,3,9 }
(F)  
(G)
(H)

*/
class Solution {
    public int commonFactors(int a, int b) {
        int numFactors = 0;
        // Compilers can optimize loop bounds underneath
        for(int i = 1; i <= 1000; ++i){ 
            numFactors = (a % i == 0 && b % i == 0) ? numFactors + 1 : numFactors + 0;
        }
        return numFactors;
    }
}
