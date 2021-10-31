package com.talhadengiz.searhmedia.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class OnboardingViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){
    private val arrayList = ArrayList<Fragment>()

    override fun createFragment(position: Int): Fragment {
        return arrayList[position]
    }

    fun getItem(position: Int): Fragment {
        return arrayList[position]
    }

    fun addFragment(fragment: Fragment) {
        arrayList.add(fragment)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}