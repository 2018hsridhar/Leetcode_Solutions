/*

1196. How Many Apples Can You Put into the Basket
URL = https://leetcode.com/problems/how-many-apples-can-you-put-into-the-basket/

THOUGHT PROCESSES :
- AKIN TO COIN CHANGING
- ALWAYS SORT INPUTS
- Start from min element and maximize from there!

*/

class Solution {
public:
    int maxNumberOfApples(vector<int>& arr) 
    {
        int numApples = 0;
        int curW = 0;
        std::sort(arr.begin(), arr.end());
        for(int i : arr)
        {
            curW += i;
            if(curW > 5000)
            {
                break;
            }
            ++numApples;
        }
        return numApples;
    }
};
