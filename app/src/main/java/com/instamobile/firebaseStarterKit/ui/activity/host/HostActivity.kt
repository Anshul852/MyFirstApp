package com.instamobile.firebaseStarterKit.ui.activity.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.instamobile.firebaseStarterKit.R
import com.instamobile.firebaseStarterKit.databinding.ActivityHostBinding
import com.instamobile.firebaseStarterKit.databinding.DrawerHeaderLayoutBinding
import com.instamobile.firebaseStarterKit.utils.Prefs

class HostActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout

        setupNavigation()
        setupDrawerHeader()
    }

    private fun setupNavigation() {
        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)

        val hasCompletedOnboarding = Prefs.getInstance(this).hasCompletedOnBoarding
        val currentUser = auth.currentUser

        if (!hasCompletedOnboarding) {
            navGraph.setStartDestination(R.id.onBoardingFragment)
        } else if (currentUser == null) {
            navGraph.setStartDestination(R.id.authFragment)
        } else {
            navGraph.setStartDestination(R.id.homeFragment)
            NavigationUI.setupWithNavController(binding.navigationView, navController)
        }

        navController.graph = navGraph
    }

    private fun setupDrawerHeader() {
        val headerView = binding.navigationView.getHeaderView(0)
        val headerBinding = DrawerHeaderLayoutBinding.bind(headerView)
        // Here you can update the header with user info if needed
        // For example: headerBinding.userName.text = currentUser?.displayName
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
