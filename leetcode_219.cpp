// https://leetcode.com/problems/contains-duplicate-ii/#/description

class Solution {
public:
    bool containsNearbyDuplicate(vector<int>& nums, int k) 
    {
        bool stat = false; 
        if(nums.empty() | nums.size() < 2)
            return false;
        // HM approach
        std::unordered_map<int,int> indexMap;
        for(int i = 0; i < nums.size(); ++i)
        {
            //std::cout << nums[i] << std::endl;
            if(indexMap.find(nums[i]) != indexMap.end())
            {
                int storedIdx = indexMap[nums[i]];
                //std::cout << "here\n";  
                if(std::abs(i-storedIdx) <= k) 
                {
                    stat = true;
                    break; 
                }
                else // update to cur HIT idx
                {
                    indexMap[nums[i]] = i;    
                }
            }
            else
            {
                indexMap[nums[i]] = i;
            }
        }
        return stat; 
    }
};
