package com.example.notesappfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.notesappfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val navControl = this.findNavController(R.id.nav_host_fragment_container)
//        NavigationUI.setupActionBarWithNavController(this,navControl)

//        supportActionBar?.hide()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navControl = this.findNavController(R.id.nav_host_fragment_container)
        return navControl.navigateUp()
    }

//    override fun onBackPressed() {
////        val fragment = this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as? NavHostFragment
////        val currentFragment = fragment?.childFragmentManager?.fragments?.get(0) as? IOnBackPressed currentFragment?.onBackPressed()?.takeIf { !it }?.let{ super.onBackPressed() }
//
////        val fragment = this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
////        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
////            super.onBackPressed()
////        }
//    }
}