package com.tadeujeronimo.deliveryapp

import android.app.Application
import com.tadeujeronimo.deliveryapp.data.util.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeliveryApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		SharedPreferences.initSharedPreferences(this)
	}
}