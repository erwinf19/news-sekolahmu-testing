package com.example.newsapp.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.newsapp.R
import com.example.newsapp.utils.PreferenceHelper
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : BaseViewModel, V : ViewBinding> : Fragment() {

//    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mViewModel: T
    lateinit var pref : PreferenceHelper
    private var _mViewDataBinding: V? = null
    val mViewDataBinding get() = _mViewDataBinding!!

    abstract fun setViewModel()

    /**
     *  Initialisasi Data Binding
     *  DataBindingUtil.inflate(inflater, R.layout.fragment_my_program, container, false)
     *
     *  Initialisasi View Binding
     *  FragmentMyProgramBinding.inflate(inflater, container, false)
     */
    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): V
    abstract fun getMainContainer(): View?


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mViewDataBinding = getViewBinding(inflater, container)
        return mViewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        _mViewDataBinding = null
        super.onDestroyView()
    }

    fun startNavigationTo(activityTarget: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, activityTarget)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startNavigationTo(context: Context, activityTarget: Class<*>, bundle: Bundle) {
        val intent = Intent(context, activityTarget)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startNavigationNewTaskTo(activityTarget: Class<*>, bundle: Bundle) {
        val intent = Intent(activity, activityTarget)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startNavigationResultTo(activityTarget: Class<*>, bundle: Bundle, resultCode: Int) {
        val intent = Intent(activity, activityTarget)
        intent.putExtras(bundle)
        startActivityForResult(intent, resultCode)
    }

    fun finishActivityWithResult(activityFragment: Activity, bundle: Bundle, resultCode: Int) {
        val intent = Intent()
        intent.putExtras(bundle)
        activityFragment.setResult(resultCode, intent)
        activityFragment.finish()
    }

    fun goToFragment(fm : FragmentManager, fragment : Fragment, bundle : Bundle?=null, tag : String?){
        val fragmentTransaction : FragmentTransaction = fm.beginTransaction()
        if(bundle != null){
            fragment.arguments = bundle
        }
        if(fragment.isAdded)
            return
        fragmentTransaction.replace(R.id.frame_tab, fragment, tag)
        fragmentTransaction.commit()
//        if(fm.findFragmentByTag(tag) == null && tag != null)
        fragmentTransaction.addToBackStack(tag)
    }
}