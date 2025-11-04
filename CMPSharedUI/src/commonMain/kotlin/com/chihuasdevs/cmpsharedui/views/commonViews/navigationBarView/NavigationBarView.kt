package com.chihuasdevs.cmpsharedui.views.commonViews.navigationBarView

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chihuasdevs.cmpsharedui.base.BaseView

class NavigationBarView(private val viewModel: NavigationBarViewModel): BaseView()  {


    private var rememberedViewModel: MutableState<NavigationBarViewModel>? = null


    @Composable
    override fun ComposableView() {
        rememberedViewModel = remember { mutableStateOf(viewModel) }

        Surface(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        rememberedViewModel?.value?.navigationBarActions?.value?.invoke()?.onBack?.let { it() }
                    },
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                rememberedViewModel?.value?.title?.value?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier.weight(1f) // Hace que el título ocupe todo el espacio disponible
                    )
                }

                IconButton(
                    onClick = {
                        // Acción para abrir el menú contextual
                    },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun NavigationBarViewPreview() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            NavigationBarView(NavigationBarViewModel("Title NavBar", null)).ComposableView()
        }

    }
}

*/