package com.tranzkargo.mobile.dagger.module

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper
@Inject constructor(
        private val sharedPreferences: SharedPreferences
) {
    private val PREF_KEY_ACCESS_TOKEN = "token"


    fun getAccessTokenFromPreference(): String? {
        var pref = sharedPreferences.getString(PREF_KEY_ACCESS_TOKEN, null)
        return  pref
    }

}