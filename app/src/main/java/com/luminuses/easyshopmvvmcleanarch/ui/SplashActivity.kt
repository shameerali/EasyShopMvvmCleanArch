package com.luminuses.easyshopmvvmcleanarch.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.luminuses.easyshopmvvmcleanarch.R
import com.luminuses.easyshopmvvmcleanarch.common.Constants
import com.luminuses.easyshopmvvmcleanarch.databinding.ActivitySplashBinding
import com.luminuses.easyshopmvvmcleanarch.utils.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var sharedPref: SharedPreferences

    private val isAppFirstTimeOpen: Boolean
        get() = sharedPref.getBoolean(Constants.PREF_IS_APP_FIRST_OPEN, true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (isAppFirstTimeOpen) {
            setupLocalNotification()
            sharedPref.edit().putBoolean(Constants.PREF_IS_APP_FIRST_OPEN, false).apply()
        }

        supportActionBar?.hide()

        lifecycleScope.launch {
            delay(DURATION_MS_DELAY)
            withContext(Dispatchers.Main) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }


    }

    private fun setupLocalNotification() {
        val periodicWorkerRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueue(periodicWorkerRequest)
    }

    companion object {
        private const val TAG = "SplashActivity-LoggingFCMToken"
        private const val DURATION_MS_DELAY = 3000L
    }
}