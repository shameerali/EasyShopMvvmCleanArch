package com.luminuses.easyshopmvvmcleanarch

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class EasyShopApp:Application() {


    @Inject
    lateinit var sharedPrefs: SharedPreferences
}