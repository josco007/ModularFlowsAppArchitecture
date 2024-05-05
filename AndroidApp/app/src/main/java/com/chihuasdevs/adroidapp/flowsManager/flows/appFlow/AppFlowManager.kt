package com.chihuasdevs.adroidapp.flowsManager.flows.appFlow

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.chihuasdevs.adroidapp.flowsManager.FlowsManager
import com.chihuasdevs.androidui.AndroidUI
import com.chihuasdevs.androidui.uiFlows.appFlow.UIAppFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import kotlinx.coroutines.launch

class AppFlowManager(flowsManager: FlowsManager?) : BaseFlow() {


    private var uiAppFlow: UIAppFlow? = null
    //private var businessAppFlow: MockAppFlow? = null

    init {
        // Crear la interfaz de usuario para el flujo de la aplicación
        uiAppFlow = UIAppFlow()

        // Crear el flujo empresarial para el flujo de la aplicación
        //businessAppFlow = MockAppFlow(flowsManager)

        // Iniciar el flujo de la interfaz de usuario con el flujo empresarial de la aplicación
        //uiAppFlow?.start(appUIEventsDelegate = businessAppFlow)


        uiAppFlow?.start(appUIEventsDelegate = null)

        AndroidUI.setUIAppFLow(uiAppFlow = uiAppFlow)

        // Iniciar el flujo empresarial de la aplicación con la pantalla de la interfaz de usuario de la aplicación
        //super.init()
        //businessAppFlow?.start(businessAppViewDelegate = uiAppFlow?.getBusinessAppViewDelegate())
    }

    fun getUIAppFlow(): UIAppFlow? {
        return uiAppFlow
    }

//    override fun getBusinessBaseFlow(): BusinessBaseFlow? {
//        return businessAppFlow
//    }

}