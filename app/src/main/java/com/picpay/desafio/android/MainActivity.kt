package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.picpay.desafio.android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var destinationListener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.toolbar.setupWithNavController(navController)

        configureDestinationListener()
    }

    private fun configureDestinationListener() {
        destinationListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.contactsFragments) {
                    binding.toolbar.visibility = View.GONE
                } else {
                    binding.toolbar.visibility = View.VISIBLE
                }

            }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(destinationListener)
    }
}
