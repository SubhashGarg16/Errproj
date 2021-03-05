package eu.welfare.newneeds.ui.LostandFound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import eu.welfare.newneeds.R

class FoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found)

        val SubmitButton1 : Button = findViewById(R.id.Submit_btn1)

        val emailEt1: EditText = findViewById(R.id.email_ET1)
        emailEt1.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailEt1.text.toString()).matches())
                    SubmitButton1.isEnabled = true
                else{
                    SubmitButton1.isEnabled = false
                    emailEt1.setError("Invalid Email")
                }

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
}