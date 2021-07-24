package com.example.newsapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.base.EmptyViewModel
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.databinding.FragmentSplashScreenBinding
import com.example.newsapp.feature.main.MainActivity
import java.util.*
import kotlin.concurrent.schedule

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun setViewModel() {
        mViewModel =
            ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun getMainContainer(): View? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timer().schedule(5000) {
            startNavigationTo(requireContext(), MainActivity::class.java, requireArguments())
            requireActivity().finish()
        }
    }
}