package eu.welfare.newneeds.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.welfare.newneeds.data.db.entities.Quote

@Dao
interface QuoteDao {

    // We will save all the quotes to our local db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes : List<Quote>)


    // We dont have to make this function as suspende
    @Query("select * from Quote")
    fun getQuotes() : LiveData<List<Quote>>


}