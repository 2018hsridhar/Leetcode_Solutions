/*
URL = https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/
1738. Find Kth Largest XOR Coordinate Value

XOR is commutative and associative, as a binary operation
Compleity
Let M,N := dims(matrix)
TIME = O(MN)
SPACE = O(MN) ( EXP -> say all unique ) ) O(1) ( IMP ) 
    ^ we can also append this to a set and execute the find max operation on a std::set :-)

    18 mins
    vector here : unique vals no matter 
*/
class Solution {
public:
    int kthLargestValue(vector<vector<int>>& matrix, int k) {
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> DP(m,vector<int>(n,0));
        std::vector<int> myXors;
        DP.at(0).at(0) = matrix.at(0).at(0);
        myXors.push_back(matrix.at(0).at(0));
        // top row
        for(int j = 1; j < n; ++j){
            DP.at(0).at(j) = matrix.at(0).at(j) ^ DP.at(0).at(j-1);
            myXors.push_back(DP.at(0).at(j));
        }
        // left col
        for(int i = 1; i < m; ++i){
            DP.at(i).at(0) = matrix.at(i).at(0) ^ DP.at(i-1).at(0);
            myXors.push_back(DP.at(i).at(0));
        }
        // the interior
        for(int i = 1; i < m; ++i){
            for(int j = 1; j < n; ++j){
                DP.at(i).at(j) = matrix.at(i).at(j) ^ DP.at(i-1).at(j-1) ^ DP.at(i-1).at(j) ^ DP.at(i).at(j-1);
                // this could be incorrecct too!
                myXors.push_back(DP.at(i).at(j));
            }
        }
        // https://stackoverflow.com/questions/13385348/sorting-sets-using-stdsort
        std::sort(myXors.begin(), myXors.end());
        auto it = myXors.end(); // try the other way instead
        while(k > 0){
            --it;
            --k;
        }
        // for(auto x : myXors){
            // printf("%d\t", x);
        // }
        int res = *it;
        return res;
    }
};
