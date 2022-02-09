package com.example.autentdenis.Sharedpref

import android.content.Context
import android.content.SharedPreferences

class Prefes (context: Context) {
    val PREFS_NAME = "com.cursokotlin.sharedpreferences"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    var name: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()

    var email: String?
        get() = prefs.getString("shared_email", "")
        set(value) = prefs.edit().putString("shared_email", value).apply()

    var perfil: String?
        get() = prefs.getString("shared_perfil", "")
        set(value) = prefs.edit().putString("shared_perfil", value).apply()
}