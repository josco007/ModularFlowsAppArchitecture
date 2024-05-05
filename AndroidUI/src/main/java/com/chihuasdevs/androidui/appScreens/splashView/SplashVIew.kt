package com.chihuasdevs.androidui.appScreens.splashView

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chihuasdevs.androidui.base.BaseView

class SplashView(private val viewModel: SplashViewModel): BaseView() {

    var rememberedViewModel: MutableState<SplashViewModel>? = null


    @Composable
    override fun ComposableView() {
        rememberedViewModel = remember { mutableStateOf(viewModel) }

        // Obtener el color aleatorio
        val instanceId = remember { hashCode() }

        Surface(modifier = Modifier.fillMaxSize()) {
            Text("numero "+rememberedViewModel?.value?.test?.value + "isnrance id "+ instanceId)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //RandomColorImage(modifier = Modifier.size(200.dp))
                Text("numero "+rememberedViewModel?.value?.test?.value + "isnrance id "+ instanceId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val viewModel: SplashViewModel = viewModel()
    SplashView(viewModel).ComposableView()
}
