/*

The ATOI problem!

Given a systems approach, data structures should not be much here
But do problem in C/C++ for point manipulation and to understand c-style '\0'-terminated strings


URL = https://leetcode.com/problems/string-to-integer-atoi/
8. String to Integer (atoi)

THOUGHT PROCESS :
Convert c-style string to a 32-bit SIGNED integer ( must account for MSB here ) 
Ignore all leading whitespace ( use boolean hit-nonwhitespace flag )
Assuming '\0' denotes the null terminator ( BUT do ask what denotes end-of-input : can be carriage return too ) 


Arrays are easy to debug as their addresses are contiguous and increment too!
When dealing iwth pointers, use "++" in lieu of "+1" for data type accounting

Read until a non-digit character OR end of input ( denoted by '\0') is reached!


DATA CONSIDERATIONS : 
Types of whitespace : only ' '
If no diits read at all -> return 0 as integer. Perfect isDigit() check
String length is reasonably bound : [ 0, 200 ]


32-bit signed integer range : clamping of integer to the lower bound and to the upper bound
White space will be leading before the sign : not afect the sign!
Characters = "+-. [a-z][A-Z]" really
English letters : ( lower-case and upper-case ) 

Handling of leading whitespaces and trailing whitespaces, as well as represntation ( ' ' VS \\s+ ) 

https://stackoverflow.com/questions/53566029/1-31-cannot-be-represented-by-type-int
COMPUTATIONAL 

(2147483648)10 = (10000000000000000000000000000000)_2 = (10000000 00000000 00000000 00000000)_2

*/
int myAtoi(char * s)
{
    int res = 0;
    char* idx = s;
    char val = *idx;
    typedef unsigned int uint32_t;
    // uint bit_mask = (1<<31);
    // printf("UINT_MAX    :   %u\n", (unsigned int) UINT_MAX);
    // printf("Unsigned int val = %u\n", bit_mask);
    uint32_t one_MSB_bit_mask = UINT32_C(1) << 31;
    uint32_t zero_MSB_bit_mask = UINT32_C(0) << 31;
    // printf("UINT_MAX    :   %u\n", (unsigned int) UINT_MAX);
    printf("Unsigned int val of zero MSB bit mask = %u\n", (unsigned int) zero_MSB_bit_mask);
    printf("Unsigned int val of one MSB bit mask = %u\n", (unsigned int) one_MSB_bit_mask);
    bool hitNonWhiteSpace = false;
    while(val != '\0')
    {
        if(val == ' ' && hitNonWhiteSpace == false)
        {
            ++idx;
            continue;
        }   
        else if ( val == '-')
            res = res || (one_MSB_bit_mask);
        else if ( val == '+')
            res = res || (zero_MSB_bit_mask);
        
        // printf("Res = %i\n", res);
        
        printf("Old Val = %c\n", val);                
        ++idx;
        val = *idx;
        // printf("New Val = %c\n", val);                
    }
    
    return res;
}
