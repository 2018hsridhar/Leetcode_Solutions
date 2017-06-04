// https://leetcode.com/problems/reverse-words-in-a-string-iii/#/description

class Solution {
public:
    // so ... use reverse iterator here, create a new string ... sounds easy here!
    string reverseWords(string s) 
    {
        std::vector<std::string> strs; 
        std::istringstream iss(s);      // initialize input string stream with the string itself!
        for(std::string s; iss >> s; )
        {
            strs.push_back(s);
        }
        
        // now have a vecctor of strings ... uhh, equilvanet of java's Array.join(s,"");
        // again ... use string streams here :-(
        std::stringstream ss;
        int elems = strs.size();
        int last = elems - 1; 
        const char delim = ' ';
        for(int i = 0; i < elems; ++i)
        {
            string cur = strs[i];
            reverse(cur.begin(), cur.end()); /// GAAAH
            ss << cur;
            if(i != last) ss << delim;
        }
        return ss.str(); 
    }
};
