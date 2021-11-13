/*

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
    

*/

class Solution 
{
    public List<String> ipToCIDR(String ip, int n) 
    {
        List<String> validCIDRBlocks = new ArrayList<String>();
        
        return validCIDRBlocks;
    }
}
