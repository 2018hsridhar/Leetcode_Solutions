/*
URL := https://leetcode.com/problems/smallest-string-with-a-given-numeric-value/
1663. Smallest String With A Given Numeric Value
Idea : Greedy and combinatorial for lexicographic. Desire for linear time and linear space.
    Avoid recursive approaches.

    bfz -> aez ( we can do better : summation persists ) 
    Wow correct algo yet TLE somehow
    Gotta work with huge n,k values too WOAH!
    How to do this faster than? Find number to fill up till instead? That's possible?
*/
func getSmallestString(n int, k int) string {
    fillRemWithZ := false
    builder := strings.Builder{}
    for n > 1 {
        numRemLets := n - 1
        maxRange := (numRemLets * 26)
        nextK := (k - 1)
        if maxRange >= nextK {
            // https://stackoverflow.com/questions/40310333/how-to-append-a-character-to-a-string-in-golang
            builder.WriteString("a")
            n--
            k--
        } else {
            nextLetter := (byte)('a' + (k - maxRange) - 1)
            builder.WriteString((string)(nextLetter))
            n--
            fillRemWithZ = true
            // Fill the rest up with z's now?
            // Based on difference method too
            break
        }
    }
    if(fillRemWithZ) {
        for n > 0 {
            builder.WriteString(string('z'))
            n--
        }
    } else {
        // n = 1 now
        // no char type : byte or rune only
        // smallestStr += strconv.Itoa((int)('a') + n)
        builder.WriteString((string)((byte)((int)('a') + k - 1)))
    }
    return builder.String()
}
