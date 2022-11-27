# https://leetcode.com/problems/decode-xored-array/description/
# 1720. Decode XORed Array
class Solution:
    def decode(self, encoded: List[int], first: int) -> List[int]:
        decoded = []
        decoded.append(first)
        # PY treats primitives like objects?
        for i in range(0,len(encoded),1):
            xorRes = encoded[i] ^ first
            decoded.append(xorRes)
            first = xorRes
        return decoded
