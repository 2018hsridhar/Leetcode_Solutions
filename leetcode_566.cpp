// https://leetcode.com/problems/reshape-the-matrix/#/description

class Solution {
public:
    vector<vector<int>> matrixReshape(vector<vector<int>>& nums, int r, int c) 
    {
        int r1 = nums.size();
        int c1 = nums[0].size();
        if(r == r1 && c == c1 || r1*c1 != r*c) return nums; 
        std::vector<std::vector<int>> results(r, vector<int>(c, 0));
        int rW = 0, rC = 0;
        for(int i = 0; i < r1; ++i)
        {
            for(int j = 0; j < c1; ++j)
            {
                results.at(rW).at(rC) = nums[i][j];
                rC++;
                if(rC == c)
                {
                    rW++;
                    rC = 0;
                }
            }
        }
        
        return results; 
    }
};

        // std::vector<std::vector<int>> results(r, vector<int>(c, 0)); // WORKS
        // SET DIMENSIONS OF NEW MATRIX ... uhh, this failed
        // std::vector<std::vector<int>> results(r);
        // for(int i = 0; i < r; ++i)
        // {
        //     std::vector<int> temp(c, 0);
        //     results.push_back(temp); 
        // }
        // cout << "T1\n";
        // results[0][0] = 1; // this is just invalid in c++? perhaps need to set type here? 
        // //results.at(0).at(0) = 1; 
        // cout << "T2\n";
