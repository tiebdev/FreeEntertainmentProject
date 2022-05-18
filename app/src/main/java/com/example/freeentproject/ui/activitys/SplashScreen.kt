package com.example.freeentproject.ui.activitys
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.freeentproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SplashScreen)

        val intentLogin = Intent(this, Login::class.java)
        val intentMain = Intent(this, MainActivity::class.java)
        val prefs: SharedPreferences = getSharedPreferences("app", Context.MODE_PRIVATE)

        if ((!TextUtils.isEmpty(prefs.getString("email",""))) &&
            (!TextUtils.isEmpty(prefs.getString("password",""))))
            startActivity(intentMain)
        else
            startActivity(intentLogin)
        finish()
    }
}