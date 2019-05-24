package com.ian.app.ui.activity

import com.ian.app.base.BasePresenter
import com.ian.app.helper.logE
import com.ian.app.repository.NewsRepository

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
class MainPresenter : BasePresenter<MainView>() {

    override fun onCreate() {
        NewsRepository(getLifeCycleOwner()).getNews({
            view()?.onSuccessGetData(it)
            view()?.onSuccessGetHeadlineData(it)
        }, {
            logE(" error happen ")
        })
        view()?.initView()
    }

}