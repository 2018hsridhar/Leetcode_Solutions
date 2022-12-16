/*
1016. Binary String With Substrings Representing 1 To N
URL = https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/description/
At best, only up to 500,500 testable combinations here.
(len = 1000: 1000 1's, 1 1000 ) type of thing ( sum of natural numbers ) 
Up to a specific n value, I can say that it will be a definete no!
Say that n is up to 1,000000 * 1K = 1 Bil ops : not bar perf ( 10^9)

Complexity
Time = O(N)
Space = O(1) ( EXP ) O(1) ( IMP ) 

Cases
(A) "0110101011111111111111111111000000000000000000000000010101010100010010100100101010100001" 100 => PASS
ACCEPTED
26 mins

*/
class Solution {
public:

    std::string getBinaryStringFromInteger(int x){
        std::string binaryString = "";
        while(x > 0){
            std::string rem = std::to_string(x % 2);
            x /= 2;
            binaryString += rem;
        }
        std:reverse(binaryString.begin(), binaryString.end());
        return binaryString;
    }

    // It is a string, not a number :-(
    // Do binary on that instead!
    bool numberIsASubString(string s, int n){
        bool isSubString = false;
        std:string binaryStringForm = getBinaryStringFromInteger(n);
        // cout << "Bin form " << binaryStringForm << endl;
        int lenBinaryStringForm = binaryStringForm.size();
        for(int i = 0; i < s.size(); ++i){
            if(i + lenBinaryStringForm <= s.size()){
                std::string mySubStr = s.substr(i, lenBinaryStringForm);
                // cout << mySubStr << endl;
                if(mySubStr == binaryStringForm){
                    isSubString = true;
                    break;
                }
            }
        }
        return isSubString;
    }

    bool queryString(string s, int n) {
        if(n > 500500){
            return false;
        }
        bool hasAllIntegersInRange = true;
        for(int number = 1; number <= n; ++number){ // inclusive range
            // cout << "Number = " << number << endl;
            if(!numberIsASubString(s,number)){
                hasAllIntegersInRange = false;
                break;
            }
        }
        return hasAllIntegersInRange;
    }
};
