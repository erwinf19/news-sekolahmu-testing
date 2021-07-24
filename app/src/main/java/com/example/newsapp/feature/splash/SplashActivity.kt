package com.example.newsapp.feature.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.base.EmptyViewModel
import com.example.newsapp.databinding.ActivityBaseBinding
import com.example.newsapp.utils.GlobalConstant
import id.mufid.android.base.BaseFullScreenActivity

class SplashActivity : BaseFullScreenActivity() {

    override fun getFragmentContainer(): Fragment {
        val bundle = intent.extras
        return newInstanceFragment(bundle, SplashFragment())
    }

}