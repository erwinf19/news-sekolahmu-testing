package com.example.newsapp.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.newsapp.R
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel, V : ViewBinding> : AppCompatActivity() {

    lateinit var mViewModel: T

//    public var mViewModelFactory: ViewModelProvider.Factory = ViewModelProvider()

    private var _mViewDataBinding: V? = null
    val mViewDataBinding get() = _mViewDataBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mViewDataBinding = getViewBinding(layoutInflater)
        setViewModel()
        setContentView(mViewDataBinding.root)
    }


    /**
     * Initialize ViewModel and Add Into mViewModel
     */
    abstract fun setViewModel()

    /**
     *Handle main view in activity / fragment
     */
    abstract fun getViewBinding(inflater: LayoutInflater): V

    /**
     * Initialize main container and call in front activity
     */
    abstract fun getFragmentContainer(): Fragment?

    /**
     * Initialize view state container for handle VISIBLE || GONE
     */
    abstract fun getMainContainer(): View?

    /**
     * Set toolbar title
     */
    abstract fun getToolbarTitle(): String

    /**
     * Custom toolbar background color if needed
     */
    @ColorRes
    abstract fun getToolbarColor(): Int

    companion object {
        fun newInstanceFragment(bundle: Bundle?, fragment: Fragment): Fragment {
            fragment.arguments = bundle ?: Bundle()
            return fragment
        }
    }

    fun startNavigationTo(activityTarget: Class<*>, bundle: Bundle) {
        val intent = Intent(this, activityTarget)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun startNavigationTo(context: Context, activityTarget: Class<*>, bundle: Bundle) {
        val intent = Intent(context, activityTarget)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment, tag: String) {
        supportFragmentManager.inTransaction {
            replace(
                frameId,
                fragment,
                tag
            ).disallowAddToBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mViewDataBinding = null
    }
}