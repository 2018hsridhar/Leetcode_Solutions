/*
THOUGHT PROCESS :

URL = https://leetcode.com/problems/restore-ip-addresses/
93. Restore IP Addresses

Check string length from the getgo too ( can't be greater than 12 ( 3 * 4 ) , or less than 4 digits long )
Make sure to check for no leading zeroes too
Perform symbol check ahead of time ( nah - contains digits only here )
Backtracking tends to be recursive : make sure to investigate thy method signature and thy return values well here!

COMPLEXITY :
TIME = #-operations * (work/operation)
#-operations = b^d, in worst case = 121 nodes
work/operation =
SPACE COMPLEXITY = O(b^d) = (3^5-1)/2 = 121 nodes, at max
b = 3
d = 4
b^d = N here ( total number tree nodes)
Branching factor is fixed - max of 3 here

TEST CASES :

All zeroes : "0000"
All ones : "1111"
All 256 : "256256256256"
*/

class Solution 
{
    public List<String> restoreIpAddresses(String s) 
    {
        LinkedList<String> IPAddrs = new LinkedList<String>();
        if(s.length() < 4 || s.length() > 12)
            return IPAddrs; // empty object ( just return the address I guess here ) - wait we have sz too 
        int curDepth = 0;
        List<String> prefixList = new LinkedList<String>();
        helper(s, prefixList, 0, IPAddrs, curDepth);
        return IPAddrs;
    }
    
    // Even if you pass in an object, you will deepCopy said object, at each stage of the backtracking recursive tree
    // Can we utilize an Array.join() method here
    
    // List<String> strList = Arrays.asList("How", "To", "Do", "In", "Java");         
    // String joinedString = String.join(", ", strList); // Use of Iterable<> interface helps much here
    
    
    /*
    helper("1111",[],0,{},0)
        helper("1111",[],0,{1},1)
            helper("1111",[],0,{1,1},2)
                helper("1111",[],0,{1,1,1},3)
                    helper("1111",[],0,{1,1,1,1},4)
                    
    OK : passed all the mentioned test cases here, but there might be a trip up later
    */
    
    // It be substring backtracking which trips folks up big time here
    public void helper(String masterStr, List<String> prefixList, int rIdx, LinkedList<String> IPAddrs, int curDepth)
    {
        // [1] Terminating condition ( let's base it on depth as usual for finite n-ary trees)
        if(curDepth == 4)
        {
            //System.out.printf("Length of prefixList = %d\n", prefixList.size());
            if(rIdx == masterStr.length())
            {
                String validIP = String.join(".", prefixList);
                IPAddrs.addFirst(validIP);
            }
        }
        else
        {
            // assume for loop here is constant ( 3 times ) and string substring methods are quick
            // child prefix list is k, which can be considered marginal ( bounded by 4 anyways ), so O(K) ~= O(~) here
            for(int i = rIdx+1; i < rIdx + 4; ++i)
            {
                if(rIdx < masterStr.length() && i <= masterStr.length())
                {
                    String prefix = masterStr.substring(rIdx,i);
                    //System.out.printf("rIdx = %d AND prefix = %s\n", rIdx, prefix);
                    if(prefix.length() >= 2 && prefix.charAt(0) == '0')
                        return;
                    int subdomain = Integer.parseInt(prefix);
                    if(subdomain > 255 || subdomain < 0)
                        return;
                    
                    // [2] Recursive thinking
                    LinkedList<String> childPrefixList = new LinkedList<String>();
                    for(String el : prefixList)
                        childPrefixList.addLast(el);
                    childPrefixList.addLast(prefix);
                    helper(masterStr,childPrefixList, i, IPAddrs, curDepth + 1);    
                }
                else
                    return; // can't even get a prefix here
            }
        }
        
        
    }
    
}
