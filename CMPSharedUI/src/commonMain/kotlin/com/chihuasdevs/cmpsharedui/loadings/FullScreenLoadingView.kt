package com.chihuasdevs.cmpsharedui.loadings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class FullScreenLoadingView(private val viewModel: FullScreenLoadingViewModel) {


    private var rememberedViewModel: MutableState<FullScreenLoadingViewModel>? = null

    @Suppress("NotConstructor")
    @Composable
    fun FullScreenLoadingView() {

        rememberedViewModel = remember { mutableStateOf(viewModel) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Loading",
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}