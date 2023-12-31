package ru.iw.bulletinboard

import android.os.Bundle
import android.view.MenuItem
import android.view.TextureView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.core.Context
import ru.iw.bulletinboard.databinding.ActivityMainBinding
import ru.iw.bulletinboard.dialogs.DialogConst
import ru.iw.bulletinboard.dialogs.DialogHelper


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var accountEmail: TextView
    private lateinit var binding: ActivityMainBinding
    private val dialogHelper = DialogHelper(this)
    val fireBaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()


    }

    override fun onStart() {
        super.onStart()
        uiUpdate(fireBaseAuth.currentUser)
    }

    private fun init() {
        val toggle =
            ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.mainContent.myToolsBar,
                R.string.open,
                R.string.close
            )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
//change text accountEmail on header
        accountEmail = binding.navView.getHeaderView(0).findViewById(R.id.accountEmail)
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
                dialogHelper.showSingDialog(DialogConst.SING_UP_STATE)
            }
            R.id.id_sing_in -> {
                dialogHelper.showSingDialog(DialogConst.SING_IN_STATE)
            }
            R.id.id_sing_out -> {
                uiUpdate(null)
                fireBaseAuth.signOut()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?) {
        accountEmail.text = if (user == null) resources.getString(R.string.not_reg) else user.email
    }
}