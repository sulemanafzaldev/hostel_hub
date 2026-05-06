package com.bkuc.hostelhub.ui.user

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.databinding.ActivityMainBinding
import com.bkuc.hostelhub.utils.StatusBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)


        StatusBar.changeStatusBarColor(this, R.color.color_header)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        //clear stack from nested fragment when click on bottom navigation it
        bottomNavigationView.setOnItemSelectedListener { item ->
            val destinationId = item.itemId

            // Only navigate if we're not already on that destination
            if (navController.currentDestination?.id != destinationId) {
                navController.popBackStack(navController.graph.startDestinationId, false)
                navController.navigate(destinationId)
            }
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}