package com.chihuasdevs.kmpbusinesskit.flows.homeFlow

import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessHomeDelegate
import com.chihuasdevs.kmmbusinesskit.KMMBusinessKit
import com.chihuasdevs.kmmbusinesskit.flows.homeFlow.HomeBusinessFlow
import com.chihuasdevs.kmpunittestkit.mockBusiness.businessToUI.screens.MockBusinessHomeDelegate
import com.chihuasdevs.kmpunittestkit.mockCoreKit.base.FlowsManager
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class HomeBusinessFlowTest {
    @Test
    fun testOnBackTapped() {
        val homeBusinessFlow = HomeBusinessFlow()
        val mockFlowsManager = FlowsManager()
        val mockBusinessKit = KMMBusinessKit(mockFlowsManager)

        homeBusinessFlow.start(mockBusinessKit, MockBusinessHomeDelegate())
        homeBusinessFlow.onBackTapped()

        // Verifica que el método actionForFlow se haya llamado con los parámetros esperados
        assertEquals(FlowManagerBase.Flow.LOGIN, mockFlowsManager.actionForFlowParam1)
        assertEquals(FlowManagerBase.FlowAction.RESUME, mockFlowsManager.actionForFlowParam2)
        assertEquals(UIAnimation.BACK, mockFlowsManager.actionForFlowParam3)
    }

    @Test
    fun testOnSpaceXRocketLaunchesTapped() {
        val homeBusinessFlow = HomeBusinessFlow()
        val mockFlowsManager = FlowsManager()
        val mockBusinessKit = KMMBusinessKit(mockFlowsManager)

        homeBusinessFlow.start(mockBusinessKit, MockBusinessHomeDelegate())
        homeBusinessFlow.onSpaceXRocketLaunchesTapped()


        assertEquals(FlowManagerBase.Flow.SPACE_X, mockFlowsManager.actionForFlowParam1)
        assertEquals(FlowManagerBase.FlowAction.START, mockFlowsManager.actionForFlowParam2)
        assertEquals(UIAnimation.FORWARD, mockFlowsManager.actionForFlowParam3)
    }




}