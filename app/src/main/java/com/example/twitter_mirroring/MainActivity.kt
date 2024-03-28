package com.example.twitter_mirroring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.twitter_mirroring.databinding.ActivityMainBinding
import com.example.twitter_mirroring.view.ui.fragments.HomeFragment
import com.example.twitter_mirroring.view.ui.fragments.MessagesFragment
import com.example.twitter_mirroring.view.ui.fragments.NotificationsFragment
import com.example.twitter_mirroring.view.ui.fragments.SearchFragment
import com.example.twitter_mirroring.view.ui.fragments.SpacesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     //Implementing ViewBinding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting up the navigation for the app
//        configNav()
        
        val homeFragment = HomeFragment()
        val searchFragment = SearchFragment()
        val communitiesFragment = SpacesFragment()
        val notificationFragment = NotificationsFragment()
        val messageFragment = MessagesFragment()
        
        binding.bnvMenu.setOnNavigationItemSelectedListener { 
            when(it.itemId) {
                R.id.navHomeFragment -> { setCurrentFragment(homeFragment) }
                R.id.navSearchFragment -> { setCurrentFragment(searchFragment) }
                R.id.navCommunitiesFragment -> { setCurrentFragment(communitiesFragment) }
                R.id.navNotificationsFragment -> { setCurrentFragment(notificationFragment) }
                R.id.navDirectMessagesFragment -> { setCurrentFragment(messageFragment) }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
    }

    //Method to indicate the bottom navigation menu and the navigation controller
//    fun configNav() {
//        NavigationUI.setupWithNavController(bnvMenu, Navigation.findNavController(this, R.id.fragmentContainer))
//    }
}