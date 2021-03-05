package eu.welfare.newneeds.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.welfare.newneeds.data.db.AppDatabase
import eu.welfare.newneeds.data.db.entities.Quote
import eu.welfare.newneeds.data.network.MyApi
import eu.welfare.newneeds.data.network.SafeApiRequest
import eu.welfare.newneeds.data.preferences.PreferenceProvider
import eu.welfare.newneeds.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import java.lang.Exception

private val MINIMUM_INTERVAL = 6

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        val lastSavedAt = prefs.getLastSavedAt()

       // if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
        if (isFetchNeeded(null))

            try {
                val response = apiRequest { api.getQuotes() }
                quotes.postValue(response.quotes)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    private fun isFetchNeeded(savedAt: String?): Boolean {
        //return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
        return true
    }


    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)

//            AppDatabase.invoke(context).getQuoteDao().saveAllQuotes(quotes)
          //  prefs.savelastSavedAt(LocalDateTime.now().toString())


           // db.getQuoteDao().saveAllQuotes(quotes)
        }
    }

}