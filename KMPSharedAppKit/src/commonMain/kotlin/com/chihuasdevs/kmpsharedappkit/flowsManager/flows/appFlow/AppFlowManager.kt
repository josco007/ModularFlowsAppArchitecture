package com.chihuasdevs.adroidapp.flowsManager.flows.appFlow

import com.chihuasdevs.cmpsharedui.CMPSharedUI
import com.chihuasdevs.cmpsharedui.uiFlows.appFlow.UIAppFlow
import com.chihuasdevs.kmmCoreKit.base.BaseFlow
import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmflowskit.FlowsManager
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

        CMPSharedUI.setUIAppFLow(uiAppFlow = uiAppFlow)

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