/*
URL := https://leetcode.com/problems/apply-discount-to-prices/
2288. Apply Discount to Prices
*/

/*
Ya strings/regexp packages LOL
String -> slice -> String again
*/
func discountPrices(sentence string, discount int) string {
    // Unicode-spacing / ask the char set type
    tokens := strings.Fields(sentence)
    newTokens := []string{}
    multiplier := (float64)(100-discount)/(float64)(100.0)
    for _, token := range tokens {
        // RE2  regex 2 regexp 
        // + : 1 or unlimited ( vs * 0 or unlimited )
        // REGEXPs : begin and end of strings
        matchedPrice, _ := regexp.MatchString(`^\$\d+$`, token)
        if matchedPrice {
            priceStr := token[1:]
            // How wide is the returned type?
            // 32-bit and 64-bit floats
            priceNum, _ := strconv.ParseFloat(priceStr,64)
            newPriceNum := priceNum * multiplier
            newToken := "$" + strconv.FormatFloat(newPriceNum, 'f',2,64)
            newTokens = append(newTokens, newToken)
        } else {
            newTokens = append(newTokens, token)
        }
    }
    spaceStr := " "
    stringResult := strings.Join(newTokens[:], spaceStr)
    return stringResult
}
