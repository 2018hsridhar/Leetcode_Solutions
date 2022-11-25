/*
1103. Distribute Candies to People
https://leetcode.com/problems/distribute-candies-to-people/description/
*/
class Solution {
public:
    vector<int> distributeCandies(int candies, int num_people) {
        vector<int> distribution(num_people, 0);
        int i = 0;
        int candiesToGive = 1;
        while(true){
            if(candies - candiesToGive > 0){
                distribution.at(i) += candiesToGive;
                candies -= candiesToGive;
                candiesToGive++;
            } else {
                distribution.at(i) += candies;
                break;
            }
            i = (i+1)%num_people;
        }
        return distribution;
    }
};
