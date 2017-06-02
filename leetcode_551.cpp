// https://leetcode.com/problems/student-attendance-record-i/#/description

class Solution {
public:
    // string's also possess iterators too ... I'm surrpised!
    // also note CONST (const_iterator) and REV (reverse_iterator)iterators too!
    // iterators do not seem as AMENABLE to point index manipulations, to actual indices or pointers themselves! GAAHHHH!
    bool checkRecord(std::string s) 
    {
            bool stat = true; 
            unsigned int idx = 0;
            unsigned int aCount = 0;
            unsigned int lCount = 0;
            // more than 2 continuous L's ... oh dang! need idx checker for that! 
            for(std::string::const_iterator it = s.cbegin(); it != s.cend(); ++it, ++idx)
            {
                const char c = (char)*it;
                if(c == 'A') 
                {
                    aCount++;
                }
                else if(c == 'L')
                {
                    lCount++; 
                    
                }
                if(aCount > 1 || lCount > 2)
                {
                    stat = false; 
                    break;
                }
            }
            return stat; 
    }
};
