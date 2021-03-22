package com.android_training.propertymanagement.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android_training.propertymanagement.data.models.Property
import com.android_training.propertymanagement.data.network.PropertyApi
import com.android_training.propertymanagement.data.repositories.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(): ViewModel() {

    init {
        Log.d("myApp", "HomeViewModel init")
    }
    var propertyList = ArrayList<Property>()

    var actionObserver = MutableLiveData<AuthAction>()
    enum class AuthAction{STARTED, FINISHED, SUCCESS, FAILURE}
    suspend fun postActionToView(action: AuthAction){
        actionObserver.value = action
    }

    fun getPropertyList(){
        viewModelScope.launch(Dispatchers.Main) {
            postActionToView(AuthAction.STARTED)
            try {
                val response = PropertyRepository().getPropertyList()
                propertyList = response.properties
                postActionToView(AuthAction.SUCCESS)
            }catch (e: Exception){
                var msg = e.localizedMessage
                Log.d("myApp","HomeViewModel: Error $msg")
                postActionToView(AuthAction.FAILURE)
            }
            finally {
                postActionToView(AuthAction.FINISHED)
            }
        }
    }
}