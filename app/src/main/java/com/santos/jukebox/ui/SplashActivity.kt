package com.santos.jukebox.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.santos.jukebox.BuildConfig
import com.santos.jukebox.R
import com.santos.jukebox.client.ClientActivity
import com.santos.jukebox.establishment.ui.activity.EstablishmentActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            if (BuildConfig.FLAVOR == "client") {
                startActivity(Intent(this, ClientActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, EstablishmentActivity::class.java))
                finish()
            }
        }, 1000)
    }

}

