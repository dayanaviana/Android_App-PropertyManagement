package com.android_training.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.data.models.*
import com.android_training.propertymanagement.data.network.AuthApi
import com.android_training.propertymanagement.data.repositories.AuthRepository
import com.android_training.propertymanagement.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setup Bindings
        var binding: com.android_training.propertymanagement.databinding.ActivityRegisterBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        init()
//        register()
//        login()
//        getAllUsers()
    }

    private fun init() {
        viewModel.actionObserver.observe(this, Observer {
            when(it){
                AuthViewModel.AuthAction.STARTED ->{
                    Log.d("myApp", "Registration Started")
                }
                AuthViewModel.AuthAction.FINISHED ->{
                    Log.d("myApp", "Registration Sent")
                }
                AuthViewModel.AuthAction.SUCCESS ->{
                    Toast.makeText(applicationContext, "Registered Successfully", Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
                AuthViewModel.AuthAction.FAILURE -> {
                    Toast.makeText(applicationContext, "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.liveResponse.observe(this, Observer {
            Log.d("myApp","Observer liveResponse changed")
            var message = it
            txt_register_error.text = message
        })
        viewModel.liveCoroutine.observe(this, Observer {
            Log.d("myApp","Observer liveResponse changed")
            var response = it
            Log.d("myApp","${response}")
        })
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edt_reg_password.windowToken, 0)
    }

    fun alreadyRegistered_onClick(view: View){
        navigateToLogin()
    }
    fun navigateToLogin(){
        startActivity(Intent(this, LoginResponse::class.java))
        finish()
    }

    private fun registerTest(){
        var name = "Dayana1"
        var email = "dayana1@gmail.com"
        var password = "password"
        var type = "landlord"
        var user = User(email=email,name = name,password = password,type = "landlord")
        Log.d("myApp", user.toString())
        var authApi = AuthApi.getForRetrofit()
        authApi.postRegister(user).enqueue(object: Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("myApp", t.message ?: "Error")
            }
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                var message = response.message()
                var code = response.code()
                if(response.isSuccessful){
                    var str = response.body()
                    Log.d("myApp", "Successful")
                }else {
                    Log.d("myApp", "Not Successful")
                }
            }
        } )
    }
    private fun loginTest(){
        var email = "dayana@gmail.com"
        var password = "password"
        var user = User(email = email, password = password)
        Log.d("myApp", user.toString())
        var authApi = AuthApi.getForRetrofit()
        authApi.postLogin(user).enqueue(object: Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("myApp", t.message ?: "Error")
            }
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                var message = response.message()
                var code = response.code()
                if(response.isSuccessful){
                    var str = response.body()?.user
                    Log.d("myApp", "Successful")
                }else {
                    Log.d("myApp", "Not Successful")
                }
            }
        } )
    }
    private fun getAllUsersTest(){
        AuthApi.getForRetrofit().getAllUsers().enqueue(object: Callback<UsersResponse>{
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.d("myApp", t.message ?: "Error")
            }

            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if(response.isSuccessful){
                    var count = response.body()?.count
                    var message = response.message()
                    var code = response.code()
                    var usersList = response.body()?.data
                    Log.d("myApp", "Successful")
                }else {
                    Log.d("myApp", "Not Successful")
                }
            }
        })
    }
}