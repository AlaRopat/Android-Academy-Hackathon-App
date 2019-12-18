package com.academy.app.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.academy.app.R
import com.academy.app.mvvm.viewModel.ActivityViewModel
import com.academy.app.view.category.CategoryListFragment
import com.academy.hackathonapp.view.settings.SettingsFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.academy.app.R.layout.activity_main)

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)
        supportFragmentManager.beginTransaction().replace(linLay.id, MainFragment()).commit()

        drawer.closeDrawer(GravityCompat.START)
        nav_view.setNavigationItemSelectedListener(this)
        setSupportActionBar(my_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.academy.app.R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else drawer.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.categories -> {
                showFragment(CategoryListFragment(), true)
                drawer.closeDrawer(GravityCompat.START)
                true

            }
            R.id.main_screen -> {
                showFragment(MainFragment(), false)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.settings -> {
                showFragment(SettingsFragment(), true)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }
            else -> false
        }
    }

    fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction
            .replace(linLay.id, fragment)
            .commit()
    }

    fun pop() {
        supportFragmentManager.popBackStack()
    }
}
