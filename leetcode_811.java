/*

811. Subdomain Visit Count
URL = https://leetcode.com/problems/subdomain-visit-count/

THOUGHT PROCESS : 
Utilization tokenization here to parse each subdomain by itself -> can we reverse iteration though?
Issues with iterators - they do not always support reverseIteration as easy ( index var based loops do ) 
Tokenization entails lexical analysis of tokens in a delimited-string : easy analysis here!
Are delimiters involved here too or not? Can write them along tokens too!

Computational Complexity : 


Edge case testing : 


References : 
1. https://www.youtube.com/watch?v=UjxvJQkjRxI
- note that String Tokenizer is an iterator based approach ( versus String.split ), but is deprecated in later versions of JAVA 
https://www.youtube.com/watch?v=3vauM7axnRs
https://www.youtube.com/watch?v=rM6w8Mcn1jU

Let cpd := count-paired domain
An adddress = a collection of domains : [ child_domain ---> parent_domain ] 

Domain counting helps web servers with analytics : which parts of website get hit the most frequent or not

            // Notice we have ( char[] str )  and ( String str )


*/
import java.util.StringTokenizer;  

class Solution 
{
    public List<String> subdomainVisits(String[] cpdomains) 
    {
        List<String> visitations = new LinkedList<String>();
        if(cpdomains == null || cpdomains.length == 0)
            return visitations; 
        HashMap<String,Integer> domainFreqs = new HashMap<String,Integer>();
        String delim = " ";
        for(String cpd : cpdomains )
        {
            StringTokenizer st = new StringTokenizer(cpd,delim);
            int freq = Integer.parseInt(st.nextToken());
            String address = st.nextToken();
            String[] subDomains = address.trim().split("\\.");
            StringBuilder sb = new StringBuilder("");
            
            // Would a suffix array assist out more here?
            // Work on first subdomain ( e.g. .com )            
            // Then remainder subdomain prefixes
            for(int i = subDomains.length - 1; i >= 0; --i)
            {
                if(i <= subDomains.length - 2)
                    sb.insert(0,".");
                sb.insert(0,subDomains[i]);
                String domain = sb.toString();
                if(domainFreqs.containsKey(domain))
                    domainFreqs.put(domain, domainFreqs.get(domain) + freq);
                else
                domainFreqs.put(domain, freq);                
            }
            
            
        }
        
        // Iterate over hashmap entries <K,V> pairs and output into SLL
        Set<Map.Entry<String,Integer>> df_pair_set = domainFreqs.entrySet();
        for(Map.Entry<String,Integer> df_pair : df_pair_set)
        {
            StringBuilder sb = new StringBuilder("");
            sb.append(df_pair.getValue());
            sb.append(" ");
            sb.append(df_pair.getKey());
            visitations.add(sb.toString());
        }
        return visitations;
    }
}
