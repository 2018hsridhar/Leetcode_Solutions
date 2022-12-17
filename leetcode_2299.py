'''
URL = https://leetcode.com/problems/strong-password-checker-ii/
2299. Strong Password Checker II

'''
class Solution:
    def strongPasswordCheckerII(self, password: str) -> bool:
        # Check that we meet all the predicates for strong passwords.
        hasAtLeastEightCharacters = (len(password) >= 8)
        hasAtLeastOneLowerCaseLetter = False
        hasAtLeastOneUpperCaseLetter = False
        hasAtLeastOneDigit = False
        hasAtLeastOneSpecialCharacter = False
        noTwoCharactersAdjacent = True # Assumed until proven otherwise.
        specialCharactersString = "!@#$%^&*()-+"
        for idx, letter in enumerate(password):
            if(letter.isupper()):
                hasAtLeastOneUpperCaseLetter = True;
            if(letter.islower()):
                hasAtLeastOneLowerCaseLetter = True
            if(letter.isdigit()):
                hasAtLeastOneDigit = True;
            if(specialCharactersString.find(letter) != -1):
                hasAtLeastOneSpecialCharacter = True;
            if(idx < len(password) - 1 and password[idx] == password[idx+1]):
                noTwoCharactersAdjacent = False
        isPasswordStrong = (
            hasAtLeastEightCharacters and
            hasAtLeastOneLowerCaseLetter and 
            hasAtLeastOneUpperCaseLetter and 
            hasAtLeastOneDigit and 
            hasAtLeastOneSpecialCharacter and 
            noTwoCharactersAdjacent
        )
        return isPasswordStrong
