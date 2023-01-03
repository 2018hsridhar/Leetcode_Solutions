'''
URL = https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
1475. Final Prices With a Special Discount in a Shop

N of length 1K max
Complexity
Time = O(N^2)
Space = O(1) ( EXP & IMP ) 

Cases : 
(A) [1,2,3,4,5] => [1,2,3,4,5] 
(B) [5,4,3,2,1] => [1,1,1,1,1]
(C) [5,5,5,5,5] => [5,5,5,5,5]
(D) [100] => 1
(E) [5,4,3,2,3,4] => [1,1,1,2,3,4]

'''
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        myFinalDiscountedPrices = [0 for i in range(len(prices))]
        for i in range(len(prices)):
            ithPrice = prices[i]
            discountToApply = 0
            for j in range(i+1,len(prices),1):
                jthPrice = prices[j]
                if(jthPrice <= ithPrice):
                    discountToApply = jthPrice
                    break
            discountedPrice = ithPrice - discountToApply
            myFinalDiscountedPrices[i] = discountedPrice
        return myFinalDiscountedPrices
