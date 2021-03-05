package eu.welfare.newneeds.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// It is similar to static in java
object Coroutines {
    fun main(work: suspend (()-> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    // We require this function, as we now have to save the remote db to our local db
    fun io(work: suspend (()-> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }
}