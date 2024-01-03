/*
2694. Event Emitter
URL := https://leetcode.com/problems/event-emitter/?envType=study-plan-v2&envId=30-days-of-javascript
JS focuses on the notion of emitted events.
Node has event emitting

1. Subscribe to events
2. Emit the events

12 minute to solution
EventEmitter as a common JS interview question

*/
class EventEmitter {
    
    /**
     * @param {string} eventName
     * @param {Function} callback
     * @return {Object}
     */

    // Constructor as an action
    constructor() {
        this.eventListeners = new Map()
    }

    subscribe(eventName, callback) {

        // Call the callback once event emitting
        // Call the listeners in order of their subscription
        // Return array of results
        // Return unsubscribe object -> enable unsubscription
        // When unsubscribe -> remove callback from the list and ret undefined
        // callback not as a set -> as an array
        // No referentially identitical callbacks -> assume each is unique
        if(!this.eventListeners.has(eventName)){
            this.eventListeners.set(eventName,Array())
        } 
        this.eventListeners.get(eventName).push(callback)

        return {
            // Object gets variables passed in from subscribe method too ( on return ) 
            // pass callback to scope here
            // Single arg : 0-index order of subscription made
            unsubscribe: () => {
                // Always have an existing subscription
                let myListeners = this.eventListeners.get(eventName)
                const indexCallback = myListeners.indexOf(callback)
                myListeners.splice(indexCallback,1) // remove 1 element from callback index
                return undefined
            }
        };
    }
    
    /**
     * @param {string} eventName
     * @param {Array} args
     * @return {Array}
     */
     // Handle optional values too?
    emit(eventName, args = []) {
        let callbackResults = []
        if(!this.eventListeners.has(eventName)){
            return callbackResults
        }
        let listeners = this.eventListeners.get(eventName)
        listeners.forEach((callback) => {
            callbackResults.push(callback(...args))
        })
        return callbackResults
    }
}

/**
 * const emitter = new EventEmitter();
 *
 * // Subscribe to the onClick event with onClickCallback
 * function onClickCallback() { return 99 }
 * const sub = emitter.subscribe('onClick', onClickCallback);
 *
 * emitter.emit('onClick'); // [99]
 * sub.unsubscribe(); // undefined
 * emitter.emit('onClick'); // []
 */
