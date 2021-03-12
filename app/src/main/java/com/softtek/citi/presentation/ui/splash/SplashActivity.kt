package com.softtek.citi.presentation.ui.splash

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.softtek.citi.R
import com.softtek.citi.data.services.local.AppPreferences
import com.softtek.citi.isPermissionGranted
import com.softtek.citi.presentation.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT: Long = 2000 // 2 sec

    companion object {
        private const val PERMISSION_CODE = 1000
    }


    private val appPreferences: AppPreferences
        get() = AppPreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        this.hasPermissions()
    }

    private fun hasPermissions() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)

            ) {
                Handler().postDelayed({
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, SPLASH_TIME_OUT)
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    PERMISSION_CODE
                )
            }
        }
    }

    /**
     * Triggered when the user responds to a permissions request.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty()) {
            hasPermissions()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

}