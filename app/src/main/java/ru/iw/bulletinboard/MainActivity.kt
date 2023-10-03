package ru.iw.bulletinboard

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }


    private fun init() {
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, myToolsBar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.id_my_ads -> {

            }
            R.id.id_cars -> {

            }
            R.id.id_pc -> {

            }
            R.id.id_smartphones -> {

            }
            R.id.id_dm -> {

            }
            R.id.id_sing_up -> {

            }
            R.id.id_sing_in -> {

            }
            R.id.id_sing_out -> {

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}