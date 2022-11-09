/*
We have only 100K as max val for nums : being O(N) is not atrocious here
We could binary search, but seems inefficient.
Handle leading and trailing 0's too.'

2443. Sum of Number and Its Reverse
URL = https://leetcode.com/problems/sum-of-number-and-its-reverse/description/

Misnomer due to carry operatoins.

Edge Cases
(A) 11 : 10 + 01
(B) 101 : 100 + 001
(C)
(D)
(E)

Radix bounded to 6 : 6 operations @ max here.

COMPLEXITY
TIME = O(N)
SPACE = O(1 ( EXP ) O(1) ( IMP ) 
Oh nonneg : remember this too!

*/
class Solution {

    public:
        bool sumOfNumberAndReverse(int num) {
            if(num == 0)
                return true;
            bool meetsCond = false;
            for(int i = 1; i <= num-1; ++i){
                int j = num - i;
                if(isReverse(i,j)){
                    meetsCond = true;
                    break;
                }
            }
            return meetsCond;       
        }

    private:
        // must be bigger of the nums though ( leading 0 issue ) 
        // you have a radix len.
        inline bool isReverse(int i, int j){
            // eventually, we get to one of the nums max Len too
            // the floor of either of them works :-).
            int myLen = std::max(std::floor(log10(j)) + 1, std::floor(log10(i)) + 1);
            int newNum = 0;
            for(int i = 0; i < myLen; ++i){
                newNum += std::pow(10,myLen - 1 - i) * (j % 10);
                 j /= 10;
            }
            return (newNum == i);
        }
};
