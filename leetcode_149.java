class Solution 
{
    
    public int maxPoints(int[][] points) 
    {
        return lineThroughPoints(points);    
    }
    
    public int lineThroughPoints(int[][] points) 
	{
		int numPoints = 1;
		int n = points.length;
		if(n == 1)
		{
			return 1;
		}
		
		// Precompute DP array
		// Precompute hashmaps ( with default values too ) 
		// Array HMs not valid in JAVA	
		// Why wouldn't an array of HM be valid in JAVA? This boils down to a contiguous block of mem for pointers to HM?
		// You just can not have an array of generic type. Leave it at that here.
		
		List<HashMap<String,Integer>> numRightPoints = new ArrayList<HashMap<String,Integer>>();
		for(int i = 0; i < n; ++i)
		{
			// So instantiation allocs mem in heap, and returns a pointer to it, in current stack frame under the call
			HashMap<String,Integer> novel = new HashMap<String,Integer>();
			novel.put("D", 1);	// "D" => default value
			numRightPoints.add(i,novel);
		}
		
		// Sort the slope points in dictionary order ( incr(x), then incr(y) for tie-breaker cases of x ) 
		Arrays.sort(points, new Comparator<int[]>(){
			public int compare(int[] pOne, int[] pTwo)
			{
				if(pOne[0] < pTwo[0])
				{
					return -1;
				}
				else if ( pOne[0] > pTwo[0])
				{
					return 1;
				}
				else
				{
					if(pOne[1] < pTwo[1])
					{
						return -1;
					}
					else if (pOne[1] > pTwo[1])
					{
						return 1;
					}
					return 0;
				}
			}
		});
		
		// Verify correctness of the sort
		// for(int i = 0; i < n; ++i)
		// 	System.out.printf("[%d,%d]\n", numPoints[0], numPoints[1]);
		
		// int gcd = gcd(1,111111);
		// System.out.printf("Gcd = %d\n", gcd);
		// Is this because strings map to seperate addresses ( versus string values ). Something to exert caution on for sure!
		// 
		for(int i = n-1; i >= 0; --i)
		{
			int[] pairOne = points[i];		
			HashMap<String,Integer> ith_pair = numRightPoints.get(i);
			for(int j = n - 1; j >= i+1; --j)	// Should j be decreasing or increasing here? Do inspect for sure.
			{
				int[] pairTwo = points[j];
				int slope_num = pairTwo[1] - pairOne[1];
				int slope_den = pairTwo[0] - pairOne[0];
				// System.out.printf("For (i,j) = (%d,%d)\t Slope (num,den) = (%s,%s)\n", i, j, slope_num, slope_den);
				HashMap<String,Integer> jth_pair = numRightPoints.get(j);
				StringBuilder sb = new StringBuilder("");
				if(slope_den == 0)
				{
					sb.append("V");	// The vertical case : use diff character now					
				}
				else if ( slope_den != 0)
				{
					int gcd = gcd(slope_num, slope_den);
					if(gcd != 0)
					{
						slope_num /= gcd;
						slope_den /= gcd;
						sb.append(slope_num);
						sb.append(":");
						sb.append(slope_den);
					}
					else
					{
						sb.append("0");
					}
						
				}
				// There is a bug here with GCD -> investigate that!
				// System.out.printf("For (i,j) = (%d,%d)\t Slope (num,den) = (%s,%s)\n", i, j, slope_num, slope_den);
				String key = sb.toString();
				if(jth_pair.containsKey(key))
				{
					// System.out.printf("Accessing jth key = %s @ j = %d\n", key, j);
					int jth_pair_val = jth_pair.get(key);
					// System.out.printf("For key = [%s] @ i,j = [%d,%d] \t jth pair val = [%d]\n", key, i, j, jth_pair_val);
					// if(ith_pair.containsKey(key) && jth_pair_val > ith_pair.get(key)) ( this seems inaccurate )
					int newIFreq = jth_pair_val + 1;
					// System.out.printf("new i freq = %d\n", newIFreq);
					if(newIFreq >= numPoints)
					{
						numPoints = newIFreq;
					}
					// System.out.printf("Writing ith key = %s @ i = %d \n", key, i);
					ith_pair.put(key, newIFreq);	// new point here : add a 1 at least
					// Is this write operation NOT taking place? HUH?
				}
				else
				{
					ith_pair.put(key,2);	// new point here : add a 1 at least
					numPoints = Math.max(numPoints,2);
				}
			}
		}
		
		return numPoints;
  }
	
	// Know how to code this up from scratch as well too :-(
	// Wait isn't this based on the more maximal element too? Cuz one divides the other
	private int gcd(int a, int b)
	{
		// Zero case handling 
		if(a == 0 || b == 0)
		{
			return 0;
		}
		int max = a;
		int min = b;
		if(b > a)
		{
			max = b;
			min = a;
		}
		int gcd = min;
		int rem = max % min;	
		while(rem != 0)
		{
			max = min;
			min = rem;
			rem = max % min;
			if(rem == 0)
			{
				gcd = min;
				break;
			}
		}
		return gcd;
	}
}
