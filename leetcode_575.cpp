//https://leetcode.com/problems/distribute-candies/#/description

class Solution {
public:
    int distributeCandies(vector<int>& candies) 
    {
        std::unordered_map<int,int> myMap; 
        int numTypes = 0;
        for(std::vector<int>::iterator it = candies.begin(); it != candies.end(); ++it)
        {
            int val = *it; 
            if(myMap.find(val) == myMap.end())
            {
                myMap.insert(std::make_pair(val,1));
                numTypes++; 
            }
            else
            {
                myMap[val]++;
            }
        }
        int maxAllowed = candies.size() / 2; 
        return std::min(maxAllowed,numTypes);
    }
};
