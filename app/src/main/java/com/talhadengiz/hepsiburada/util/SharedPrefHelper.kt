package com.talhadengiz.hepsiburada.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper(context: Context) {

    companion object{
        private const val sharedPreference = "sharedPreference"
        private const val onBoarding = "onBoarding"
    }

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(
            sharedPreference,
            Context.MODE_PRIVATE
        )
    }

    fun saveOnboardingSkip() {
        sharedPref.edit().putBoolean(onBoarding, true).apply()
    }

    fun isSkipedOnboarding(): Boolean {
        return sharedPref.getBoolean(onBoarding, false)
    }
}