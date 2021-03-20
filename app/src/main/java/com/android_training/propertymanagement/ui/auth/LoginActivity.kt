package com.android_training.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.databinding.ActivityLoginBinding
import com.android_training.propertymanagement.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val viewModel by viewModels<LoginViewModel>()
    lateinit var mAuthBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mAuthBinding.vm = viewModel

        viewModel.actionObserver.observe(this, Observer {
            when(it) {
                LoginViewModel.AuthAction.SUCCESS -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                LoginViewModel.AuthAction.FAILURE -> {
                    txt_login_error.text = viewModel.responseMessage
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun newUser_onClick(view: View){
        startActivity(Intent(this,SignupActivity::class.java))
    }
}