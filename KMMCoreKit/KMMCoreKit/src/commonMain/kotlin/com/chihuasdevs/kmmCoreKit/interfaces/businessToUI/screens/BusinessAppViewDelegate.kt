package com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow

interface BusinessAppViewDelegate {
    //fun showGlobalAlert(businessFlow: BusinessBaseFlow, alertModel: AlertModel): Int
    fun closeOverlayView(businessFlow: BusinessBaseFlow, id: Int?)
    fun showLoading(businessFlow: BusinessBaseFlow, show: Boolean, id: Int?): Int
}