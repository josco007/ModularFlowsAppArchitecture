package com.chihuasdevs.kmpunittestkit.mockCoreKit.base

import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation

class FlowsManager() : FlowManagerBase() {

    var actionForFlowParam1: Flow? = null
    var actionForFlowParam2: FlowAction? = null
    var actionForFlowParam3: UIAnimation? = null

    override fun actionForFlow(
        flow: Flow,
        action: FlowAction,
        animation: UIAnimation?,
        isDynamic: Boolean?,
        dynamicFlowId: String?
    ): Pair<String?, BusinessBaseFlow?> {

        actionForFlowParam1 = flow
        actionForFlowParam2 = action
        actionForFlowParam3 = animation

        return super.actionForFlow(flow, action, animation, isDynamic, dynamicFlowId)
    }




}