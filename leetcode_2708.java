/*
URL = https://leetcode.com/problems/maximum-strength-of-a-group/
2708. Maximum Strength of a Group

Multiply all the positive values anyways
Multiply all the negative values evenly ... with the greatest beg values first

Hang on ... we can get case of all values equal to 0
Be very careful
Forming a group : can we avoid choosing any numbers too ... no ... must be a nonEMpty group
Seems negative positive split fares better too?
And the zero split too?
*/
class Solution {
    public long maxStrength(int[] nums) {
        long myMaxStrength = 1;
        Arrays.sort(nums);
        List<Integer> negNums = new ArrayList<Integer>();
        List<Integer> zeros = new ArrayList<Integer>();
        List<Integer> posNums = new ArrayList<Integer>();
        for(int x : nums) {
            if(x<0){
                negNums.add(x);
            } else if ( x == 0 ) {
                zeros.add(x);
            } else {
                posNums.add(x);
            }
        }
        if(negNums.size() == 0 && posNums.size() == 0) {
            return 0;
        } else {
            if(posNums.size() == 0 && negNums.size() == 1) {
                if(zeros.size() > 0){
                    return 0;
                }
                myMaxStrength = (long)(negNums.get(0));
                // no breka in switch or loop statements too!
            } else {
                for(int x : posNums) {
                    myMaxStrength *= x;
                }
                int i = 0;
                while(i < negNums.size()){
                    int nextIdx = i + 1;
                    if(nextIdx >= negNums.size()){
                        break;
                    }
                    myMaxStrength *= (negNums.get(i) * negNums.get(nextIdx));
                    i += 2;
                }
            }
        }
        return myMaxStrength;
    }
}
Console
/*
URL = https://leetcode.com/problems/maximum-strength-of-a-group/
2708. Maximum Strength of a Group

Multiply all the positive values anyways
Multiply all the negative values evenly ... with the greatest beg values first

Hang on ... we can get case of all values equal to 0
Be very careful
Forming a group : can we avoid choosing any numbers too ... no ... must be a nonEMpty group
Seems negative positive split fares better too?
And the zero split too?
*/
class Solution {
    public long maxStrength(int[] nums) {
        long myMaxStrength = 1;
        Arrays.sort(nums);
        List<Integer> negNums = new ArrayList<Integer>();
        List<Integer> zeros = new ArrayList<Integer>();
        List<Integer> posNums = new ArrayList<Integer>();
        for(int x : nums) {
            if(x<0){
                negNums.add(x);
            } else if ( x == 0 ) {
                zeros.add(x);
            } else {
                posNums.add(x);
            }
        }
        if(negNums.size() == 0 && posNums.size() == 0) {
            return 0;
        } else {
            if(posNums.size() == 0 && negNums.size() == 1) {
                if(zeros.size() > 0){
                    return 0;
                }
                myMaxStrength = (long)(negNums.get(0));
                // no breka in switch or loop statements too!
            } else {
                for(int x : posNums) {
                    myMaxStrength *= x;
                }
                int i = 0;
                while(i < negNums.size()){
                    int nextIdx = i + 1;
                    if(nextIdx >= negNums.size()){
                        break;
                    }
                    myMaxStrength *= (negNums.get(i) * negNums.get(nextIdx));
                    i += 2;
                }
            }
        }
        return myMaxStrength;
    }
}
