package com.android_training.propertymanagement.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_training.propertymanagement.data.models.*
import com.android_training.propertymanagement.data.network.AuthApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    var liveMessage = MutableLiveData<String>()

    fun login(email: String, password: String):LiveData<String>{
        var user = User(email = email, password = password)
        AuthApi.getForRetrofit().postLogin(user).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                var msg = t.localizedMessage
                liveMessage.value = msg
            }
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                var isSuccessful = response.isSuccessful
                if(!isSuccessful) {
                    var user = response.body()?.user
                    var message = response.message()
                    Log.d("myApp", "LoginResponse: Request Successful")
                    liveMessage.value = message
                }else{
                    var errorBody = response.errorBody()
                    var type = object : TypeToken<LoginResponseError>() {}.type
                    var errorResponse = Gson().fromJson<LoginResponseError>(errorBody!!.charStream(), type)
                    var message = errorResponse.message //email already registered
                    Log.d("myApp", "LoginErrorResponse: $message")
                    liveMessage.value = message
                }
            }
        })
        return liveMessage
    }

    //Retrofit
    fun register(email: String, password: String, name: String, type:String):MutableLiveData<String>{
        var user = User(email = email, password = password, name= name, type = type)
//        Log.d("myApp", user.toString())
        AuthApi.getForRetrofit().postRegister(user).enqueue(object: Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                var msg = t.localizedMessage
                Log.d("myApp", msg ?: "Network Error")
                liveMessage.value = "Network Error"
            }
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                var message = response.message()
                var code = response.code()
                if (response.isSuccessful) {
                    var msg = response.body()?.message //user registered successfully
                    var message = response.message() //Created
                    Log.d("myApp", "RegisterResponse: Request Successful")
                    liveMessage.value = msg
                } else {
                    var errorBody = response.errorBody()
                    var type = object : TypeToken<RegisterErrorResponse>() {}.type
                    var errorResponse = Gson().fromJson<RegisterErrorResponse>(errorBody!!.charStream(), type)
                    var message = errorResponse.message //email already registered
                    Log.d("myApp", "RegisterErrorResponse: $message")
                    liveMessage.postValue(message)
                }
            }
        } )
        return liveMessage
    }

    //RxJava
    fun registerRx(email: String, password: String, name: String, type:String): Single<RegisterResponse> {
        var user = User(email = email, password = password, name= name, type = type)
        return  AuthApi.getForRxJava().postRegisterRx(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(getRegisterObserver() as SingleObserver<RegisterResponse>)
    }

    //Coroutines
    fun registerCo(user: User):MutableLiveData<String>{
        var liveData = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                liveData.value = AuthApi.getForRxJava().postRegisterCo(user).message
            }catch (e: Exception){
                liveMessage.value = "Network error"
            }
        }
        return liveData
    }


    private fun getRegisterObserver(): SingleObserver<RegisterResponse> {
        return object : SingleObserver<RegisterResponse>{
            override fun onSuccess(t: RegisterResponse) {
                liveMessage.value = t.message
            }

            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {
                liveMessage.value = e.localizedMessage
            }

        }
    }
}