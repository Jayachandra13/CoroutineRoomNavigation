package com.jc.kotlincoroutin.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.jc.kotlincoroutin.R

class NavigationActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//
//        val host: NavHostFragment =
//            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment?
//                ?: return
//        val navController = host.navController
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBar(navController, appBarConfiguration)
//
//        setupNavigationMenu(navController)
//
//        setupBottomNavMenu(navController)
//
//        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.home_dest, R.id.deeplink_dest),
//            drawerLayout
//        )
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            val dest: String = try {
//                resources.getResourceName(destination.id)
//            } catch (e: Resources.NotFoundException) {
//                destination.id.toString()
//            }
//            Toast.makeText(this@NavigationActivity, "Navigated to $dest", Toast.LENGTH_SHORT).show()
//            Log.d("NavigationActivity", "Navigated to $dest")
//        }
//
//    }
//
//    private fun setupBottomNavMenu(navController: NavController) {
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
//        bottomNav?.setupWithNavController(navController)
//    }
//
//    private fun setupNavigationMenu(navController: NavController) {
//        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
//        sideNavView?.setupWithNavController(navController)
//    }
//
//    private fun setupActionBar(
//        navController: NavController,
//        appBarConfiguration: AppBarConfiguration
//    ) {
//        setupActionBarWithNavController(navController, appBarConfiguration)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val retValue = super.onCreateOptionsMenu(menu)
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        if (navigationView == null) {
//            menuInflater.inflate(R.menu.overflow_menu, menu)
//            return true
//        }
//        return retValue
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.my_nav_host_fragment).navigateUp(appBarConfiguration)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment)) || super.onOptionsItemSelected(
//            item
//        )
//    }
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        val drawerLayout: DrawerLayout? = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home_dest, R.id.deeplink_dest),
            drawerLayout
        )

        setupActionBar(navController, appBarConfiguration)

        setupNavigationMenu(navController)

        setupBottomNavMenu(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            Toast.makeText(
                this@NavigationActivity, "Navigated to $dest",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.nav_view)
        sideNavView?.setupWithNavController(navController)
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val retValue = super.onCreateOptionsMenu(menu)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        if (navigationView == null) {
            menuInflater.inflate(R.menu.overflow_menu, menu)
            return true
        }
        return retValue
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.my_nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp(appBarConfiguration)
    }
}