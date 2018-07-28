// https://leetcode.com/problems/pascals-triangle/

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        if(numRows == 0)
        {
            return res;
        }
        else
        {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(1);
            res.add(temp);
            while(numRows >= 2)
            {
                ArrayList<Integer> newList = new ArrayList<Integer>();
                newList.add(1);
                for(int i = 0; i < temp.size()-1; i++)
                {
                    int newSum = temp.get(i) + temp.get(i+1);
                    newList.add(newSum);
                }
                newList.add(1);
                res.add(newList);
                temp = newList;
                numRows--;
            }
        }
        return res;
    }
}
