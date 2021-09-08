
class Solution
{

	/*
  287. Find the Duplicate Number
  URL = https://leetcode.com/problems/find-the-duplicate-number/
  
	We are allowed to mutate the input array too
	Typical approaches taken by candidate : utilizing a hashmap/hashset
		=> always probe if hashset can be used in place of hashmap : halve the in-memory storage here
	Note : return the first duplicate value with the MINIMAL INDEX too!
	Oh -> I see where we get tripped up here. Nevermind!
	
	Complexity
	Let N := number of elements in the array
	Space = O(N)
	Time = O(N)
	
	Using Standard sort function
	Space = O(1)
	Time = O(N) + O(NLogN) = O(NlogN)
	
	Input array is guaranteed to be between [1,n] inclusive
	where n := total array length!
	
	*/
    public int findDuplicate(int[] nums)
	{
    // int ans = hashmapSolution(array);
		int ans = msbToggleSolution(nums);
    return ans;
  }
	
	public int msbToggleSolution(int[] array)
	{
		for(int i = 0; i < array.length; ++i)
		{
			int val = array[i];
			int idx = val - 1;
			if(val < 0)
				idx = (int)Math.abs(val) - 1;
			if(array[idx] > 0)
				array[idx] *= -1;
			else
				return (int)Math.abs(val);
		}
		return -1;
	}
	
	// Utilize the hashmap/hashset as we go!
	public int hashmapSolution(int[] array)
	{
		HashSet<Integer> encountered = new HashSet<Integer>();
		for(int i = 0; i < array.length; ++i)
		{
			int key = array[i];
			if(!encountered.contains(key))
			{
				encountered.add(key);
			}
			else
			{
				return key;
			}
		}			
	
	return -1;
	}
}
