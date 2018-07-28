public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        LinkedList<List<Integer>> results = new LinkedList<List<Integer>>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        int remain = n; int start = 1;
        helper(results,path,1,k,remain);
        return results;
    }
    
    public void helper(LinkedList<List<Integer>> results, LinkedList<Integer> path, int start, int k, int remain)
    {
        if(remain < 0 || k < 0) return;
        else if (remain == 0 && k == 0) results.add(path);
        else
        {
            for(int i = start; i <= 9; i++)
            {
                LinkedList<Integer> newPath = (LinkedList)path.clone();
                newPath.add(i);
                helper(results,newPath,i+1,k-1,remain-i);
            }
        }
    }
    
}

// https://leetcode.com/problems/combination-sum-iii/

