package eu.welfare.newneeds.ui.auth

import androidx.lifecycle.LiveData
import eu.welfare.newneeds.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user : User)

    fun onFailure(message:String)
}