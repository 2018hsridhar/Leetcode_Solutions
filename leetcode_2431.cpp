/*
2431. Maximize Total Tastiness of Purchased Fruits
URL = https://leetcode.com/problems/maximize-total-tastiness-of-purchased-fruits/
*/
class Solution {
public:
    // Notice the hiding of internal implementation details
    int maxTastiness(vector<int>& price, vector<int>& tastiness, int maxAmount, int maxCoupons) {
        // Initially 0 initialized too! maxAmount = 0 corresponds to a 0 directly!
        vector<vector<vector<int>>> memo(maxAmount+1, vector<vector<int>> (price.size()+1, vector<int>(maxCoupons+1,0)));
        return maxTastinessInternal(memo, 0, price,tastiness,maxAmount,maxCoupons);
    }
    
private:
        int maxTastinessInternal(vector<vector<vector<int>>>& memo, int idx,
             vector<int>& price, vector<int>& tastiness, int curAmount, int numCoupons){
            if(idx == price.size())
              return 0;
            if(memo[curAmount][idx][numCoupons] > 0)
                return memo[curAmount][idx][numCoupons];
            int myMaxTastiness = 0;
            int curTastiness = tastiness[idx];
            int caseNotPurchaseAmount = curAmount;
            int casePurchaseWithCouponAmount = curAmount - floor(price.at(idx)/2 ); 
            int casePurchaseWithoutCouponAmount = curAmount - price.at(idx);
            int subProbOne = 0;
            int subProbTwo = 0;
            int subProbThree = 0;
            if(numCoupons >= 1){
                if(casePurchaseWithCouponAmount >= 0){
                    subProbTwo = curTastiness + maxTastinessInternal(memo,idx+1,price,tastiness,casePurchaseWithCouponAmount,numCoupons-1);
                }
            }
            if(caseNotPurchaseAmount >= 0){
                subProbOne = maxTastinessInternal(memo,idx+1,price,tastiness,caseNotPurchaseAmount,numCoupons);
            }
            if(casePurchaseWithoutCouponAmount >= 0){
                subProbThree = curTastiness + maxTastinessInternal(memo,idx+1,price,tastiness,casePurchaseWithoutCouponAmount,numCoupons);
            }
                
            // This is a slow operation, for lord knows why!
            // myMaxTastiness = *std::max_element(cases.begin(), cases.end());
            myMaxTastiness = max(subProbOne,max(subProbTwo,subProbThree));
            // printf("myMaxTasty @ idx = %d = %d\n", idx, myMaxTastiness);
            memo[curAmount][idx][numCoupons] = myMaxTastiness;
            return memo[curAmount][idx][numCoupons];
        }
};
