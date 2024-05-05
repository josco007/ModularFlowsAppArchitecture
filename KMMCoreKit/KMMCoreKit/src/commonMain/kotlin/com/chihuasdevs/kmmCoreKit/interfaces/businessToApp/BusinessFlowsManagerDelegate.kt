package com.chihuasdevs.kmmCoreKit.interfaces.businessToApp

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation

interface BusinessFlowsManagerDelegate {
    fun actionForFlow(
        businessFlow: BusinessBaseFlow,
        flow: FlowManagerBase.Flow,
        action: FlowManagerBase.FlowAction,
        animation: UIAnimation?,
        isDynamic: Boolean?,
        dynamicFlowId: String?
    ): Pair<String?, BusinessBaseFlow?>

    fun destroyAllFlowsExcept(businessFlow: BusinessBaseFlow, exceptFlows: List<FlowManagerBase.Flow>)

    fun getBusinessFlow(
        businessFlow: BusinessBaseFlow,
        flow: FlowManagerBase.Flow,
        isDynamic: Boolean?,
        dynamicFlowId: String?
    ): BusinessBaseFlow?

    fun addLogConsole(consoleLog: FlowManagerBase.ConsoleLog)
}