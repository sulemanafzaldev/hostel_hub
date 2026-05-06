package com.bkuc.hostelhub.ui.admin.admindashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bkuc.hostelhub.R
import com.bkuc.hostelhub.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Initialize NavHostFragment and NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // 2. Setup Bottom Navigation with NavController
        binding.bottomNavigationView.setupWithNavController(navController)

        // 3. Optional: Update toolbar title automatically based on destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.adminHomeFragment -> setToolbarTitle("Dashboard")
                R.id.roomsFragment -> setToolbarTitle("Manage Rooms")
                R.id.adsFragment -> setToolbarTitle("Advertisements")
                R.id.rulesFragment -> setToolbarTitle("Hostel Rules")
                else -> setToolbarTitle("Hostel Hub")
            }
        }
    }

    fun setToolbarTitle(title: String) {
        binding.tvTitle.text = title
    }
}