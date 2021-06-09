/*

Range of values in string : [a-zA-Z]
URL = https://leetcode.com/problems/determine-if-string-halves-are-alike/
1704. Determine if String Halves Are Alike
Count vowels ( including repeated vowels )
Evenness remains guaranteed for strings
Use a two pointers approach? Possible?
Do not go sorting this string
Yes, uppercase and lowercase vowels contribute to counts too

chutzpah - 8 characters [ 0-3 : 4 - 7 ] must be searched here
[0-3 : 4 - 8 ]


Isn't it weird, how we can evaluate an expression, in parantheses, and then return it, in JAVA 7?
Racing pointers : handling of even or odd lengths ( use <=, for the odd case ? )
- make sure to prevent any double counting though

Ideas for checking if a vowel
(a) HashSet initialized with all vowels
(b) a Regex?

        // char c = 'a';
        // String c_a = "a";
        // c_a.matches(regex);
Q : Why might string objects be preferred, even for single literals?
A : Because string objects come with more built in methods than just operator tests , such as matching a REGEX! 

Can use index of instead of REGEXs - but regexes seem cooler ( can define character ranges easily ) 

*/

class Solution 
{
    public boolean halvesAreAlike(String s) 
    {
        String vowelRegex = "[aeiouAEIOU]";
        int i = 0;
        int j = s.length() - 1;
        char[] c_arr = s.toCharArray();
        int firstVowelCount = 0;
        int secondVowelCount = 0;
        // String x = "b";
        // System.out.printf("%s\n",x.matches(vowelRegex));
        while(i <= j)
        {
            String headChar = s.substring(i,i+1);
            String tailChar = s.substring(j,j+1);
            if(headChar.matches(vowelRegex))
                ++firstVowelCount;
            if(tailChar.matches(vowelRegex))
                ++secondVowelCount;
            ++i;
            --j;
        }
        return (firstVowelCount == secondVowelCount);
    }
}
