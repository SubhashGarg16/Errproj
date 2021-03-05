package eu.welfare.newneeds.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import eu.welfare.newneeds.R
import eu.welfare.newneeds.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (binding.navView.getHeaderCount() > 0) {
            // avoid NPE by first checking if there is at least one Header View available
            val headerLayout: View = binding.navView.getHeaderView(0)
        }
        setSupportActionBar(binding.toolbar)
        val navController= Navigation.findNavController(this, R.id.fragment)

        //val nav_view : NavigationView = findViewById(R.id.nav_view)

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)



    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            findViewById(R.id.drawer_layout)
        )
    }
}