package eu.welfare.newneeds.data.repositories

import eu.welfare.newneeds.data.db.AppDatabase
import eu.welfare.newneeds.data.db.entities.User
import eu.welfare.newneeds.data.network.MyApi
import eu.welfare.newneeds.data.network.SafeApiRequest
import eu.welfare.newneeds.data.network.responses.AuthResponse


// This is the UserRepository that is communicating with the backend API  through MyApi
// and will communicate with the ViewModel
class UserRepository(
    private val api: MyApi,
    private val db : AppDatabase
) : SafeApiRequest() {
    suspend fun userLogin(email:String,password:String) : AuthResponse{
        return apiRequest { api.userLogin(email,password)}
    }

    suspend fun userSignup(
        name:String,
        email: String,
        password: String
    ) : AuthResponse{
        return apiRequest { api.userSignup(name,email,password) }
    }

    suspend fun saveUser(user : User)=
        db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}