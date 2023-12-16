/**
 * @param {number} n
 * @param {number} discount
 * @param {number[]} products
 * @param {number[]} prices
 */
 /*
 URL := https://leetcode.com/problems/apply-discount-every-n-orders/
 1357. Apply Discount Every n Orders
 Product -> Amount : 
 200 products @ max ( 200 prices ) 
 Not to many calls
 Set up a mapping for expedited lookups.

OOP In Javascript GAAAH
Markets and billing -> billing microservices

this -> defined for new obj

Object instantiated from a function ( via var syntax ) 
Add methods to object via prototype keyword
<object_name.prototype.method_name>
 */
var Cashier = function(n, discount, products, prices) {
    this.customerCount = 0;
    this.n = n; // semi-colon language
    this.discount = discount;
    this.prodPriceMap = new Map();
    for(let i = 0; i < products.length; i++) {
        let productId = products[i];
        let price = prices[i];
        this.prodPriceMap.set(productId,price);
    }
    // console.log(this.prodPriceMap)
};

/** 
 * @param {number[]} product 
 * @param {number[]} amount
 * @return {number}
 */
 /*
 Will not always have applicable discounts.
 FPE accounting
 The nth custgomer paying ( n is given ) 

16 minutes
 */
Cashier.prototype.getBill = function(product, amount) {
  this.customerCount++;
  let applyDiscount = (this.customerCount % this.n == 0); // bool capture function expr style 
  let finalBill = 0;
  // Scope capture of outside var needed too
  // once function invoke - in order
  product.forEach((prod,index) => {
      let am = amount[index];
    //   console.log("Prod = %d \t amount = %d\n", prod, am);
      var curAmountPrice = (this.prodPriceMap.get(prod) * am);
      // finnicky : indexing or .get() -> is .get()
      finalBill += curAmountPrice;
  });
  if(applyDiscount) {
      let discountFactor = ((100 - this.discount) / 100);
      finalBill *= discountFactor;
  }
  return finalBill;  
};

/** 
 * Your Cashier object will be instantiated and called as such:
 * var obj = new Cashier(n, discount, products, prices)
 * var param_1 = obj.getBill(product,amount)
 */
