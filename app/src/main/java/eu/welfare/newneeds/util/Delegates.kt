package eu.welfare.newneeds.util

import kotlinx.coroutines.*

// Deferred type is basically a job with the result


fun<T> lazyDeferred(block : suspend CoroutineScope.() -> T) : Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}