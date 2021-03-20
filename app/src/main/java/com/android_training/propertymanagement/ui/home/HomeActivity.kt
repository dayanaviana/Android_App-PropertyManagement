package com.android_training.propertymanagement.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.app.MyApplication
import com.android_training.propertymanagement.data.models.Property
import com.android_training.propertymanagement.di.ViewModelFactory
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    val viewModel by viewModels<HomeViewModel>()

//    @Inject lateinit var providerFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        (application as MyApplication).daggerComponent?.inject(this)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        init()
    }

    private fun init() {
        viewModel.getPropertyList()
        viewModel.actionObserver.observe(this, Observer{
            when(it){
                HomeViewModel.AuthAction.STARTED->
                    Log.d("myApp","getList STARTED")
                HomeViewModel.AuthAction.SUCCESS ->{
                    Log.d("myApp","getList SUCCESS")
                    showListOfProperties(viewModel.propertyList)
                }
                HomeViewModel.AuthAction.FAILURE ->{
                    Log.d("myApp","getList FAILURE")
                    Toast.makeText(applicationContext, "Network Error", Toast.LENGTH_LONG).show()
                }
                HomeViewModel.AuthAction.FINISHED ->
                    Log.d("myApp","getList FINISHED")
            }
        })
    }

    private fun showListOfProperties(list: ArrayList<Property>) {
        Log.d("myApp",list.toString())
    }
}