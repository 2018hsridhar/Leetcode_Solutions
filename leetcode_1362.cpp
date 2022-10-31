/*
URL = https://leetcode.com/problems/closest-divisors/
1362. Closest Divisors

Also we do know the square root test 2, so leverage that as well!

*/
class Solution {
public:
    vector<int> closestDivisors(int num) {
        vector<int> results;
        pair<int,int> case1 = getClosest(num+1);
        pair<int,int> case2 = getClosest(num+2);
        if(abs(case1.first - case1.second) <= abs(case2.first - case2.second))
            return { case1.first, case1.second };
        else
            return { case2.first, case2.second };
        return results;
    }
    
private:
    pair<int,int> getClosest(int x){
        int curDist = INT_MAX;
        pair<int,int> result = make_pair(0,0);
        // int sqrt = sqrt(static_cast<double>(x)); // Paranthetical for static_cast<T>(val)
        int factorBound = static_cast<int>(floor(sqrt(x)));
        for(int i = 1; i <= factorBound; ++i){
            if(x % i == 0){
                int firstFac = i;
                int secondFac = x / i;
                if(abs(firstFac - secondFac) < curDist){
                    result.first = firstFac;
                    result.second = secondFac;
                }
            }
        }
        return result;
    }
    
};
