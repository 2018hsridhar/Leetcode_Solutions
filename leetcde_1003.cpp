/*
1003. Check If Word Is Valid After Substitutions
URL = 1003. Check If Word Is Valid After Substitutions

Let N = len(s)
Time = O(N)
Space = O(N) ( EXP ) O(1) ( IMP ) 
*/
class Solution {
public:
    bool isValid(string s) {
        bool stat = true;
        std::stack<char> stk;
        for(char c : s){
            if( c == 'a' || c == 'b'){
                stk.push(c);
            } else if ( c == 'c'){
                if(stk.size() < 2){
                    stat = false;
                    break;
                } else {
                    char topEl = stk.top();
                    stk.pop();
                    char nextEl = stk.top();
                    stk.pop();
                    if(!(topEl == 'b' && nextEl == 'a')){
                        stat = false;
                        break;
                    }
                }
            }
        }
        return (stat && (stk.size() == 0));
    }
};
