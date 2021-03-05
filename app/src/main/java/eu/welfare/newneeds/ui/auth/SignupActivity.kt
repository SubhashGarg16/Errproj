package eu.welfare.newneeds.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import eu.welfare.newneeds.R
import eu.welfare.newneeds.data.db.entities.User
import eu.welfare.newneeds.databinding.ActivityLoginBinding
import eu.welfare.newneeds.databinding.ActivitySignupBinding
import eu.welfare.newneeds.ui.home.HomeActivity
import eu.welfare.newneeds.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_signup)

        val binding : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)

        // Now we have the binding, now we need to get the ViewModel
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        // Now we need to define the authListener to our ViewModel
        // we assign this, because this current class contains our AuthListener
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer{
            if(it!=null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }

            }
        })

    }

    override fun onStarted() {

        toast("Login Started")
    }

    override fun onSuccess(user: User) {
        //  toast("${user.name} is logged in")
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}