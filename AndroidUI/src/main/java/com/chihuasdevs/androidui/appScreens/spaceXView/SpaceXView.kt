package com.chihuasdevs.androidui.appScreens.spaceXView


import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch
import coil.compose.rememberImagePainter
import com.chihuasdevs.androidui.base.BaseView

class SpaceXView(private val viewModel: SpaceXViewModel): BaseView() {


    private var rememberedViewModel: MutableState<SpaceXViewModel>? = null


    @Composable
    override fun ComposableView() {
        rememberedViewModel = remember { mutableStateOf(viewModel) }

        val rocketLaunches = rememberedViewModel?.value?.rocketLaunches?.value

        AndroidBackButtonHandler()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            rocketLaunches?.let { launches ->
                items(launches.size) { index ->
                    RocketLaunchItem(rocketLaunch = launches[index])
                }
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun RocketLaunchItem(rocketLaunch: RocketLaunch) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = rocketLaunch.missionName,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                rocketLaunch.links.patch?.small?.let { imageUrl ->
                    Image(
                        painter = rememberImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.CenterHorizontally),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = rocketLaunch.details ?: "",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

    @Composable
    fun AndroidBackButtonHandler() {
        val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current

        DisposableEffect(onBackPressedDispatcher) {
            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    rememberedViewModel?.value?.spaceXUIEventsDelegate?.onBackTapped()
                }
            }
            onBackPressedDispatcher?.onBackPressedDispatcher?.addCallback(callback)
            onDispose {
                callback.remove()
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SpaceXViewPreview() {
    val viewModel: SpaceXViewModel = viewModel()
    SpaceXView(viewModel).ComposableView()
}
