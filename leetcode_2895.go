/**
 * @param {number[]} processorTime
 * @param {number[]} tasks
 * @return {number}
 */
 /*
 URL := https://leetcode.com/problems/minimum-processing-time/
2895. Minimum Processing Time
As usual, consider sorting the tasks -> tasks need not be done in an exact order
Each process has 4 cores :-) -> leverage that for paralleilism
Process avail earliest = go do work taking more time then?

{n processors * 4 cores = n*4 tasks  }
Task Assignation problem :-) 

Min(max(summation)) Greedy Sorts Approach

Complexity
Let T := #-tasks
Let P := #-processors
Time := O(TlgT) + O(PlgP) + O(T)
Space := O(1) ( IMPLICIT ) O(...) ( EXPLICIT ) 
 */
var minProcessingTime = function(processorTime, tasks) {
    tasks.sort(function(a,b){return b-a;});
    processorTime.sort(function(a,b){return a - b;}); // assume array protoType?
    // Need sort function -> gaaah stinking dictionary default sorting ( string conv ) 10 > 8
    var processorMaxTime = 0;
    var n = processorTime.length; // length is data property -> not prototype function
    var t = tasks.length;
    var numCores = 4;
    var p = 0;
    // console.log(tasks);
    // console.log(processorTime);
    for(let i = 0; i < t; i += 4) {
        for(let j = 0; j < numCores; j++) { // 4 iters tight-bounded loop
            var cpuTaskTime = tasks[i+j];
            let curtime = processorTime[p] + cpuTaskTime;
            // let proccessorTime = processorTime[p] + cpuTaskTime;
            // console.log(processorTime); // why is this treated as an array? That's confounding! OMG Initial type var keyword
            // Gaah built in ... with Math package
            processorMaxTime = Math.max(processorMaxTime, curtime);
        }
        p++;
    }
    return processorMaxTime;
};

