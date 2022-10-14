class Solution {
public:
    string solveEquation(string equation) {
        // std::regex rxSplit(std::string("\\") + "s+");
        // std::OutputIterator output; // NOT all types are available in the std namespace!
        // std::copy(std::sregex_token_iterator(std::begin(equation),std::end(equation),rxSplit,-1),
                 // std::sregex_token_iterator(), output);
        // https://stackoverflow.com/questions/10058606/splitting-a-string-by-a-character
        std::stringstream test(equation);
        std::string segment;
        std::vector<std::string> handSides;
        while(std::getline(test, segment, '=')) {
            handSides.push_back(segment);
        }
        std::pair<int,int> lhsCoeffs = getCoeffs(handSides.at(0));
        std::pair<int,int> rhsCoeffs = getCoeffs(handSides.at(1));
        if(lhsCoeffs.first == rhsCoeffs.first && lhsCoeffs.second == rhsCoeffs.second){
            return "Infinite solutions";
        } else if ( lhsCoeffs.first != 0 || rhsCoeffs.first != 0 ) {
            // int -> string in C++
            if(rhsCoeffs.first - lhsCoeffs.first == 0){
                return "No solution";
            }
            int temp = ((lhsCoeffs.second - rhsCoeffs.second) / (rhsCoeffs.first  - lhsCoeffs.first));
            printf("temp = %d\n", temp);
            return "x=" + to_string(temp);
        } else {
            return "No solution";
        }
        return "";
    }
    
private: 
    pair<int,int> getCoeffs(std::string expr){
        std::pair<int,int> coeffs; // can I default initialize this?
        // sliding window, in the reverse, more helpful
        // Only operations : no negations :-)
        // We can do indexOf methods with `+` or `-` too?
        int rPtr = expr.size() - 1;
        int lPtr = expr.size() - 1;;
        int n = expr.size();
        while(lPtr >= 0){
            if(expr.at(lPtr) == '+'){
                // printf("rPtr = %d\n", rPtr);
                if(expr.at(rPtr) == 'x'){
                    // printf("here\n lPtr = %d \t rPtr = %d\n", lPtr, rPtr);
                    if(lPtr == rPtr - 1){
                        coeffs.first += 1;
                    }
                    else if(rPtr > lPtr + 1) {
                        int coeff = stoi(expr.substr(lPtr+1,rPtr+1)); // string to a nint
                        // printf("coeff = %d\n", coeff);
                        coeffs.first += coeff;
                    }
                } else {
                    int coeff = stoi(expr.substr(lPtr+1,rPtr)); // string to a nint
                    coeffs.second += coeff;
                }
                rPtr = lPtr - 1;
            } else if ( expr.at(lPtr) == '-'){
                // printf("lPtr = %d \t rPtr = %d\n", lPtr, rPtr);
                if(expr.at(rPtr) == 'x'){
                    if(lPtr == rPtr - 1){
                        coeffs.first -= 1;
                    }
                    else if(rPtr > lPtr + 1) {
                        int coeff = stoi(expr.substr(lPtr+1,rPtr)); // string to a nint
                        coeffs.first -= coeff;
                    }
                } else {
                    int coeff = stoi(expr.substr(lPtr+1,rPtr)); // string to a nint
                    coeffs.second -= coeff;
                }
                rPtr = lPtr - 1;
            }
            --lPtr;
        }
        if(rPtr >= 0){
            if(expr.at(rPtr) == 'x'){
                if(lPtr == rPtr - 1){
                  coeffs.first += 1;  
                } else if(rPtr > lPtr + 1) {
                    int coeff = stoi(expr.substr(lPtr+1,rPtr)); // string to a nint
                    coeffs.first += coeff;
                }
            } else {
                int coeff = stoi(expr.substr(lPtr+1,rPtr+1)); // string to a nint
                coeffs.second += coeff;
            }
        }
        return coeffs;
    }
};
