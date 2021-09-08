package com.example.notesappfirebase

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater

class LoadingDialog(private val activity: Activity) {
    private var dialog:AlertDialog? = null

    fun startLoadingDialog(){
        val builder = AlertDialog.Builder(activity)
        val inflater : LayoutInflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_dialog_loading, null))
        builder.setCancelable(true)
        dialog = builder.create()
        dialog?.show()
    }

    fun dismissDialog(){
        dialog!!.dismiss()
    }
}