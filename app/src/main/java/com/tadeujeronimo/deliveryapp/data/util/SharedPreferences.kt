package com.tadeujeronimo.deliveryapp.data.util

import android.content.Context

object SharedPreferences {
    private const val SHARED_PREFERENCES_NAME = "sharedPreferences"

    var preferences: android.content.SharedPreferences? = null

    fun initSharedPreferences(context: Context) {
        preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    var isAdmin
        get() = preferences?.getBoolean("admin", false)
        set(value) = preferences?.edit()?.putBoolean("admin", value!!)!!.apply()

    var token
        get() = preferences?.getString("token", null)
        set(value) = preferences?.edit()?.putString("token", value!!)!!.apply()

    var userEmail
        get() = preferences?.getString("userEmail", null)
        set(value) = preferences?.edit()?.putString("userEmail", value!!)!!.apply()

    var existCart
        get() = preferences?.getBoolean("existCart", false)
        set(value) = preferences?.edit()?.putBoolean("existCart", value!!)!!.apply()

    var idCart
        get() = preferences?.getString("idCart", "")
        set(value) = preferences?.edit()?.putString("idCart", value!!)!!.apply()
}