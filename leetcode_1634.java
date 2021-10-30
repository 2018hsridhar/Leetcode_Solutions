/**
 * Definition for polynomial singly-linked list.
 * class PolyNode {
 *     int coefficient, power;
 *     PolyNode next = null;
 
 *     PolyNode() {}
 *     PolyNode(int x, int y) { this.coefficient = x; this.power = y; }
 *     PolyNode(int x, int y, PolyNode next) { this.coefficient = x; this.power = y; this.next = next; }
 * }
 */

/*
Every node := a term in a polynomial expression
Coeff = integer multiplier of a term
Polynomial SLL is in its standard form : in decreasing order ( wait isn't big-O strictly descending order by power value )
Return head of sum of polynomials

Either (A) Create a new PolyNode SLL or (B) modify the existing one

COMPLEXITY
Let M := len(poly1), N := len(poly2), P := max(M,N)
Time = O(P)
Space = O(1) explicit and maybe O(P) implicit

TEST BENCH
(A)
(B)
(C)

Powers are a strictly decreasing sequence as well
Strategies :: Head recursive or tail recursive
We just add the coeffs if the terms are the same

akin to DP with problem size as well
if poly1.pow > poly2.pow
    PolyNode head.next = poly1
    addPoly(poly1.next,poly2)
else if ( poly2.pow > poly1.pow)
    addPoly(poly1,poly2.next)
else
    addPoly(poly1.next,poly2.next)

If either is null : we just append the non-null polynomial
If both null : return null

*/

class Solution 
{
    public PolyNode addPoly(PolyNode poly1, PolyNode poly2) 
    {
        return helper(poly1, poly2);
    }
    
    private PolyNode helper(PolyNode poly1, PolyNode poly2) 
    {
        if(poly1 == null && poly2 == null)
        {
            return null;
        }
        else if(poly1 == null)
        {
            return poly2;
        }
        else if ( poly2 == null)
        {
            return poly1;
        }
        else
        {        
            PolyNode largestTerm = null; // initial point here ( dflt initialization needed ) 
            if(poly1.power > poly2.power)
            {
                largestTerm = poly1;
                PolyNode remainingOne = poly1.next;
                poly1.next = null;
                PolyNode lowerOrderTerms = helper(remainingOne, poly2);
                largestTerm.next = lowerOrderTerms;
            }
            else if ( poly1.power < poly2.power)
            {
                largestTerm = poly2;
                PolyNode remainingTwo = poly2.next;
                poly2.next = null;
                PolyNode lowerOrderTerms = helper(poly1, remainingTwo);
                largestTerm.next = lowerOrderTerms;
            }
            else
            {
                int newCoeff = poly1.coefficient + poly2.coefficient;
                int newPower = poly1.power;
                if(newCoeff != 0)
                {
                    PolyNode newUpperTerm = new PolyNode(newCoeff, newPower);
                    PolyNode lowerOrderTerms = helper(poly1.next, poly2.next);
                    largestTerm = newUpperTerm;
                    largestTerm.next = lowerOrderTerms;
                }
                else
                {
                    largestTerm = helper(poly1.next, poly2.next);
                }
            }
            return largestTerm;
        }
    }
}
