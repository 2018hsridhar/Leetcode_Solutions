/*
1726. Tuple with Same Product
URL = https://leetcode.com/problems/tuple-with-same-product/description/

All numbers are distinct -> we can sort without breaking here
Return all tuples too ( a,b,c,d ) ( none equal ).
Leverge the distinct property ~ seems open to sets.

Complexity
Let N := len(nums)
TIME = O(N^2)
SPACE = O(N) ( EXP ) O(1) ( IMP ) 

We have O(N^2) possible unique products -> can we do a counting here?
12 = 2,6,3,4 ( number of factors ) ( number of pairs )?
    Consider each other pairing as an inverse
    (2,6) -> (3,4) (4,3)  :: select a pair : 2 choices per each other pairing
    (6,2) -> (3,4) (4,3)    :: select a pair : 2 x if we reverse the pairing
    (3,4) -> (2,6),(6,2)    :: apply to each product pairing
    (4,3) -> (2,6),(6,2)

    If only 2 numbers to a product ~ we can safely ignore!
    Distinct : leverage distinct factors anyways ( just do a count of num factors ) ~ should be even ( not exactly -> takes [1,2,4,5,10] = 20 case : 20 % 1 == 1)
    Ok ... let us precompute all the possible products
    Handle case such as 4 / 2 = 2 : all distinct factors!
    We do the set check too!

Passing Cases
(A) [1,2,3,4,5,6,7,8]
(B) [1,2,4,8,16,32,64,128,256]
(C) [1,2,3,4,5,6,7,8,9,10,11,12,113,14,15,16,17,18,19,20]
(D) [1,2,3,4,5,6,7,8,9,10,11,12,113,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40]
(E) [3]
(F) [1,3,5,7]


28 mins
*/
class Solution {
public:
    int tupleSameProduct(vector<int>& nums) {
        int numSameProdTuples = 0;
        int n = nums.size();
        std::sort(nums.begin(), nums.end());
        set<int> myNums;
        for(int x : nums){
            myNums.insert(x);
        }
        // [1] Precomputation step : get count of unique tuples. Sorted too!
        map<int,int> prodFactorCounts;
        for(int i = 0; i < n; ++i){
            for(int j = i+1; j < n; ++j){
                int myProd = nums.at(i) * nums.at(j);
                if(prodFactorCounts.find(myProd) == prodFactorCounts.end()){
                    prodFactorCounts[myProd] = 2;
                } else {
                    prodFactorCounts[myProd] += 2;
                }
            }
        }
        // [2] Algorithm set match portoin
        for(const auto& [myProd, myNumFactors] : prodFactorCounts){
            if(myNumFactors % 2 == 0 && myNumFactors >= 4){
                int numPairs = myNumFactors / 2;
                int nMin1pairs = numPairs - 1;
                for(int i = 0; i < numPairs; ++i){
                    numSameProdTuples += 2 * (nMin1pairs * 2);     
                }
            }
        }
        return numSameProdTuples;
    }
};
