https://leetcode.com/problems/longest-harmonious-subsequence/#/description
class Solution {
public:
    int findLHS(std::vector<int>& nums) 
    {
        int res = 0;
        if(nums.empty())
            return 0;
        typedef std::unordered_map<int,int> myMap;
        myMap numFreqs;
        // [1] 1 pass, init hm key->val pairs 
        // you should use a for-each loop here
        for(int const& x : nums)
            numFreqs[x]++;
        
        // [2] iterate over keys. if there's a (x+1,x-1) vals, calc abs diff. 
        //      MAXIMIZE this absolute difference
        for(myMap::iterator it = numFreqs.begin(); it != numFreqs.end(); ++it)
        {
            std::pair<int,int> keyVal = *it;
            int x = keyVal.first;
            int freq = keyVal.second; 
            //std::cout << x << '\t' << freq << std::endl;
            if(numFreqs.find(x+1) != numFreqs.end())
            {
                int otherFreq = numFreqs.at(x+1);
                int diff = freq + otherFreq;
                res = std::max(res,diff);
            }
            if(numFreqs.find(x-1) != numFreqs.end())
            {
                int otherFreq = numFreqs.at(x-1);
                int diff = freq + otherFreq; 
                res = std::max(res,diff);
            }
            
        }
        return res; 
    }
};
