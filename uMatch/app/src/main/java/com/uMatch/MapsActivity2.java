package com.uMatch;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uMatch.databinding.ActivityMaps2Binding;

// Implementasi MapsView

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    LatLng home, position;
    Double latitude, longitude, x, y;
    int PLACE_AUTO=1;

    private GoogleMap mMap;
    private ActivityMaps2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        home = new LatLng(-6.257385, 106.618320);
        map.addMarker(new MarkerOptions().position(home).title("MOP Company")).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLng(home));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(home,16));
        map.setTrafficEnabled(true);
    }


    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    Intent data = result.getData();
                    if(resultCode == RESULT_OK){
                        Place place = PlaceAutocomplete.getPlace(getApplicationContext(), data);
                        LatLng search = place.getLatLng();
                        x=place.getLatLng().latitude;
                        y = place.getLatLng().longitude;
                        String alamat = place.getAddress().toString();
                        String telp = place.getPhoneNumber().toString();
                        String nama = place.getName().toString();
                        String snip = alamat+System.getProperty("line.separator")+telp;
                        map.addMarker(new MarkerOptions().position(search).title(nama).snippet(snip)).showInfoWindow();
                        map.animateCamera(CameraUpdateFactory.newLatLng(search));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 18));
                    }
                }


            });
}