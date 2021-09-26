/*

URL = https://leetcode.com/problems/edit-distance/
72. Edit Distance



*/
class Solution 
{
    // Recursive approach with overlapping subproblems as well
    public int minDistance(String word1, String word2) 
    {
        // return recursiveSolution(word1, word2);
        return dp_solution(word1, word2);
    }
     
    // Base cases = one of the inputs here be an empty string
    public int recursiveSolution(String word1, String word2)
    {
        int dist = Integer.MAX_VALUE;;
        if(word1.length() == 0)
            return word2.length();
        else if ( word2.length() == 0)
            return word1.length();
        // Substitute heavily overlaps with this case of EQUAL characters as well ( it does reduce to it ) 
        // Hence strongly consider a bottom-up DP paradigm in place instead
        String newWord1 = word1.substring(1);
        String newWord2 = word2.substring(1);
        if(word1.charAt(0) == word2.charAt(0))
        {
            dist = Math.min(dist, recursiveSolution(newWord1, newWord2));
        }
        else
        {
            // Insert OR delete a character from the front
            dist = Math.min(dist, 
                Math.min(1+recursiveSolution(newWord1, word2), 1 + recursiveSolution(word1, newWord2)));
                
            // Character substitute ( this changes it a lot BTW ) 
            dist = Math.min(dist, 1 + recursiveSolution(newWord1, newWord2));
        }
        // System.out.printf("For word combos = [%s,%s] => solution = %d\n", newWord1, newWord2, dist);
        return dist;
    }
        
    public int dp_solution(String word1, String word2)
    {
        // SIMPLE BASE CASES
        if(word1.length() == 0 && word2.length() == 0)
            return 0;
        if(word1.length() == 0)
            return word2.length();
        else if( word2.length() == 0)
            return word1.length();
        
        // NOW THE ACTUAL MATRIX
        // INITIALIZE BOUNDARY
				// SIMPLE BASE CASES
        if(word1.length() == 0 && word2.length() == 0)
            return 0;
        if(word1.length() == 0)
            return word2.length();
        else if( word2.length() == 0)
            return word1.length();
        
        // NOW THE ACTUAL MATRIX
        // INITIALIZE BOUNDARY
				// NEED an additional matrix portion to account for empty string ( len 0 ) properly too
		
        int m = word1.length();
        int n = word2.length();
        int[][] DP = new int[m+1][n+1];
		
        for(int i = 0; i < m+1; ++i)
            DP[i][0] = i;
        for(int j = 0; j < n+1; ++j)
            DP[0][j] = j;
        
        for(int i = 1; i < m+1; ++i)
        {
            for(int j = 1; j < n+1; ++j)
            {
                if(word1.charAt(i-1) == word2.charAt(j-1))
                {
                    DP[i][j] = DP[i-1][j-1]; // just add anyways
                }
                else
                {
                    DP[i][j] = Math.min(1+DP[i-1][j-1], Math.min(1+DP[i][j-1], 1+DP[i-1][j]));
                }
            }
        }
        return DP[m][n];
            
        
    }
}
