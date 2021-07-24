package id.mufid.android.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.newsapp.R
import com.example.newsapp.base.BaseActivity
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.utils.GlobalConstant

abstract class BaseFullStateActivity<T : BaseViewModel, B : ViewBinding> :
    BaseActivity<T, B>() {

    override fun getToolbarTitle(): String {
        return if (intent.extras != null && intent.extras!!.containsKey(GlobalConstant.TOOLBAR_TITLE))
            intent.extras!!.getString(GlobalConstant.TOOLBAR_TITLE)!!
        else
            getString(R.string.empty_string)
    }

    override fun getToolbarColor(): Int = R.color.teal_200
    override fun getMainContainer(): View? = null
}