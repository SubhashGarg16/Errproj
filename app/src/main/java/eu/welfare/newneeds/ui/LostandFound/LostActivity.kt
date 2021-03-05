package eu.welfare.newneeds.ui.LostandFound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import eu.welfare.newneeds.R
import eu.welfare.newneeds.databinding.ActivityLostBinding

private val Unit.matches: Unit
    get() {}

class LostActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_lost)



        val binding : ActivityLostBinding = DataBindingUtil.setContentView(this, R.layout.activity_lost)
        val SubmitButton : Button = findViewById(R.id.Submit_btn)
        //val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        //        binding.viewmodel = viewModel
        val viewModel = ViewModelProviders.of(this).get(AnimalViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this


        val emailEt: EditText = findViewById(R.id.email_ET)
        emailEt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches())
                 SubmitButton.isEnabled = true
                else{
                    SubmitButton.isEnabled = false
                    emailEt.setError("Invalid Email")
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        SubmitButton.setOnClickListener { this }

    }
    val PhoneNumber: EditText = findViewById((R.id.Phone_ET))
    private  fun validate() : Boolean{
        if (PhoneNumber.text.toString().isEmpty()){
            PhoneNumber.error = "Please Enter Phone Number"
            return false
        }
        return  true
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.Submit_btn->
            {
                if (validate()){
                    Toast.makeText(applicationContext,"Done",Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}