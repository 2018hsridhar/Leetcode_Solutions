/*
URL := https://leetcode.com/problems/sender-with-largest-word-count/
2284. Sender With Largest Word Count
Lex largest name
Let's toy arond with slice idea here
Uppercase before lowercase in lex ordering ( presumed to work anyways ) 

Careful with JSON marshal of slices

*/
func largestWordCount(messages []string, senders []string) string {
    lwc := 0
    senderWordCount := make(map[string]int)
    spaceChar := " "
    largestSenders := []string{}
    for i := 0; i < len(senders); i++ {
        message := messages[i]
        sender := senders[i]
        tokens := strings.Split(message, spaceChar)
        curMessageWordCount := len(tokens)
        senderWordCount[sender] += curMessageWordCount
        if senderWordCount[sender] > lwc {
            lwc = senderWordCount[sender]
            // nil slices well-behaved
            // nil goes back to garbage collection
            largestSenders = nil
            largestSenders = append(largestSenders, sender)
        } else if senderWordCount[sender] == lwc  {
            largestSenders = append(largestSenders, sender)
        }
    }
    sort.Strings(largestSenders)
    lexLargestName := largestSenders[len(largestSenders) - 1]
    return lexLargestName
}
