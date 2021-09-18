class Solution {
    public static int maximumSwap(int num) 
    {
      if(num < 10) return num;
      String s = num + "";
      char[] c_arr = s.toCharArray();
      int max = num;
      // length is not proper here
      for(int i = 0; i < c_arr.length-1; ++i)
      {
        for(int j = i + 1; j < c_arr.length; ++j)
        {
          int testVal = makeValFromSwap(c_arr, i, j);
          max = Math.max(max,testVal);
        }
      }
      
      
      return max;
    }
  
  // Deep copy and space are both O(D) here
    public static int makeValFromSwap(char[] c_arr, int i, int j)
    {
        char[] new_arr = new char[c_arr.length];
        for(int k = 0; k < c_arr.length; ++k)
          new_arr[k] = c_arr[k];
      
        swap(new_arr, i, j);
        int val = 0;
        for(int k = c_arr.length - 1; k >= 0; --k)
          val += (int)Math.pow(10,(new_arr.length - 1) - k) * Character.getNumericValue(new_arr[k]);
        
        return val;
    }
  
    public static void swap(char[] input, int i, int j)
    {
      char temp = input[i];
      input[i] = input[j];
      input[j] = temp;
    }
}
