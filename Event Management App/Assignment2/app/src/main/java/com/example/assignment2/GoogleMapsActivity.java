package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.assignment2.provider.Category;
import com.example.assignment2.provider.EMAViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.assignment2.databinding.ActivityGoogleMapsBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ActivityGoogleMapsBinding binding;

    private Geocoder geocoder;

    SupportMapFragment mapFragment;

    private EMAViewModel emaViewModel;

    Handler handler = new Handler(Looper.getMainLooper());

    LatLng location = new LatLng(3.710756396207877, 101.99398642789973);

    List<Address> nameToAddressList = new ArrayList<>();

    String eventLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoogleMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        geocoder = new Geocoder(this, Locale.getDefault());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        emaViewModel = new ViewModelProvider(this).get(EMAViewModel.class);
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

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            mMap = googleMap;

            String categoryId = getIntent().getStringExtra(KeyStore.KEY_CATEGORY_ID);

            Category category = emaViewModel.getCategory(categoryId);
            eventLocation = category.getEventLocation();

            handler.post(() -> {
                try {
                    nameToAddressList = geocoder.getFromLocationName(eventLocation, 1);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Category address not found", Toast.LENGTH_SHORT).show();
                }

                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(10));

                if (!nameToAddressList.isEmpty()) {
                    location = new LatLng(nameToAddressList.get(0).getLatitude(), nameToAddressList.get(0).getLongitude());
                    mMap.addMarker(new MarkerOptions().position(location).title(eventLocation));
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

            });
        });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                String msg;
                String selectedCountry = "";

                List<Address> addresses = new ArrayList<>();

                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if (addresses.isEmpty()) {
                    msg = "No country at this location!! Sorry";
                } else {
                    android.location.Address address = addresses.get(0);
                    selectedCountry = address.getCountryName();
                    msg = "Country selected is " + selectedCountry;
                }

                Toast.makeText(mapFragment.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}