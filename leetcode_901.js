/*
URL := https://leetcode.com/problems/online-stock-span/
901. Online Stock Span

Complexity : 
2x-ops each element
Let N := #-stocks over the stock price history
Time := O(N)
Space := O(N) ( EXP ) O(1) ( IMP )

15 minutes to solution

*/
var StockSpanner = function() {
    this.dailyPriceQuotes = []
};

/** 
 * @param {number} price
 * @return {number}
 */
StockSpanner.prototype.next = function(price) {
   let stockSpanCurrentDay = 1; // the default value
   while(this.dailyPriceQuotes.length > 0) {
       let topEl = this.dailyPriceQuotes.at(-1); // .at() prototype
       let topPrice = topEl[0];
       let topSpan = topEl[1];
       if(price >= topPrice) {
           this.dailyPriceQuotes.pop();
           stockSpanCurrentDay = stockSpanCurrentDay + topSpan;
       } else { // NOP : 100:1 -> 80:1 -> 70:1
           break;
       }
   }
   this.dailyPriceQuotes.push([price, stockSpanCurrentDay])
   return stockSpanCurrentDay; 
};

/** 
 * Your StockSpanner object will be instantiated and called as such:
 * var obj = new StockSpanner()
 * var param_1 = obj.next(price)
 */
