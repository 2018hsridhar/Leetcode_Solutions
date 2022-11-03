/*
823. Binary Trees With Factors
URL = https://leetcode.com/problems/binary-trees-with-factors/

Exert caution with case of equal trees vs non-equal trees : 
E.g. 4 -> (2,2) VS 4 -> (3,5) & 4 -> (5,3)

Restricted to only this integers too -> take note!
HM for the DP size?

No factor of <1> here too.

Complexity
Let N := len(arr)
TIME = O()
SPACE = O() ( EXP ) O() ( IMP ) 

Cases
(A) [2,4,8,16] => 23
(B) [2,4,8]
(C) [2,4,5,10]
(D) [2,4,5,10]  => remedy
    ^ ***
    
    Algo is right => 20 mins
    Forgetting a moduland manipulatino here 
    
*/
class Solution {
public:
    int numFactoredBinaryTrees(vector<int>& arr) {
        using ll = long long;
        ll numTrees = 0;
        std::sort(arr.begin(), arr.end());
        std::map<int,ll> numTreesMap;
        int n = arr.size();
        const ll moduland = static_cast<ll>(pow(10,9) + 7);
        for(int i = 0; i < n; ++i){
            ll curSubProblem = 1;
            ll el = static_cast<ll>(arr.at(i));
            // cout << "------------" << endl;
            // cout << arr.at(i) << endl;
            for(int j = 0; j < i; ++j){                 // Factor set limited anyways
                ll factor1 = static_cast<ll>(arr.at(j));
                ll factor2 = static_cast<ll>(el / factor1);
                // cout << factor1 << endl;
                // cout << factor2 << endl;
                if(el % factor1 == 0){
                    if(numTreesMap.find(factor1) != numTreesMap.end() && numTreesMap.find(factor2) != numTreesMap.end()){
                        curSubProblem += (numTreesMap[factor1] * numTreesMap[factor2]);
                    }
                }
            }
            numTreesMap[el] = curSubProblem;
        }
        for(const auto& entry : numTreesMap){
            numTrees += static_cast<ll>(entry.second);
        }
        numTrees %= moduland;
        return static_cast<int>(numTrees);
    }
};
