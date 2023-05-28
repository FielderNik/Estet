package com.culture.estet.ui.presentation.map

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.setViewTreeLifecycleOwner
import com.culture.estet.MainActivity
import com.culture.estet.R
import com.culture.estet.core.eventbus.BottomSheetEventBus
import com.culture.estet.data.map.School
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.localcomposition.LocalBottomSheetEventBus
import com.culture.estet.ui.theme.Grey
import com.culture.estet.ui.theme.Purple40
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
) {

    val appTopBarState = LocalAppTopBarState.current
    val title = stringResource(id = R.string.title_screen_map)

    LaunchedEffect(Unit) {
        appTopBarState.title = title
    }

    val state = viewModel.state.collectAsState()
    MapScreenContent(state.value, viewModel::sendAction)
}

@Composable
private fun MapScreenContent(
    state: MapScreenState,
    sendAction: (MapAction) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val bottomSheetEventBus = LocalBottomSheetEventBus.current
    val activity = LocalContext.current as MainActivity

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                MapKitFactory.getInstance().onStart()
                activity.mapView?.onStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                activity.mapView?.onStop()
                MapKitFactory.getInstance().onStop()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            activity.mapView = null
            activity.mapObjects = null
            activity.pointListener.clear()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val view = MapView(context).apply {
                    activity.mapView = this
                }
                view.map.move(
                    CameraPosition(Point(55.7524, 37.610778), 10.0f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 0f),
                    null
                )
                activity.mapObjects = view.map.mapObjects.addCollection()
                val imageProvider = ImageProvider.fromResource(context, R.drawable.school_point)
                if (state is MapScreenState.Available) {
                    setPoints(
                        context,
                        view.map.mapObjects.addCollection(),
                        imageProvider,
                        state.schools,
                        bottomSheetEventBus,
                        activity.pointListener
                    )
                }
                view.setViewTreeLifecycleOwner(lifecycleOwner)
                view
            }
        )

        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            PlusButton()

            Spacer(modifier = Modifier.height(8.dp))

            MinusButton()
        }

        AnimatedVisibility(
            visible = when (state) {
                is MapScreenState.Available -> false
                is MapScreenState.Unavailable -> true
            },
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(Color.Red),
                contentAlignment = Alignment.Center,
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = LocalContext.current.getString(R.string.bad_internet_title),
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 4.dp, start = 2.dp, end = 2.dp)
                    )
                    Text(
                        text = LocalContext.current.getString(R.string.bad_internet_subtitle),
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 4.dp, start = 2.dp, end = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PlusButton() {
    val activity = LocalContext.current as MainActivity

    FloatingActionButton(
        modifier = Modifier
            .clip(RoundedCornerShape(MaterialTheme.shapes.large.topStart))
            .size(56.dp)
            .background(Color.White, MaterialTheme.shapes.large)
            .border(1.dp, Color.Black, MaterialTheme.shapes.large)
            .shadow(8.dp, MaterialTheme.shapes.large),
        onClick = {
            activity.mapView?.let {
                val center = it.map.cameraPosition.target
                val curZoom = it.map.cameraPosition.zoom
                it.map.move(
                    CameraPosition(center, curZoom + 1, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 0.3f),
                    null
                )
            }
        },
        containerColor = Color.White,
        contentColor = Purple40
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_plus_button),
            contentDescription = null,
        )
    }
}

@Composable
private fun MinusButton() {
    val activity = LocalContext.current as MainActivity

    FloatingActionButton(
        modifier = Modifier
            .clip(RoundedCornerShape(MaterialTheme.shapes.large.topStart))
            .size(56.dp)
            .background(Color.White, MaterialTheme.shapes.large)
            .border(1.dp, Color.Black, MaterialTheme.shapes.large)
            .shadow(8.dp, MaterialTheme.shapes.large),
        onClick = {
            activity.mapView?.let {
                val center = it.map.cameraPosition.target
                val curZoom = it.map.cameraPosition.zoom
                it.map.move(
                    CameraPosition(center, curZoom - 1, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 0.3f),
                    null
                )
            }
        },
        containerColor = Color.White,
        contentColor = Purple40
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_minus_button),
            contentDescription = null,
        )
    }
}


private fun setPoints(
    context: Context,
    collection: MapObjectCollection,
    imageProvider: ImageProvider,
    points: List<School>,
    bottomSheetEventBus: BottomSheetEventBus,
    pointsListeners: MutableList<MapObjectTapListener>
) {

    points.forEach {
        val placemark = collection.addPlacemark(it.point, imageProvider)
        val listener = MapObjectTapListener { _, _ ->
            bottomSheetEventBus.show {
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(
                        text = it.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Text(
                        text = context.getString(R.string.opening_hours),
                        color = Grey,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = it.openingHours,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
            true
        }
        pointsListeners.add(listener)
        placemark.addTapListener(listener)
    }
}