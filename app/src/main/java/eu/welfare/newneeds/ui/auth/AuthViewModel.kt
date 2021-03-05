package eu.welfare.newneeds.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import eu.welfare.newneeds.data.repositories.UserRepository
import eu.welfare.newneeds.util.ApiException
import eu.welfare.newneeds.util.Coroutines
import eu.welfare.newneeds.util.NoInternetException


// If we want to use this AuthViewModel as model for LoginActivity then we have to enable two way data binding for it.
class AuthViewModel(
    private  val repository: UserRepository
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var passwordConfirm : String? = null


    var authListener : AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    // How we will show the message on the activity. That we do with the help of AuthListener interface.
    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalide email or password")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let{
                    authListener?.onSuccess(authResponse.user)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }


        }
    }


    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignupButtonClick(view : View){
        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }

        if(email.isNullOrEmpty()){
            authListener?.onFailure("Email is required")
            return
        }

        if(password.isNullOrEmpty()){
            authListener?.onFailure("Please enter a password")
            return
        }

        if(password != passwordConfirm){
            authListener?.onFailure("Password did not match")
            return
        }



      /*  if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalide email or password")
            return
        }
*/
        Coroutines.main {
            try {
                val authResponse = repository.userSignup(name!!,email!!, password!!)
                authResponse.user?.let{
                    authListener?.onSuccess(authResponse.user)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }


        }
    }
}