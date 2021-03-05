package eu.welfare.newneeds.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import eu.welfare.newneeds.data.db.entities.CURRENT_USER_ID
import eu.welfare.newneeds.data.db.entities.User

// This interface is a data access object

@Dao
interface UserDao {

     // function to insert or update the user
    // If the user is successfully inserted, we get the inserted row id.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user:User) : Long

    @Query("select * from user where uid=$CURRENT_USER_ID")
    fun getUser() : LiveData<User>


}