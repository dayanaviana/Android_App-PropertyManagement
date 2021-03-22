package com.android_training.propertymanagement.ui.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.app.MyActions
import com.android_training.propertymanagement.databinding.ActivityAddPropertyBinding
import kotlinx.android.synthetic.main.activity_add_property.*

class AddPropertyActivity : AppCompatActivity() {
    val viewModel by viewModels<PropertyViewModel>()
    lateinit var mBinding: ActivityAddPropertyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)
        mBinding.vm = viewModel
//        mBinding.lifecycleOwner = this
        init()
    }

    private fun init() {
        viewModel.actionObserver.observe(this, Observer {
            when (it) {
                MyActions.SUCCESS -> {
                    Log.d("myApp", "AddPropertyActivity SUCCESS:${viewModel.message}")
                }
                MyActions.FAILURE -> {
                    Log.d("myApp", "AddPropertyActivity FAILURE:${viewModel.message} ")
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }
            }
        })

    }

}
