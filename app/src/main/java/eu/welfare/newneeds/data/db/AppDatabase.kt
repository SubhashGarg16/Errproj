package eu.welfare.newneeds.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import eu.welfare.newneeds.ui.LostandFound.Animal
import eu.welfare.newneeds.ui.LostandFound.AnimalDao
import eu.welfare.newneeds.data.db.entities.Quote
import eu.welfare.newneeds.data.db.entities.User

// We can use this class to get our UserDao and from UserDao we can get all the methods for db

@Database(
    entities = [User::class, Quote::class, Animal::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao() : UserDao
    abstract fun getQuoteDao() : QuoteDao
    abstract fun getAnimalDao(): AnimalDao

    // Now we will define one companion object to create our AppDatabase
    companion object{

        @Volatile
        private var instance : AppDatabase? = null
        private val LOCK=Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase.db"
            ).build()




    }
}