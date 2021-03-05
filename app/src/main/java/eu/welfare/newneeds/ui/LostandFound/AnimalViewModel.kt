package eu.welfare.newneeds.ui.LostandFound

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eu.welfare.newneeds.data.db.AppDatabase
import eu.welfare.newneeds.ui.auth.AuthListener
import androidx.lifecycle.ViewModel

class AnimalViewModel() :  ViewModel()  {

    var pid: Int? = null
    var LostOrFound: String? = null
    var Location: String? = null
    var DateofMissing: String? = null
    var DateofFound: String? = null
    var Breed: String? = null
    var Gender: String? = null
    var Color: String? = null
    var Size: String? = null
    var IdentificationMarks: String? = null
    var ApproximateAge: Int? = null
    var WearingCollar: Boolean? = null
    var Email: String? = null
    var Phone: Int? = null
    var OtherInfo: String? = null

    var authListener : AuthListener? = null

fun onSubmitClick(View view){
    authListener?.onStarted()

    if(Location.isNullOrEmpty()) {
        authListener?.onFailure("Add Place")
        return
    }
    if(DateofMissing.isNullOrEmpty()) {
        authListener?.onFailure("Enter Date of missing")
        return
    }
    if(DateofFound.isNullOrEmpty()) {
        authListener?.onFailure("Enter Date when it is found")
        return
    }
    if(Breed.isNullOrEmpty()) {
        authListener?.onFailure("Enter Breed")
        return
    }
    if(Gender.isNullOrEmpty()) {
        authListener?.onFailure("Enter Gender of pet")
        return
    }
    if(Size.isNullOrEmpty()) {
        authListener?.onFailure("Please mension size")
        return
    }
    if(Color.isNullOrEmpty()) {
        authListener?.onFailure("Please mension color")
        return
    }
    if(ApproximateAge?.equals(null) == true) {
        authListener?.onFailure("add Age")
        return
    }
    if(WearingCollar?.equals(null) == true) {
        authListener?.onFailure("Enter Date of missing")
        return
    }
    if(Email.isNullOrEmpty()) {
        authListener?.onFailure("Enter Email")
        return
    }
    if(Phone?.equals(null)==true) {
        authListener?.onFailure("Enter Date of missing")
        return
    }







}

}