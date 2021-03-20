package com.android_training.propertymanagement.ui.auth

import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.*
import com.android_training.propertymanagement.data.models.RegisterResponse
import com.android_training.propertymanagement.data.repositories.AuthRepository
import com.android_training.propertymanagement.data.models.User
import com.android_training.propertymanagement.data.repositories.UserRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    lateinit var disposable: Disposable
    var liveResponse = MutableLiveData<String>()
    var liveCoroutine= MutableLiveData<String>()

    var email: String? = null
    var password: String? = null
    var name: String? = null
    var landlordEmail: String? = null
    private var type: String? = "landlord"

    var actionObserver = MutableLiveData<AuthAction>()
    enum class AuthAction{STARTED, FINISHED, SUCCESS, FAILURE}
    private fun postActionToView(action: AuthAction){
        actionObserver.value = action
    }



    fun btnRegister_onClick(view: View){
        postActionToView(AuthAction.STARTED)

        if (email.isNullOrEmpty() || password.isNullOrBlank()|| name.isNullOrBlank()){
            postActionToView(AuthAction.FAILURE)
            liveResponse.value = "Invalid entry, must complete form."
        }else{
//            liveResponse = AuthRepository().register(email!!, password!!,name!!,type!!)

//            var repo = AuthRepository().registerRx(email!!,password!!,name!!,type!!)
//            repo.subscribeWith(MyObserver())

            var user = User(email = email, password = password, name= name, type = type)
            liveResponse = AuthRepository().registerCo(user)

            postActionToView(AuthAction.FINISHED)
        }
    }

    fun radioButton_onCLick(view: View){
//        var radioButton: RadioButton = view.findViewById((view.id))
        var radioButton = view as RadioButton
        type = radioButton?.text.toString().decapitalize()
//        Log.d("myApp", type!!)
    }

    inner class MyObserver(): SingleObserver<RegisterResponse>{
        override fun onSuccess(t: RegisterResponse) {
            AuthViewModel().postActionToView(AuthAction.SUCCESS)
            liveResponse.value = t.message
        }
        override fun onSubscribe(d: Disposable) {disposable = d}

        override fun onError(e: Throwable) {
            postActionToView(AuthAction.FAILURE)
            Log.d("myApp",e.localizedMessage)
        }
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            UserRepository().logout()
//            logoutLiveData.postValue(true)
        }
    }
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}