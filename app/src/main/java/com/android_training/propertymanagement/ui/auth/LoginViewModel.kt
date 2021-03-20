package com.android_training.propertymanagement.ui.auth

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android_training.propertymanagement.app.MyApplication
import com.android_training.propertymanagement.app.SessionManager
import com.android_training.propertymanagement.data.models.User
import com.android_training.propertymanagement.data.repositories.UserRepository
import com.android_training.propertymanagement.di.components.DaggerAppComponent
import com.android_training.propertymanagement.di.modules.SessionModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel: ViewModel() {
    @Inject
    lateinit var session: SessionManager

    var email: String? = null
    var password: String? = null
    init {
        Log.d("myApp", "LoginViewModel init")
        DaggerAppComponent.builder().sessionModule(SessionModule()).build().inject(this)
    }
    var actionObserver = MutableLiveData<AuthAction>()
    enum class AuthAction{SUCCESS, FAILURE}
    private fun postActionToView(action: AuthAction){
        actionObserver.postValue(action)
    }
    var responseMessage: String? = null
    fun btnLogin_onClick(view: View){

        if (email.isNullOrEmpty() || password.isNullOrBlank()){
            postActionToView(AuthAction.FAILURE) // Invalid Entry
            responseMessage = "Fields cannot be blank"
        }else {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    var user = User(email = email, password = password)
                    var authResponse = UserRepository().login(user)
//                    session.login(user_response?._id!!,user_response?.name!!)
                    if(authResponse != null && authResponse.error!!){
                        responseMessage = authResponse.message
                        postActionToView(AuthAction.FAILURE)
                    }else {
                        postActionToView(AuthAction.SUCCESS)
                    }
                } catch (e: HttpException){
                    var error = e.message()
                    Log.d("myApp", error)
                    responseMessage = "Network Error"
                    postActionToView(AuthAction.FAILURE)
                }
            }
        }
    }
}