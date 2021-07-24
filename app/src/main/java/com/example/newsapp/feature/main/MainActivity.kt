package com.example.newsapp.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.EmptyViewModel
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.feature.home.HomeFragment
import com.example.newsapp.utils.GlobalConstant
import id.mufid.android.base.BaseFullStateActivity

class MainActivity : BaseFullStateActivity<EmptyViewModel, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(HomeFragment())
    }

    override fun setViewModel() {
        mViewModel = ViewModelProvider(this).get(EmptyViewModel::class.java)
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun getFragmentContainer(): Fragment? = null

    override fun getMainContainer(): View? = null

    private fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.commit()
    }
}