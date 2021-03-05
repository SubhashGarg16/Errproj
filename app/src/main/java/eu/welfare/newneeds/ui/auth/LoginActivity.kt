package eu.welfare.newneeds.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import eu.welfare.newneeds.R
import eu.welfare.newneeds.data.db.AppDatabase
import eu.welfare.newneeds.data.db.entities.User
import eu.welfare.newneeds.data.network.MyApi

import eu.welfare.newneeds.data.repositories.UserRepository
import eu.welfare.newneeds.databinding.ActivityLoginBinding

import eu.welfare.newneeds.ui.home.HomeActivity

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),AuthListener,KodeinAware {
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //val factory = AuthViewModelFactory(repository)




       // setContentView(R.layout.activity_login)

        // Now we need to add this listener to the LoginActivity
        // But first we need to get the ViewModel
        // And also we need to bind the ViewModel to our ActivityModel
        // WE need binding instance. That is generated automatically
        val binding :ActivityLoginBinding  = DataBindingUtil.setContentView(this,R.layout.activity_login)

        // Now we have the binding, now we need to get the ViewModel
        val viewModel = ViewModelProvider(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        // Now we need to define the authListener to our ViewModel
        // we assign this, because this current class contains our AuthListener
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this,Observer{
            if(it!=null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })

    }

    override fun onStarted() {

       // toast("Login  Started")
      //  toast("Login Started")
    }

    override fun onSuccess(user: User) {
      //  toast("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
       // toast(message)
    }
}