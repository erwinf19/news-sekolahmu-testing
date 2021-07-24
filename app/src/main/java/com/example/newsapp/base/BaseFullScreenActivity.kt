package id.mufid.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.base.EmptyViewModel
import com.example.newsapp.databinding.ActivityBaseBinding
import com.example.newsapp.utils.GlobalConstant

abstract class BaseFullScreenActivity : BaseActivity<EmptyViewModel, ActivityBaseBinding>() {

    override fun getToolbarTitle(): String {
        return if (intent.extras != null && intent.extras!!.containsKey(GlobalConstant.TOOLBAR_TITLE))
            intent.extras!!.getString(GlobalConstant.TOOLBAR_TITLE)!!
        else
            getString(R.string.empty_string)
    }

    override fun getToolbarColor(): Int {
        return if (intent.extras != null && intent.extras!!.containsKey(GlobalConstant.TOOLBAR_BACKGROUND_COLOR))
            intent.extras!!.getInt(GlobalConstant.TOOLBAR_BACKGROUND_COLOR)!!
        else
            android.R.color.white
    }

    override fun setViewModel() {
        mViewModel = ViewModelProvider(this).get(EmptyViewModel::class.java)
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityBaseBinding {
        return ActivityBaseBinding.inflate(inflater)
    }

    override fun getMainContainer(): View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(
            R.id.container_fragment,
            getFragmentContainer()!!,
            getFragmentContainer()!!::class.java.simpleName
        )
    }
}