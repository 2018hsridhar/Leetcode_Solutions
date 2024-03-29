
/*

URL = https://leetcode.com/problems/factorial-trailing-zeroes/
172. Factorial Trailing Zeroes

THOUGHTPROCESSES :
Trailing zeroes != leading zeroes BTW




COMPUTATIONAL COMPLEXITY : 
Time = O(N), but O(logN) desireable
Space = O(1), but say O(LogN) or O(N) remains on possibility frontier too


EDGE CASE TESTING : 
(1) 0! = 0, 1! = 1, 2! = 2*1 = 1
(2) large values of n ( e.g. 10^4 = 10,000)
(3) Typical values ( n = 3,10,100 )

Can not keep multiplying unless a seperate data type is used?
Strongly consider the BigInteger class for this problem?
Signed int data type limitations : [-2^31,2^31-1] = data range

Is math based : try to notice patterns here
Naive idea : compute result - store in string - parse string for trailing zeroes - O(N) space, O(N) time with one-pass linear scan

For any multiplier eventually equal to 10 - > that adds a trailing zero! This is known!
Ignore non-trailing zeroes ( e.g. 5040 )
If 1 trailing zero exists -> it carries over to the rest of the problem ( no matter how many multiplications )

Possibly a factorization thing? 
Numbers which matter : {2,5,10} as (2*5) = 10
Notice shifts in trailing zero count when we hit {5,10,15,20} in the first couple of factorial calculations
Note : if we factorization into multiple "5s 2s", ... that boils down to a factorization to "10s" and aren't 5 and 2 primes :-O

Take a look at a basis 12x12 multiplication table
Notice what multiplications of single-digit values actually have trailing zeroes
https://www.istockphoto.com/vector/multi-colored-multiplication-table-in-the-vector-located-on-a-green-background-with-gm1177111296-328485921

Enumeration by cases ( up to 20! ) 
Try prime factorization approaches too? Focused on 2 and 5 here!

Working with large numbers is difficult; but working witht heir factorization of primes is much easier, and we know largest factor is up to sqrt(n) of a number :-). Less search space here OMG!

*/
/*

URL = https://leetcode.com/problems/factorial-trailing-zeroes/
172. Factorial Trailing Zeroes

THOUGHTPROCESSES :
Trailing zeroes != leading zeroes BTW




COMPUTATIONAL COMPLEXITY : 
Time = O(N), but O(logN) desireable
Space = O(1), but say O(LogN) or O(N) remains on possibility frontier too


EDGE CASE TESTING : 
(1) 0! = 0, 1! = 1, 2! = 2*1 = 1
(2) large values of n ( e.g. 10^4 = 10,000)
(3) Typical values ( n = 3,10,100 )

Can not keep multiplying unless a seperate data type is used?
Strongly consider the BigInteger class for this problem?
Signed int data type limitations : [-2^31,2^31-1] = data range

Is math based : try to notice patterns here
Naive idea : compute result - store in string - parse string for trailing zeroes - O(N) space, O(N) time with one-pass linear scan

For any multiplier eventually equal to 10 - > that adds a trailing zero! This is known!
Ignore non-trailing zeroes ( e.g. 5040 )
If 1 trailing zero exists -> it carries over to the rest of the problem ( no matter how many multiplications )

Possibly a factorization thing? 
Numbers which matter : {2,5,10} as (2*5) = 10
Notice shifts in trailing zero count when we hit {5,10,15,20} in the first couple of factorial calculations
Note : if we factorization into multiple "5s 2s", ... that boils down to a factorization to "10s" and aren't 5 and 2 primes :-O

Take a look at a basis 12x12 multiplication table
Notice what multiplications of single-digit values actually have trailing zeroes
https://www.istockphoto.com/vector/multi-colored-multiplication-table-in-the-vector-located-on-a-green-background-with-gm1177111296-328485921

Enumeration by cases ( up to 20! ) 
Try prime factorization approaches too? Focused on 2 and 5 here!

Working with large numbers is difficult; but working witht heir factorization of primes is much easier, and we know largest factor is up to sqrt(n) of a number :-). Less search space here OMG!

Can the prime factorization algorithm be implemented here instead? Or not -> would have to do a lot of multiplication work
Can we go logartihmic somehow? I know the linear approach ( keep decrementing n till 5 ) 


*/
import java.math.BigInteger;
class Solution 
{
    public int trailingZeroes(int n) 
    {
        int count5 = 0;
        if(n == 0)
            return 0;
        else if ( n <= 4)
            return 0;
        else
        {
            while(n >= 5)
            {
                int num_fives = 0;
                int val = n;
                while(val % 5 == 0 && val >= 5)
                {
                    val /= 5;
                    ++num_fives;
                }
                count5 += num_fives;
                n-=1;
                
            }
        }
        // Confirmed : Excel ran into overflow issues; but prime factorization algorithms work quickly :-)
        // BigInteger x1 = new BigInteger("48658040163532800000");
        // BigInteger x2 = new BigInteger("2432902008176640000");
        // BigInteger x3 = x1.add(x2);
        // System.out.printf("Val of x3 = %s\n", x3.toString());
        
        
        return count5;        
    }
}
