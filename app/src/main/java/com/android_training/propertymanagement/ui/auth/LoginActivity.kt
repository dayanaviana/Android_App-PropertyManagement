package com.android_training.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var mAuthBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        mAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        mAuthBinding.vm = viewModel

        viewModel.actionObserver.observe(this, Observer {
            when(it){
                AuthViewModel.AuthAction.STARTED ->{
                    Log.d("myApp", "Login Started")
                }
                AuthViewModel.AuthAction.FINISHED ->{
                    Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                }
                AuthViewModel.AuthAction.FAILURE -> {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun newUser_onClick(view: View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }
}