package com.culture.estet.ui.presentation.map

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.setViewTreeLifecycleOwner
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {
    Log.v("~", "Create")
    val state = viewModel.state.collectAsState()
    MapScreenContent(state.value, viewModel::sendAction)
}

@Composable
private fun MapScreenContent(
    state: MapScreenState,
    sendAction: (MapAction) -> Unit
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    var mapView: MapView? = null

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                MapKitFactory.getInstance().onStart()
                mapView?.onStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                mapView?.onStop()
                MapKitFactory.getInstance().onStop()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context -> MapView(context).apply { mapView = this } },
            update = { view ->
                view.map.move(
                    CameraPosition(Point(55.7524, 37.610778), 10.0f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 0f),
                    null
                )
                view.setViewTreeLifecycleOwner(lifecycleOwner)
            }
        )

        AnimatedVisibility(
            visible = state is MapScreenState.Unavailable,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier

                    .width(IntrinsicSize.Max)
                    .background(Color.Red)
                    .padding(vertical = 2.dp, horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "нет инета", color =  Color.White)
            }

        }

    }
}

@Composable
private fun MapAvailableScreen() {

}

@Composable
private fun MapUnavailableScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        val density = LocalDensity.current
        Text(
            text = "Карта недоступна",
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}