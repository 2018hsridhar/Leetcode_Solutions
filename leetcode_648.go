/*
URL := https://leetcode.com/problems/replace-words/
648. Replace Words

n := LEN(SENTENCE)
conv dictionary into some nice map structure for fast lookup
Go thru each word in sentence -> start constructing the roots -> check if in map or not
If in map : replace -> else, do not replace
Do we want to use a trie ( vs append via string builder, which honestly seems easier too ) ?

S := O(D)
WHERE D := len(dictionary)

Better : join operation on a nice sentence ( splice it ) 

Input validation : 
    always at least 1 word in sentence with at least len = 1
    No weird space handling
    At least 1 valid input in dictionary too

16 mins to solution

*/
func replaceWords(dictionary []string, sentence string) string {
    dictMap := make(map[string]bool)
    constructDictMap(dictionary, dictMap)
    tokens := strings.Fields(sentence)
    newTokens := []string{}
    builder := strings.Builder{}
    for _, successor := range tokens {
        // So many langs introduce builder notation :-) 
        // gaaah why not clear guilder each for loop iteration?
        hitRoot := false
        for _, ch := range successor {
            builder.WriteRune(ch)
            // gaaah at this need for constant string creation
            curRoot := builder.String() 
            // Early break for shortest length
            if dictMap[curRoot] {
                newTokens = append(newTokens, curRoot)
                hitRoot = true
                break
            }
        }
        // if no root in the dictionary for a given successor -> go add successor anyways
        if !hitRoot {
            newTokens = append(newTokens, successor)
        }
        builder.Reset()
    }
    seperator := " "
    // strings -> package as string -> type
    myFinalString := strings.Join(newTokens, seperator)
    return myFinalString
}

func constructDictMap(dictionary []string, dictMap map[string]bool) {
    for _, root := range dictionary {
        dictMap[root] = true
    }
}
