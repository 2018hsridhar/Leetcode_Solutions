//https://leetcode.com/problems/combination-sum-ii/
// 1. all numbers are positive integers .. can ask if target = 0?
// 2. use old trick for duplicate prevention!
public class Solution {
   public List<List<Integer>> combinationSum2(int[] candidates, int target)
   {
      if(candidates == null)
      {
          return null;
      }
      LinkedList<List<Integer>> result = new LinkedList<List<Integer>>();
      Arrays.sort(candidates);
      LinkedList<Integer> path = new LinkedList<Integer>();
      helper(result,path, candidates,target,0);
      return result;
   }

   public void helper(LinkedList<List<Integer>> results, LinkedList<Integer> path, int[] candidates,
int target, int idx)
   {
      if(target == 0)
      {
         results.add(path);
      }
      else
      {
         // skip onsecutive elemens, while doing this processing!
         for(int i = idx; i < candidates.length; i++)
         {
            if(i > idx && candidates[i] == candidates[i-1])
            {
               continue;
            }
            if(target - candidates[i] >= 0)
            {
               LinkedList<Integer> newPath = (LinkedList)path.clone();
               newPath.add(candidates[i]);
               helper(results, newPath,candidates,target-candidates[i],i+1);
            }

         }
        }
    }
    // [10, 1, 2, 7, 6, 1, 5] and target 8,
    // sorted :: [1,1,2,5,6,7,10]
}
