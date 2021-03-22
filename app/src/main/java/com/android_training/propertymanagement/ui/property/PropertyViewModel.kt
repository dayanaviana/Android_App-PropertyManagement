package com.android_training.propertymanagement.ui.property

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android_training.propertymanagement.app.MyActions
import com.android_training.propertymanagement.app.MyApplication
import com.android_training.propertymanagement.app.SessionManager
import com.android_training.propertymanagement.app.Utils
import com.android_training.propertymanagement.data.models.Property
import com.android_training.propertymanagement.data.repositories.PropertyRepository
import com.android_training.propertymanagement.di.components.DaggerAppComponent
import com.android_training.propertymanagement.di.modules.SessionModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class PropertyViewModel() : ViewModel() {
    @Inject
    lateinit var userId: String
    val userType: String? = null
    lateinit var property: Property
    var message: String? = null

    var actionObserver = MutableLiveData<MyActions>()
    suspend fun postActionToView(action: MyActions){
        actionObserver.value = action
    }

    init {
        Log.d("myApp", "PropertyViewModel init")
        DaggerAppComponent.builder().sessionModule(SessionModule()).build().inject(this)
        property = Property(userId = userId, userType = "landlord")
//        addProperty()
    }

    fun btnSaveNext_onClick(view: View){addProperty()}

    private fun addProperty(){
        property.address = "5800 S Redwood Rd"
        property.city = "Taylorsville"
        property.state = "UT"
        property.country = "US"
        property.zipcode = "84123"
        setImage()
        var searchString = property?.getFullAddress()
        var latLong = GeocoderHelper().getLatLongFromAddress("$searchString")
        if(latLong!=null) property.setLatLong(latLong)
        Log.d("myApp", "PropertyViewModel city=${property.toString()}")

        postProperty()
    }

    fun addPhoto_onClick(view: View){
        setImage()
        Toast.makeText(MyApplication.appContext,"Image Added",Toast.LENGTH_SHORT).show()
    }
    private fun setImage(){
        property.image = "https://thumbor.forbes.com/thumbor/fit-in/1200x0/filters%3Aformat%28jpg%29/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F1026205392%2F0x0.jpg"
    }
    private fun postProperty(){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var response = PropertyRepository().postProperty(property)
                message = response.message
                postActionToView(MyActions.SUCCESS)
            }catch (e: HttpException){
                message = Utils.getErrorMessage(e)
                postActionToView(MyActions.FAILURE)
            }
        }
    }


}
