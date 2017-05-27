// https://leetcode.com/problems/number-of-boomerangs/

class Solution {
public:
    // ITERATE over all pairs
    int numberOfBoomerangs(std::vector<std::pair<int, int>>& points) 
    {
        int res = 0;
        if(points.empty())
            return 0;
        // ITERATRE over all pairs
        for(std::vector<std::pair<int,int>>::iterator it = points.begin(); it != points.end(); ++it)
        {
            std::pair<int,int> myPoint = *it; 
            std::map<double,int> distCounts; 
            getDistances(myPoint, points, distCounts);
            res += countPointBoomerangs(distCounts); 
        }
        
        return res; 
    }
    
    // NOTE :: passing pointer to iterator from OUTER- while loop
    // std::vector<std::pair<int,int>>::iterator it != std::pair<int,int>* myPoint! TOTALLY DIFFERENT TYPES
    void getDistances(std::pair<int,int> myPoint, std::vector<std::pair<int,int>>& points, std::map<double,int>& distCounts)
    {
        for(std::vector<std::pair<int,int>>::iterator pairIt = points.begin(); pairIt != points.end(); ++pairIt)
        {
            std::pair<int,int> other = *pairIt; 
            double d = std::hypot(myPoint.first - other.first,myPoint.second - other.second);
            distCounts[d]++; 
        }
    }
    
    // COMBINATORIAL BASED CALCULATION FROM 'distCounts' map ... set to own method
    int countPointBoomerangs(std::map<double,int>& map)
    {
        int val = 0; 
        for(std::map<double,int>::iterator it = map.begin(); it != map.end(); ++it)
        {
            int x = it->second;
            val += x * (x-1); 
        }
        return val; 
    }
};
