/*
869. Reordered Power of 2
URL = https://leetcode.com/problems/reordered-power-of-2/

*/
class Solution {
public:
    bool reorderedPowerOf2(int n) {
        bool status = false;
        int i = 1;
        set<string> powers2;
        int threshold = static_cast<int>(std::pow(10,9));
        while(i <= threshold){
            string curTwo = std::to_string(i); // no `function-style` cast?
            std::sort(curTwo.begin(), curTwo.end());
            // printf("curTwo = %s\n", curTwo.c_str());
            powers2.insert(curTwo);
            i *= 2;
        }
        // avoid leading digit being a 0 too
        // will be handled by the sort though
        // ok ... you got a problem here
        // you can not just sort your inputs!
        // do the raw comparisons themselves
        // oh we can rearrange digits ... I forgot
        string strN = to_string(n); 
        std::sort(strN.begin(), strN.end());
        if(powers2.find(strN) != powers2.end()){
            status = true;
        }
        return status;
    }
};
