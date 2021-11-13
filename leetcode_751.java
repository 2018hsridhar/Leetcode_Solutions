/*

It is a bit manipulation question as well

URL = https://leetcode.com/problems/ip-to-cidr/
751. IP to CIDR

IP->CIDR problem

Input = 32-bit UNSIGNED integer
Each group of 8 bits printed as a decimal number 
Groups are split by the '.' character

CIDR blocks : denote specific sets of IP addresses
[ base_IP_adrress : / : prefix_len_k ]
Prefix := leading bits here

We are given a starting IP address, and a number n : number to cover
Use as few CIDR blocks as possible ( optimization problem ) 
Cover IP addresses in the INCLUSIVE range [ ip, ip + n - 1] 

Does the 'n' value increment only our last grouping here?
You can not use a CIDR block that covers addresses outside the desired range as well ! 
    -> Must be strictly within !
    If it does not exist ... what to return though?
Can we leverage a power of two range trick here as well? HUH?
    See the [.8,.15] coverage block
Notice how /32 prefix always covers one VERY VERY specific address!
    -> hence, we always get validity
    
Given one IP address here : guarantee "_._._._", "___.___.___.___" [ 7, 15] [ 4+3, 12+3 ] reasoning
1 <= N <= 1000 
( IP + x ) guaranteed to be a valid IPv4 address too :-)
1000 = 255+255+255+235

TEST BENCH : 
(A) 255.255.252.255, 765
    
(B)
(C)
(D)
(E)

COMPLEXITY
TIME = ___
SPACE = ___

Consider an ampersand operation with the bitwise prefix here as well

If IP address is a worst case here, such as "255.255.255.255" -> well, we can not increment by n here\
But what if "255.255.252.255"? Where n = 765? 


Would have had a reset take place here : 
    254.255.255.255
    255.0.0.0

Can we test for our 1 shift here as well?
How to manipulate our bits for easy addition here as well?
    

*/

class Solution {
public:
    vector<string> ipToCIDR(string ip, int n) 
    {
        vector<String> validCIDRBlocks;
        
        return validCIDRBlocks;        
    }
};





