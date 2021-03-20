package com.android_training.propertymanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.ui.auth.LoginActivity
import com.android_training.propertymanagement.ui.auth.RegisterActivity
import com.android_training.propertymanagement.ui.auth.SignupActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun register_onClick(v: View){
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    fun login_onClick(v: View){
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}