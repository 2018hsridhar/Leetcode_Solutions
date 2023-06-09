/*
2525. Categorize Box According to Criteria
URL = https://leetcode.com/problems/categorize-box-according-to-criteria/description/
*/
func categorizeBox(length int, width int, height int, mass int) string {
    volume := length * width * height
    isBulky := false
    isHeavy := false
    tenFour := (int)(math.Pow(10,4))
    tenNine := (int)(math.Pow(10,9))
    if length >= tenFour || width >= tenFour || height >= tenFour || volume >= tenNine {
        isBulky = true
    }
    if mass >= 100 {
        isHeavy = true
    }
    if isBulky && isHeavy {
        return "Both"
    } else if !isBulky && !isHeavy {
        return "Neither"
    } else if isBulky && !isHeavy {
        return "Bulky"
    } else if !isBulky && isHeavy {
        return "Heavy"
    }
    return ""
}
