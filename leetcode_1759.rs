/*
https://leetcode.com/problems/count-number-of-homogenous-substrings/
1759. Count Number of Homogenous Substrings
https://doc.rust-lang.org/std/string/struct.String.html#method.len
Classic sliding window algorithm technique with combinatorics
Need to modulo take note
How to handle TLE in rust ( due to indexing issues )
// unwrap() operation takes time

*/
impl Solution {
    pub fn count_homogenous(s: String) -> i32 {
        let a = 2; // Can also explicitly define type i.e. i32
        // i32(val) NOT a type!
        let modulo = i128::pow(10,9) + 7;
        // mut needs nameBinding here
        let mut countHomo: i128 = 0;
        let mut lPtr = 0;
        let mut rPtr = 0;
        let len = s.len();
        // Jeezus heck https://users.rust-lang.org/t/accessing-the-char-at-a-byte-index/15398
        // Gaaah UTF-8 encoding
        let mut iter = s.chars();
        let sCopy = s.clone(); 
        let mut iterOne = sCopy.chars();
        iterOne.next();
        let mut runLength = 1;
        while rPtr + 1 < len {
            // Heck is a `None` value?
            // how to make indexing a const time opperation?
            // let mut curChar = iter.nth(rPtr).unwrap();
            // let mut nextChar = iter.nth(rPtr + 1).unwrap();
            let mut curChar = iter.next();
            let mut nextChar = iterOne.next();
            if curChar == nextChar {
                runLength += 1;
            } else {
                let delta: i128 = ((runLength)*(runLength + 1))/2;
                countHomo += delta;
                runLength = 1;
            }
            rPtr += 1;
        }
        let delta: i128 = ((runLength)*(runLength + 1))/2;
        countHomo += delta;
        // unsafe conversion with 'as' keyword
        return (countHomo % modulo) as i32;
    }
}
