package com.ian.app.ui.activity

import com.ian.app.base.BaseView
import com.ian.app.model.GeneralData

/**
 *
Created by Ian Damping on 07/05/2019.
Github = https://github.com/iandamping
 */
interface MainView : BaseView {
    fun onSuccessGetData(data: GeneralData?)
    fun onSuccessGetHeadlineData(data: GeneralData?)
}