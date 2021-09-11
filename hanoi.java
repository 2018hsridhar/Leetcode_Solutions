

/*
Complexity
Time = ???
Space = O(depth(n-1))
*/
public class hanoi
{
	public static void main(String[] args)
	{
		System.out.printf("Executing Towers of Hanoi Code\n");
		int n = 4;
		int numMoves = hanoi(n, "S", "D","E");
		System.out.printf("Num moves = %d\n", numMoves);
	}

	/*
	Highly recursive
	Will execute prints too
	Denote {source, dest, extra} nodes here as well too
	Denote number of elements to move as well ( but do HALT at case of 1 node ) 
	Just pass in the Strings themselves anyways
	*/
	public static int hanoi(int n, String s, String d, String e)
	{
		int numMoves = 0;
		if(n == 1)
		{
			System.out.printf("Moving disc %d from %s to %s\n", n, s, d);
			return 1;
		}
		else
		{
			numMoves += hanoi(n-1, s, e, d); // here, e = dest, d = extra
			System.out.printf("Moving disc %d from %s to %s\n", n, s, d);		
			numMoves += 1;	
			numMoves += hanoi(n-1, e, d, s); // here, e = source, s = extra ( flip em ) 			
		}
		return numMoves;
	}

}
