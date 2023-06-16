/**
 * @return {Generator<number>}
 */
 /*
 Generator functions based on function*() syntax
 Consume next value via gen.next() method
 Exec until `yield` encountered
 https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Generator
 The infinite generator!
 Iterate once only
 Exec until yield encountered
 --- read up on generators!

function*() : tells we have a generator
Need `yield` statements in generators : special type of return -> only in a generator
Purpose : ( run code->return val ) cyclically
generator function leads to generator object for use

Want an infinite loop without crashing your program;
    Exec one step @ a time
    say, for ID generation!

Can use generators as an iterator
    has a .next() property
    .next() gives a value,done combo
    less work to create an iterator
    Array -> Iterator-style syntax. Some libs prefer iterators over arrays

while (object.next().done !== false) {
    // EXEC CODE
}

 */
var fibGenerator = function*() {
    yield 0
    yield 1
    let termOne = 0
    let termTwo = 1
    while ( true ) {
        let termThree = termOne + termTwo
        termOne = termTwo
        termTwo = termThree
        yield termThree
    }
};

/**
 * const gen = fibGenerator();
 * gen.next().value; // 0
 * gen.next().value; // 1
 */

 // Value and done property
 // Goes to the next `yield` until no more code to execute
 // Run code in segments : (code->yield) 
// Gen func to create gen object ---> is a brand-new instance!
//  https://www.youtube.com/watch?v=IJ6EgdiI_wU&t=651s

// Create new generator objects : reset IDs!

