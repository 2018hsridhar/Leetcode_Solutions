Merge sort algorithm

				 0  1  2  3  4  5  6
array = [8, 5, 2, 9, 5, 6, 3]
answer= [2, 3, 5, 5, 6, 8, 9]

Inputs : 
	- integer type
	- negatives
	- at least one elememnt

Strategy : Recursive ( Divide-and-Conquer ) : Bottom-Up ( subproblem directionality )

Recc relation : T(N) = 2*T(N/2) + O(n)

PSEUDOCODE : 

	mergeSort(A,0,len(A) - 1)

	// no auxillary space
	mergeSort(A, low, high):
		// base case : single element
		if(low >= high)
		{
			return
		}
		// Recursive case
		else if(low < high)
		{
			mid = low + (high-low)/2;
			mergeSort(A,low,mid)
			mergeSort(A,mid+1,high)
			merge(A, low, mid + 1, high)
		}
		
	// A = [2,4,1,3] : left = 3, right = 6, mid = 4 ( in this step )
	// 		  P1  P2
	// Allocate in merge step
	// Comparison operations : iteratively
	merge(A, low, mid, high):
		sz = right-left+1
		temp[sz]
		ptr1 = low					// Get addressing correct!
		ptr2 = mid + 1
		k = 0
		// Sizes of merged subarray scould be dissimilar ( odd length case )
		while(ptr1 <= mid || ptr2 <= high)
			if(ptr1 <= mid && ptr2 <= high)
				if(A[ptr1] <= A[ptr2])
					temp[k++] = A[ptr1++]
				else
					temp[k++] = A[ptr2++]
			else if (ptr1 <= mid)
				temp[k++] = A[ptr1++]
			else if (ptr2 <= high)
				temp[k++] = A[ptr2++]

		// Write back to the input Array
		for i in range(0,len(temp),1):
			A[low + i] = temp[i]

Complexity
N := len(array)
TIME = O(NLGN) 
SPACE = O(LGN) ( IMP: call stack ) O(N) ( EXP : auxillary/heap ) 
Not an in-order sort NOR in-place

T(N) = 2*T(N/2) + O(N)

Test Cases : 
(A) [2,3,-1,5,6] ( odd length ) 
			  |
(B) [2,3,1,5] ( even length )
(C) [2,2,2,2,2] ( constant ) 
(D) [1,2,3,4,5,6] ( sort incr ) 
(E) [6,5,4,3,2,1] ( sort decr ) 
(F) [1]
(G) [1,2]
(H) [2,1]





				 4 5 6 7 8
		[...,1,2,3,4,5,...]
		
		
		low = 4						= p1
		high = 8
		mid = 6						= p2
		mid+1 = 7
		
		sz = high-low+1 = 5
		
					(0,8):U
					/			\
				(0:4):U		(5:8)U
				/		\			/			\
								(5:6)U		(7:8:S)
								/		\			/			\
												(7:7:S)	(8:8:S)				< single element
												
					(0,8):S			<- stack size = 1
					/			\
				(5:8:S)		(0:4):U
				/		\		
								
												
Backtrack : put solved problem/subrange before the unsolved problem/subrange	
Check you have @ least two elems in stack
Merge always requires two subproblems solved
												
import java.util.*;

class Triplet
{
	public int low;
	public int high;
	public boolean solved;
}

class Program 
{
  public static int[] mergeSort(int[] array) 
	{
   	recurseMergeSort(array,0,array.length - 1);
		return array;
  }
	
	// List<Integer> triplet -> (low,high,)
	
	private static void recurseMergeSort(int[] A, int low, int high)
	{
			if(low >= high)
			{
				return;
			}
			else
			{
				int mid = low + (high-low)/2;
				recurseMergeSort(A,low,mid);
				recurseMergeSort(A,mid+1,high);
				merge(A,low,mid,high);
			}
	}
	// Post order : left->right->root
	// Stack with (low,high) and keep dividing
	// [(0,8,F)] -> [(0,4,F),(5,8,F),...] -> [(0,4,F),(5,6,F),(7,8,F),...]
	// First stack element is merged but second stack element is not merged -> merge
	// [(0,4),S:(5,8)]
	
	
	private static void merge(int[] A, int low, int mid, int high)
	{
		int sz = high-low+1;
		int temp[] = new int[sz];
		int ptr1 = low;					// Get addressing correct!
		int ptr2 = mid + 1;
		int k = 0;
		// Sizes of merged subarray scould be dissimilar ( odd length case )
		while(ptr1 <= mid || ptr2 <= high)
		{
			if(ptr1 <= mid && ptr2 <= high)
			{
				if(A[ptr1] <= A[ptr2])
					temp[k++] = A[ptr1++];
				else
					temp[k++] = A[ptr2++];
			}
			else if (ptr1 <= mid)
			{
				temp[k++] = A[ptr1++];
			}
			else if (ptr2 <= high)
			{
				temp[k++] = A[ptr2++];
			}
		}
		// Write back to the input Array
		for(int i = 0; i < temp.length; ++i)
		{
			A[low + i] = temp[i];
		}
	}
}

												
import java.util.*;

class Triplet
{
	public int low;
	public int high;
	public boolean solved;
}

class Program 
{
  public static int[] mergeSort(int[] array) 
	{
   	iterativeMergeSort(array);
		return array;
  }
	
	// List<Integer> triplet -> (low,high,)
	
	private static void iterativeMergeSort(int[] A)
	{
			int low = 0;
			int high = A.length - 1;
			Stack<Triplet> stk = new Stack<Triplet>();
			Triplet initProblem = new Triplet(low,high,false);
			stk.push(initProblem);
			Triplet cur=null;
			while(cur!=null || !stk.isEmpty())
			{
				while (cur!=null) {
					Triplet topEl = stk.peek();
					int mid=(topEl.low + topEl.high)/2;
					Triplet leftEl = new Triplet(low, mid, false);
					Triplet rightEl = new Triplet(low, mid, false);
					
				}
				cur=stk.peek();
				if (cur.canBeSolved == true) {
					
				}
				merge(cur);
				Triplet curProblem = stk.pop();	// right problem ( pushLeft, pushRight)
				
			}
		}
	}
	// Post order : left->right->root
	// Stack with (low,high) and keep dividing
	// [(0,8,F)] -> [(0,4,F),(5,8,F),...] -> [(0,4,F),(5,6,F),(7,8,F),...]
	// First stack element is merged but second stack element is not merged -> merge
	// [(0,4),S:(5,8)]
	
	
	private static void merge(int[] A, int low, int mid, int high)
	{
		int sz = high-low+1;
		int temp[] = new int[sz];
		int ptr1 = low;					// Get addressing correct!
		int ptr2 = mid + 1;
		int k = 0;
		// Sizes of merged subarray scould be dissimilar ( odd length case )
		while(ptr1 <= mid || ptr2 <= high)
		{
			if(ptr1 <= mid && ptr2 <= high)
			{
				if(A[ptr1] <= A[ptr2])
					temp[k++] = A[ptr1++];
				else
					temp[k++] = A[ptr2++];
			}
			else if (ptr1 <= mid)
			{
				temp[k++] = A[ptr1++];
			}
			else if (ptr2 <= high)
			{
				temp[k++] = A[ptr2++];
			}
		}
		// Write back to the input Array
		for(int i = 0; i < temp.length; ++i)
		{
			A[low + i] = temp[i];
		}
	}
}												
					
