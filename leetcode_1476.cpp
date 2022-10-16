/*
URL = https://leetcode.com/problems/subrectangle-queries/
1476. Subrectangle Queries

*/
class SubrectangleQueries {
private:
    // But why do we want `this` explicitly?
    // [1] It seems safer
    // [2] We can avoid multiple namings
    // [3] We need `this` is the name is same : we do not if the name differs!
    std::vector<std::vector<int>> rectangle;
public:
    // Notice : deep copy preferred : initVals not all 0-set here!
    SubrectangleQueries(vector<vector<int>>& rectangle) {
        // myRect = std::vector<std::vector<int>>(); // Again ; why is an explicit initialization not needed for non-trivial types?
        for(const auto& row: rectangle){
            this->rectangle.push_back(std::vector<int>(row));
            // std::cout << '\n' << std::endl;
            // std::cout << std::endl;  // Can eschew the additional '\n' here!
        }
    }
    
    // No need to specify `return` in a void signature.
    // Every else needs `return <val>` expr : we get to save on LOC here!
    void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for(int i = row1; i <= row2; ++i){
            for(int j = col1; j <= col2; ++j){
                this->rectangle.at(i).at(j) = newValue;
            }
        }
    }
    
    int getValue(int row, int col) {
        return this->rectangle.at(row).at(col); // (myRect.at(row)).at(col) not needed!
    }
};

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries* obj = new SubrectangleQueries(rectangle);
 * obj->updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj->getValue(row,col);
 */
