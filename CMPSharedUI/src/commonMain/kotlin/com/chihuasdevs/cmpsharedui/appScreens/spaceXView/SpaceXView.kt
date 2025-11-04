package com.chihuasdevs.cmpsharedui.appScreens.spaceXView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.painter.Painter
import com.chihuasdevs.cmpsharedui.base.BaseView
import com.chihuasdevs.cmpsharedui.utils.BackHandler
import com.chihuasdevs.kmmCoreKit.models.webServices.spacexWS.RocketLaunch

// Importa la utilidad multiplataforma para cargar imágenes
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class SpaceXView(private val viewModel: SpaceXViewModel) : BaseView() {

    private var rememberedViewModel: MutableState<SpaceXViewModel>? = null

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun ComposableView() {
        rememberedViewModel = remember { mutableStateOf(viewModel) }
        val rocketLaunches = rememberedViewModel?.value?.rocketLaunches?.value ?: emptyList()

        // ✅ En multiplataforma, no usamos OnBackPressedDispatcher
        // En su lugar, puedes manejar el back manualmente con un callback opcional
        BackHandler { rememberedViewModel?.value?.spaceXUIEventsDelegate?.onBackTapped() }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(rocketLaunches.size) { index ->
                RocketLaunchItem(rocketLaunch = rocketLaunches[index])
            }
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun RocketLaunchItem(rocketLaunch: RocketLaunch) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = rocketLaunch.missionName,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                rocketLaunch.links.patch?.small?.let { imageUrl ->
                    // ✅ Usa Kamel para cargar imágenes remotas multiplataforma
                    KamelImage(
                        resource = asyncPainterResource(imageUrl),
                        contentDescription = rocketLaunch.missionName,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = rocketLaunch.details ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}




/*
@Preview(showBackground = true)
@Composable
fun SpaceXViewPreview() {
    val viewModel: SpaceXViewModel = viewModel()
    SpaceXView(viewModel).ComposableView()
}


 */