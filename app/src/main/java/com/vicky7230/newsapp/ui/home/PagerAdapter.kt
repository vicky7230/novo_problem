package com.vicky7230.newsapp.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vicky7230.newsapp.data.network.Source
import com.vicky7230.newsapp.ui.news.NewsFragment

class PagerAdapter(
    private val sources: MutableList<Source>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int {
        return sources.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = NewsFragment()
        fragment.arguments = Bundle().apply {
            putString(NewsFragment.SOURCE_ID, sources[position].id)
            putString(NewsFragment.SOURCE_NAME, sources[position].name)
        }
        fragments.add(fragment)
        return fragment
    }

}
