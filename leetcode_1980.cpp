/*
1980. Find Unique Binary String
URL = https://leetcode.com/problems/find-unique-binary-string/

Complexity
Let N := len(input) = len(nums[i]) for any input.
input arr size bounded to 16 only ( seems like we can be efficient with a sort to begin with ).
Time = O(NlgN)
Space = O(1) ( EXP & IMP ) 

stime = 17:30
etime = 40:00
23 mins

Practice naming

(A) ["0"]
(B) ["1"]
(C) ["0000","0110","1100","1111"]
(D) ["00","10"]
(E) ["10","11"]
(F) ["000","001","010"]
(G)

*/
class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        std::string firstNotFoundBinaryString = "";
        int n = nums.size();
        std::sort(nums.begin(), nums.end());
        int testBinaryVal = 0;
        int numsPtr = 0;
        bool foundMissingBinaryNumber = false;
        while(numsPtr < nums.size()){
            int myBinaryEquivalent = getBinaryRepresentation(nums.at(numsPtr));
            // printf("Binary equiv = %d\n", myBinaryEquivalent);
            if(myBinaryEquivalent == testBinaryVal){
                testBinaryVal++;
                numsPtr++;
            } else {
                firstNotFoundBinaryString = getTextualEquivalentOfBinaryNumber(testBinaryVal,n);
                foundMissingBinaryNumber = true;
                break;
            }
        }
        // Handling of text and integrals.
        if(!foundMissingBinaryNumber){
            firstNotFoundBinaryString = getTextualEquivalentOfBinaryNumber(testBinaryVal, n);
        }
        return firstNotFoundBinaryString;
    }

private:

    std::string getTextualEquivalentOfBinaryNumber(int binaryNumber, int n){
        std::string textualEquiv = "";
        while(inputVal > 0){
            if(inputVal % 2 == 1){
                textualEquiv = '1' + textualEquiv;
            } else {
                textualEquiv = '0' + textualEquiv;
            }
            inputVal /= 2;
        }
        while(textualEquiv.size() < n){
            textualEquiv = '0' + textualEquiv;
        }
        return textualEquiv;
    }

    int getBinaryRepresentation(const std::string& num){
        int binaryRepresentation = 0;
        int count = 0;
        for(int i = num.size() - 1; i >= 0; --i){
            if(num.at(i) == '1'){
                binaryRepresentation += pow(2,count);
            }
            count++;
        }
        return binaryRepresentation;
    }
};
