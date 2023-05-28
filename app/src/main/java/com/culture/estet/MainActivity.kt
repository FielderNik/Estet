package com.culture.estet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.culture.estet.ui.presentation.root.RootScreen
import com.culture.estet.ui.theme.EstetTheme
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.mapview.MapView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var mapObjects: MapObjectCollection? = null
    var pointListener: MutableList<MapObjectTapListener> = mutableListOf()
    var mapView: MapView? = null

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.initialize(this)
        setContent {
            EstetTheme {
                RootScreen()
            }
        }
    }
}



