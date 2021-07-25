package com.example.newsapp.feature.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.base.EmptyViewModel
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.feature.news.NewsDetailFragment
import com.example.newsapp.feature.news.NewsFragment
import id.mufid.android.base.BaseFullStateActivity

class MainActivity : BaseFullStateActivity<EmptyViewModel, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mViewDataBinding.toolbar)
        openFragment(NewsFragment())
    }

    override fun setViewModel() {
        mViewModel = ViewModelProvider(this).get(EmptyViewModel::class.java)
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun getFragmentContainer(): Fragment? = null

    override fun getMainContainer(): View? = null

    fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_tab, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        Log.d("on backpress", "On backpress = " + fm.backStackEntryCount)
        if (fm.backStackEntryCount>1) {
            fm.popBackStack()
        }else{
            finish()
        }
    }
}