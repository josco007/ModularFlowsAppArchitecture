package com.chihuasdevs.kmmCoreKit.base

import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToApp.BusinessFlowsManagerDelegate
import com.benasher44.uuid.uuid4

open class FlowManagerBase : BusinessFlowsManagerDelegate {

    enum class Flow {
        APP, DETAIL, HOME, LOGIN, MY_ACCOUNT, REGISTER, SPLASH, TAB_BAR, SPACE_X
    }

    enum class FlowAction {
        DESTROY, RESUME, START
    }

    data class ConsoleLog(val type: ConsoleLogType, val message: String) {
        enum class ConsoleLogType {
            ERROR, WARNING
        }
    }

    data class DynamicFlow(val id: String, val flow: Flow, val flowObj: BaseFlow)

    private val flows: MutableMap<Flow, BaseFlow?> = mutableMapOf()
    private val dynamicFlows: MutableList<DynamicFlow> = mutableListOf()
    private val consoleLogs: MutableList<ConsoleLog> = mutableListOf()

    init {
//        #if DEBUG
//        startConsoleLogging()
//        #endif
    }

    open fun actionForFlow(flow: Flow, action: FlowAction, animation: UIAnimation? = null, isDynamic: Boolean? = false, dynamicFlowId: String? = null): Pair<String?, BusinessBaseFlow?> {
        val executeFlowResult = executeFlow(flow, action, animation, isDynamic, dynamicFlowId)
        return Pair(executeFlowResult.first, executeFlowResult.second?.getBusinessBaseFlow())
    }

    private fun destroyFlow(flow: Flow, isDynamic: Boolean?, dynamicFlowId: String?) {
        if (isDynamic == true) {
            dynamicFlows.removeAll { it.id == dynamicFlowId }
        } else {
            flows[flow] = null
        }
    }

    private fun resumeFlow(flow: Flow, isDynamic: Boolean?, dynamicFlowId: String?, animation: UIAnimation?): Pair<String?, BaseFlow?> {
        if (isDynamic == true) {
            dynamicFlows.firstOrNull { it.id == dynamicFlowId }?.flowObj?.resume(animation)
        } else {
            if (flows[flow] is BaseFlow) {
                flows[flow]?.resume(animation)
            } else {
                return startFlow(flow, getNewFlowObjFor(flow, animation), isDynamic)
            }
        }
        return Pair(null, null)
    }

    private fun startFlow(flow: Flow, flowObj: BaseFlow, isDynamic: Boolean?, animation: UIAnimation? = null): Pair<String?, BaseFlow?> {
        if (isDynamic == true) {
            val dynamicFlowId = uuid4().toString()
            val dynamicFlow = DynamicFlow(dynamicFlowId, flow, flowObj)
            dynamicFlows.add(dynamicFlow)
            return Pair(dynamicFlowId, flowObj)
        } else {
            flows[flow] = flowObj
        }
        return Pair(null, null)
    }

    private fun executeFlow(flow: Flow, action: FlowAction, animation: UIAnimation?, isDynamic: Boolean?, dynamicFlowId: String?): Pair<String?, BaseFlow?> {
        when (action) {
            FlowAction.DESTROY -> destroyFlow(flow, isDynamic, dynamicFlowId)
            FlowAction.RESUME -> return resumeFlow(flow, isDynamic, dynamicFlowId, animation)
            FlowAction.START -> return startFlow(flow, getNewFlowObjFor(flow, animation), isDynamic)
        }
        return Pair(null, null)
    }

    open fun getNewFlowObjFor(flow: Flow, animation: UIAnimation?): BaseFlow {
        return BaseFlow()
    }

//    #if DEBUG
//    private fun startConsoleLogging() {
//        fun printActiveFlows() {
//            println("=======ACTIVE FLOWS=======")
//            println("___________________________")
//            println("|          FLOWS          |")
//            println("|Flow Name |              |")
//            println("|-------------------------|")
//            println(if (flows.isEmpty()) "No flows found" else "|                         |")
//            flows.keys.forEach { println("| 🌀 $it|") }
//            println("|_________________________|")
//            println("|       DYNAMIC FLOWS     |")
//            println("|Flow Name | Flow Id      |")
//            println("---------------------------")
//            println(if (dynamicFlows.isEmpty()) "No Dynamic flows found" else "|                         |")
//            dynamicFlows.forEach { println("| 🌀 ${it.flow} | ${it.id}|") }
//            println("|_________________________|")
//            println("|       ERRORS            |")
//            println("|Error message |          |")
//            println("---------------------------")
//            val errors = consoleLogs.filter { it.type == ConsoleLog.ConsoleLogType.ERROR }
//            println(if (errors.isEmpty()) "No errors found" else "|                         |")
//            errors.forEach { println("| ❌ ${it.message}|") }
//            println("|_________________________|")
//            println("|       WARNINGS          |")
//            println("|Warning message |        |")
//            println("---------------------------")
//            val warnings = consoleLogs.filter { it.type == ConsoleLog.ConsoleLogType.WARNING }
//            println(if (warnings.isEmpty()) "No warnings found" else "|                         |")
//            warnings.forEach { println("| ⚠️ ${it.message}|") }
//            println("==========================")
//        }
//        Timer().schedule(object : TimerTask() {
//            override fun run() {
//                printActiveFlows()
//            }
//        }, 0, 5000)
//    }
//    #endif

    fun getFlow(flow: Flow, isDynamic: Boolean?, dynamicFlowId: String?): BaseFlow? {
        if (isDynamic ?: false) {
            return dynamicFlows.firstOrNull { it.id == dynamicFlowId && it.flow == flow }?.flowObj
        }
        return flows[flow]
    }

    fun destroyFlowsExcept(exceptFlows: List<Flow>) {
        flows.keys.filter { !exceptFlows.contains(it) }.forEach { flows[it] = null }
    }

    //MARK: BusinessFlowsManagerDelegate

    override fun destroyAllFlowsExcept(businessFlow: BusinessBaseFlow, exceptFlows: List<FlowManagerBase.Flow>) {
        destroyFlowsExcept(exceptFlows)
    }

    override fun actionForFlow(businessFlow: BusinessBaseFlow, flow: FlowManagerBase.Flow, action: FlowManagerBase.FlowAction, animation: UIAnimation?, isDynamic: Boolean?, dynamicFlowId: String?): Pair<String?, BusinessBaseFlow?> {
        return actionForFlow(flow, action, animation, isDynamic, dynamicFlowId)
    }

    override fun getBusinessFlow(businessFlow: BusinessBaseFlow, flow: FlowManagerBase.Flow, isDynamic: Boolean?, dynamicFlowId: String?): BusinessBaseFlow? {
        return getFlow(flow, isDynamic, dynamicFlowId)?.getBusinessBaseFlow()
    }

    override fun addLogConsole(consoleLog: FlowManagerBase.ConsoleLog) {
//        #if DEBUG
//        consoleLogs.add(consoleLog)
//        #endif
    }
}
