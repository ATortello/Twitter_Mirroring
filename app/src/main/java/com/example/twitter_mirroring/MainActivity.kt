package com.example.twitter_mirroring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.twitter_mirroring.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set a new toolbar
        setActionBar(findViewById(R.id.toolbarMain))

        //Setting up the navigation for the app
        configNav()
    }

    //Method to indicate the bottom navigation menu and the navigation controller
    fun configNav() { NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragContent)) }
}