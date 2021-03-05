package eu.welfare.newneeds.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// We define this constant so that there is only one user at any given instance of time.
const val CURRENT_USER_ID=0
// This will map to the Table name and create a table with the name User
@Entity
data class User(
    var id : Int? = null,
    var name : String? = null,
    var email : String? = null,
    var password : String? = null,
    var email_verified_at: String? =null,
    var created_at: String? =null,
    var updated_at: String? =null
){
    @PrimaryKey(autoGenerate = false)
    var uid : Int = CURRENT_USER_ID

}
