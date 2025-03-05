package com.luminuses.easyshopmvvmcleanarch.ui

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

    }

    private fun setupNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(binding.bottomNavigation, navController)

        navController.addOnDestinationChangedListener{  _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    updateBottomNavigationVisibility(true)
                    supportActionBar?.hide()
                }
                R.id.cartFragment -> {
                    updateBottomNavigationVisibility(true)
                    setActionBarProperties(getString(R.string.title_shopping_cart))
                }

                else -> {
                    updateBottomNavigationVisibility(false)
                    supportActionBar?.hide()
                }
            }

        }
    }


    private fun updateBottomNavigationVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.bottomNavigation.visibility = View.VISIBLE
//            binding.bottomNavigation.visible()
        } else {
//            binding.bottomNavigation.setVisibility(View.GONE)
            binding.bottomNavigation.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setActionBarProperties(title: String = "") {
        supportActionBar?.apply {
            this.title = title
            this.show()
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), FLAG)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    companion object {
        private const val FLAG = 0
    }

}