package com.vicky7230.newsapp.ui.home

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.vicky7230.newsapp.data.network.Source
import com.vicky7230.newsapp.ui.news.NewsFragment

class PagerAdapter(
    private val sources: MutableList<Source>,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        val fragment = NewsFragment()
        fragment.arguments = Bundle().apply {
            putString(NewsFragment.SOURCE_ID, sources[position].id)
            putString(NewsFragment.SOURCE_NAME, sources[position].name)
        }
        return fragment
    }


    override fun getCount(): Int {
        return sources.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return sources[position].name
    }
}
