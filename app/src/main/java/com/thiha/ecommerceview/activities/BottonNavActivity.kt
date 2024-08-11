package com.thiha.ecommerceview.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.thiha.ecommerceview.fragments.HomeFragment
import com.thiha.ecommerceview.R
import com.thiha.ecommerceview.databinding.ActivityBottonNavBinding

class BottonNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottonNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBottonNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nav)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpBottomNavWithFragment()
    }

    // setUp bottom Nav with Fragment
    private fun setUpBottomNavWithFragment() {
        switchFragment(HomeFragment())
       binding.bottomNav.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    switchFragment(HomeFragment())
                }


            }

            true

        }
    }

    private fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer,fragment)
            .commit()
    }

}