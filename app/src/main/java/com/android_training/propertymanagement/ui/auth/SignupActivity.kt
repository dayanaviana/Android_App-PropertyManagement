package com.android_training.propertymanagement.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.ui.auth.adapter.MyFragmentAdapter
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        init()
    }
    private fun init(){
        var myFragmentAdapter = MyFragmentAdapter(supportFragmentManager)
        view_pager.adapter = myFragmentAdapter

        tab_layout.setupWithViewPager(view_pager)

        //Handle icons setup on adapter
        myFragmentAdapter.setTitleIcons(tab_layout)
    }
}