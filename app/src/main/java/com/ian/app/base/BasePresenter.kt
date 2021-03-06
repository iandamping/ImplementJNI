package com.ian.app.base

import android.app.ProgressDialog
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.ian.app.helper.Constant.dialogMessage
import com.ian.app.helper.Constant.dialogTittle

/**
 *
Created by Ian Damping on 06/05/2019.
Github = https://github.com/iandamping
 */

abstract class BasePresenter<View> : ViewModel(), LifecycleObserver, BasePresenterHelper {
    private var view: View? = null
    private var viewLifecycle: Lifecycle? = null
    private lateinit var lifecycleOwner: FragmentActivity
    private lateinit var dialog: ProgressDialog


    fun attachView(view: View, lifeCycleOwner: FragmentActivity) {
        this.view = view
        this.viewLifecycle = lifeCycleOwner.lifecycle
        this.lifecycleOwner = lifeCycleOwner
        setBaseDialog(lifecycleOwner)
        viewLifecycle?.addObserver(this)
    }

    protected fun view(): View? {
        return view
    }

    protected fun getLifeCycleOwner(): FragmentActivity {
        return lifecycleOwner
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }

    private fun setBaseDialog(ctx: Context) {
        dialog = ProgressDialog(ctx)
        dialog.setTitle(dialogTittle)
        dialog.setMessage(dialogMessage)
        dialog.isIndeterminate = true
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
    }

    protected fun setDialogShow(status: Boolean) {
        if (status) {
            dialog.dismiss()
        } else {
            dialog.show()
        }
    }
}