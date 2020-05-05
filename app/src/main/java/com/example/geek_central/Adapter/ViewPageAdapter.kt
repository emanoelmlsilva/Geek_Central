package com.example.geek_central.Adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

@SuppressLint("WrongConstant")
class ViewPageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    val listFragment : MutableList<Fragment> = ArrayList()
    val listTitle : MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return listFragment.get(position)
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }

    fun addFragment(fragment: Fragment,title: String){
        listFragment.add(fragment)
        listTitle.add(title)
    }

}