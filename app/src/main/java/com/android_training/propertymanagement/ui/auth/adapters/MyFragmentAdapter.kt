package com.android_training.propertymanagement.ui.auth.adapter

//Add Layout Dependencies
//File > Project Structure > dependency > app > + > Library dependency > search
// design & material

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android_training.propertymanagement.R
import com.android_training.propertymanagement.ui.auth.LandlordFragment
import com.android_training.propertymanagement.ui.auth.TenantFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_signup.*

//Adapter: setup for pager view and tab layout in the activity
class MyFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }

    //Pager view swipe setup
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> LandlordFragment()
            1 -> TenantFragment()
            else -> LandlordFragment()
        }
    }

    //Tab Layout - setup name for each tab
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Landlords"
            1 -> "Tenants"
            else -> ""
        }
    }

    fun setTitleIcons(tab: TabLayout) {
        //Add icons to Tab Layout
        tab.getTabAt(0)!!.setIcon(R.drawable.ic_person)
        tab.getTabAt(1)!!.setIcon(R.drawable.ic_person)
    }
}










