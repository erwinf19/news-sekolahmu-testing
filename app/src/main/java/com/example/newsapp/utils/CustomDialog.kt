package com.example.newsapp.utils

import android.app.Activity
import android.app.AlertDialog
import com.example.newsapp.R

class CustomDialog(val activity : Activity) {
    private lateinit var progressBar : AlertDialog

    fun init(){
        val dialogView = activity.layoutInflater.inflate(R.layout.progress_dialog, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        progressBar = builder.create()
    }

    fun startLoading(){
        progressBar.show()
    }

    fun stopLoading(){
        if(progressBar.isShowing){
            progressBar.dismiss()
        }
    }
}