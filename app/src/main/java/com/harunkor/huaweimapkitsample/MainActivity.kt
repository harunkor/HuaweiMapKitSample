package com.harunkor.huaweimapkitsample

import android.Manifest
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.huawei.hms.maps.*
import com.huawei.hms.maps.model.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var huaweiMap: HuaweiMap? = null
    private lateinit var mapView: MapView
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var mapViewBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationPermission()
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }

        //mapView()
        mapProgrammatically()



    }

    private fun mapView(){
        mapView = findViewById(R.id.mapView)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    private fun mapProgrammatically(){
        val huaweiMapOptions = HuaweiMapOptions()
        huaweiMapOptions.compassEnabled(true)
        huaweiMapOptions.zoomControlsEnabled(true)
        huaweiMapOptions.scrollGesturesEnabled(true)
        huaweiMapOptions.zoomGesturesEnabled(true)
        mapView = MapView(this, huaweiMapOptions)
        mapView?.onCreate(mapViewBundle)
        mapView?.getMapAsync(this)
        setContentView(mapView)
    }

    private fun addMarker(){
        val PARIS = LatLng(48.893478, 2.334595)
        val ORSAY = LatLng(48.85, 2.78)
        huaweiMap?.addMarker(MarkerOptions().position(PARIS).title("paris").snippet("hello").clusterable(true))
        huaweiMap?.addMarker(MarkerOptions().position(ORSAY)
            .alpha(0.5f)
            .title("Orsay")
            .snippet("hello")
            .clusterable(true)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_custom_marker)))
    }

    private fun addPolygon(){
        huaweiMap?.addPolygon(
            PolygonOptions().addAll(createRectangle(LatLng(48.893478, 2.334595), 0.1, 0.1))
            .fillColor(Color.GREEN)
            .strokeColor(Color.BLACK))
    }

    private fun addPolyline(){
        val FRANCE = LatLng(47.893478, 2.334595)
        val FRANCE1 = LatLng(48.993478, 3.434595)
        val FRANCE2 = LatLng(48.693478, 2.134595)
        val FRANCE3 = LatLng(48.793478, 2.334595)
        huaweiMap?.addPolyline(
            PolylineOptions().add(FRANCE, FRANCE1, FRANCE2, FRANCE3)
                .color(Color.BLUE)
                .width(3f))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


    private fun locationPermission(){
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                when {
                    permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                        // Precise location access granted.
                    }
                    permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                        // Only approximate location access granted.

                    } else -> {
                    // No location access granted.
                }
                }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,))
    }

    override fun onMapReady(hMap: HuaweiMap) {
        huaweiMap=hMap
        huaweiMap?.isMyLocationEnabled = false //true = Adds icon to guide you to your location and map support multi-language
        huaweiMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(48.893478, 2.334595), 10f))
        addMarker()
        //addPolygon()
        addPolyline()

    }

    fun createRectangle(center: LatLng, halfWidth: Double, halfHeight: Double): List<LatLng> {
        return listOf(LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
            LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
            LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
            LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
            LatLng(center.latitude - halfHeight, center.longitude - halfWidth))
    }


}