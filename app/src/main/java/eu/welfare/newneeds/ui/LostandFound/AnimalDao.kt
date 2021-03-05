package eu.welfare.newneeds.ui.LostandFound

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.welfare.newneeds.ui.LostandFound.Animal

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(animals: Animal)
    suspend fun delete(animals: Animal)

    @Query("Select * from Animal_Table")
    fun getAnimals(): LiveData<List<Animal>>
}