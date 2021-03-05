package eu.welfare.newneeds.ui.home.quotes

import androidx.lifecycle.ViewModel
import eu.welfare.newneeds.data.repositories.QuotesRepository
import eu.welfare.newneeds.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    // 1. getQuotes is a suspending function, we can only call it from other suspend fun or coroutine scope
    // 2. Second problem is we don't want to fetch qutoes each time the Qutoes View Model is insantiated, We want only when changed or needed
    // for this Initialization, intialize when needed, we have lazy in kotlin.
    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}