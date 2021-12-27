/*
295. Find Median from Data Stream
https://leetcode.com/problems/find-median-from-data-stream/
*/
class MedianFinder 
{
    double median;
    // is initialization here even permitted to begin with?
    ArrayList<Integer> nums;

    public MedianFinder() 
    {
        median = 0.0;
        nums = new ArrayList<Integer>();       
    }
    
    public void addNum(int number) 
    {
		if(nums.size() == 0)
			{
				// System.out.printf("Adding first el = %d\n", number);
				nums.add(number);
			}
			else	// assume your preceding input maintains the sorted invariant as well
			{
				int firstEl = nums.get(0);
				int lastEl = nums.get(nums.size() - 1);
				if(number >= lastEl)
				{
					// System.out.printf("Adding lastEl = [%d]\n", lastEl);
					nums.add(number);
				}
				else if ( number <= firstEl )
				{
					nums.add(0, number);
				}
				else // binary search right position
				{
					int low = 0;
					int high = nums.size() - 1;	// Must be this ( imagine singleton case and high = 1 here . That'd be bad! )
					while(low <= high)
					{
						int mid = (low + high)/2;
						int midEl = nums.get(mid);
						if(midEl == number)
						{
							nums.add(mid + 1,number);
							return;
						}
						else if ( midEl < number ) // must insert to the right now
						{
							if(mid < high)
							{
								int next = mid + 1;
								int nextEl = nums.get(next);
								if(nextEl < number)
								{
									low = mid + 1;	// set here
								}
								else if ( midEl <= number && nextEl >= number )
								{
									nums.add(next,number);
									return;
								}
							}
							else if ( mid == high)
							{
								nums.add(number);
								return;
							}
						}
						else if ( midEl > number ) // Cleary you never check your right here anyways. No point. Check left
						{
							if(mid > 0)
							{
								int prev = mid - 1;
								int prevEl = nums.get(prev);
								if(prevEl <= number && number <= midEl)
								{
									nums.add(mid,number); // one plus preceding here
									return;
								}
								else if ( prevEl > number)
								{
									high = mid - 1;
								}
							}
							else if ( mid == 0)
							{
								nums.add(0, number);
								return;
							}
						}
					}
				}
			}
    }
    
    public double findMedian() 
    {
        if(nums == null || nums.size() == 0)
        {
            return 0.0;
        }
        int length = nums.size();
        // System.out.println(length);
        boolean isEven = (length % 2 == 0);
        // System.out.println(isEven);
        if(isEven == true)
        {
            // System.out.printf("here in length = even\n");
            int firstEl = nums.get(length / 2);
            int secondEl = nums.get((length / 2 ) - 1);
            // System.out.printf("firstEl = %d \t secondEl = %d\n", firstEl, secondEl);
            median = 0.5 * (firstEl + secondEl);
        }	
        else if ( isEven == false )
        {
            // System.out.printf("Here in len = odd\n ");
            median = (nums.get(length / 2));
        }
      return median;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

