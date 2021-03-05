package eu.welfare.newneeds.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

// We will create one function named toast on Context
fun Context.toast(message: String){
     Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
fun Context.getSupportFragmentManager(){

}

fun ProgressBar.show(){
     visibility = View.VISIBLE
}

fun ProgressBar.hide(){
     visibility = View.GONE
}

