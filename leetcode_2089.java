/*
https://stackoverflow.com/questions/12992925/c-correct-way-to-return-pointer-to-array-from-function

https:/https://stackoverflow.com/questions/12992925/c-correct-way-to-return-pointer-to-array-from-function
/stackoverflow.com/questions/12992925/c-correct-way-to-return-pointer-to-array-from-function

2089. Find Target Indices After Sorting Array
URL = https://leetcode.com/problems/find-target-indices-after-sorting-array/

Questions candidates should ask
1. Ask if the input is 0-indexed
2. Ask if target value is guaranteed to be an element of the array
3. Return is non-decrasing order : return the list of indices after the sort ( indices change )

Sorting entails well-ordered elements, so indices guaranteed order
Remember that a comparison func must return an int, as we have three orderings 
    a<b,a>b,a=b => encode as {-1,1,0}
    
https://www.tutorialspoint.com/c_standard_library/c_function_qsort.htm

COMPLEXITY
Let N := #-elements in nums array
TIME = O()
SPACE = O()

TEST CASES : 
(A)
(B)
(C)
(D)
(E)
(F)

*/

/**
 * Note: The returned array must be malloced, assume caller calls free().
 * Note the casting of pointer type from void* to int* here as well
 * Ask if our operations are read-only or read-write. Tells usage of pointers
 */
int cmpfunc (const void *a, const void* b)
{
    return (*(int*)a - *(int*)b);
}

// So it return size set here?
int* targetIndices(int* nums, int numsSize, int target, int* returnSize)
{
    // note how functions are passed in as "names" here : harken to symbol tables in compilers
    qsort(nums,numsSize,sizeof(int), cmpfunc); 
    // We could do a count and malloc a new array as well
    int count = 0;
    for(int i = 0; i < numsSize; ++i)
    {
        int* elemAddr = (nums + i); // arithmetic on pointers just changes val stored in pointer
        int elem = *(elemAddr);
        if(elem == target)
        {
            count++;
        }
    }
    // printf("Count = %d\n", count);
    // [2] Perform malloc, and do another pass
    // int results[count];
    // the "results" pointer here went out of scope. Hence expect more use of parameter passing in the C-language as well    
    int wIdx = 0;
    *returnSize = count; // we're gonna malloc now to handling stack frame scoping issues
    int *results = (int*)malloc(sizeof(int)*count);
        // set the pointer type later ... after you malloc your blocks
        // hey yo could do (char*)malloc(sizeof(int)*count) and iterate as if you had a char pointer
    for(int i = 0; i < numsSize; ++i)
    {
        if(*(nums + i) == target)
        {
            *(results + wIdx) = i;
            // *(nums+wIdx++) = i;
            // *(nums+wIdx) = i;  // Write-operation on pointers : we do not overwrite the address
            wIdx++;
            // Notice our post-fix magic here : do full eval in EXPR ... then the changes
        }
    }
    return results;
    // In C, if you return a pointer to an array, return the siez as well :-O
}
