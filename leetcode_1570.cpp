/*
URL = https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
1570. Dot Product of Two Sparse Vectors

*/
class SparseVector {
private:
    std::map<int,int> els;
public:
    
    SparseVector(vector<int> &nums) {
        // not a range loop : no auto keyword here
        for(int i = 0; i < nums.size(); ++i){
            if(nums[i] > 0){
                els[i] = nums[i];
            }
        }
    }
    
    std::map<int,int>* getEls(){
        return &els;
    }
    
    // Return the dotProduct of two sparse vectors
    int dotProduct(SparseVector& vec) {
        int res = 0;
        // Leverage structured bindings heres
        for(const auto& [key,val] : els){
            if(vec.getEls()->find(key) != vec.getEls()->end()){
                res += (val * vec.els[key]);
            }
        }
        return res;
    }
};

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1(nums1);
// SparseVector v2(nums2);
// int ans = v1.dotProduct(v2);
