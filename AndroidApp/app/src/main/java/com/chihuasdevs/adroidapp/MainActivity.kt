package com.chihuasdevs.adroidapp

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.chihuasdevs.adroidapp.flowsManager.FlowsManager
import com.chihuasdevs.adroidapp.flowsManager.flows.appFlow.AppFlowManager
import com.chihuasdevs.kmmCoreKit.base.FlowManagerBase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            AdroidAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
            SplashView()

        }
    }

}

@Composable
fun SplashView() {
    var flowsManager = FlowsManager(LocalContext.current)
    flowsManager.actionForFlow(FlowManagerBase.Flow.APP, FlowManagerBase.FlowAction.START)
    (flowsManager.getFlow(FlowManagerBase.Flow.APP, false, null) as? AppFlowManager)?.
    getUIAppFlow()?.
    getAppView()?.
    AppView()

    LaunchedEffect(Unit) {
        flowsManager.actionForFlow(FlowManagerBase.Flow.SPLASH, FlowManagerBase.FlowAction.START)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    var flowsManager = FlowsManager(LocalContext.current)
    flowsManager.actionForFlow(FlowManagerBase.Flow.APP, FlowManagerBase.FlowAction.START)
    (flowsManager.getFlow(FlowManagerBase.Flow.APP, false, null) as? AppFlowManager)?.
    getUIAppFlow()?.
    getAppView()?.
    AppView()

    LaunchedEffect(Unit) {
        flowsManager.actionForFlow(FlowManagerBase.Flow.SPLASH, FlowManagerBase.FlowAction.START)
    }
}