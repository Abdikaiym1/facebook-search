package com.example.xstrike.facebook_search_by_tag.ui.fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xstrike.facebook_search_by_tag.R;
import com.example.xstrike.facebook_search_by_tag.beans.StructureQuery;
import com.example.xstrike.facebook_search_by_tag.ui.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryFragment extends CoreFragment {

    private static final int REQUEST_LOCATION = 1;

    private View view;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessToken accessToken;
    private LocationManager locationManager;
    private StructureQuery structureQuery;
    private FloatingActionButton floatingActionButton;
    private EditText editLatitude;
    private EditText editLongitude;


    public QueryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.query_fragment, container, false);

        loginButton = view.findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,address,age_range,can_review_measurement_request,context");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.d("onCancel", "cancel");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        ActivityCompat.requestPermissions((MainActivity)getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        floatingActionButton = view.findViewById(R.id.fa_button);
        floatingActionButton.setOnClickListener(faButtonClick);

        editLatitude = view.findViewById(R.id.edit_latitude);
        editLongitude = view.findViewById(R.id.edit_longitude);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission((MainActivity)getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission((MainActivity)getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((MainActivity)getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                structureQuery = new StructureQuery();
                structureQuery.setLatitude(String.valueOf(location.getLatitude()));
                structureQuery.setLongitude(String.valueOf(location.getLongitude()));
                editLongitude.setText(structureQuery.getLongitude());
                editLongitude.setFocusable(false);
                editLatitude.setText(structureQuery.getLatitude());
                editLatitude.setFocusable(false);
            } else {
                Toast.makeText(getContext(), "Unble to Trace your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    View.OnClickListener faButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                buildAlertErrorGPS();
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                getLocation();
            }
        }
    };

    protected void buildAlertErrorGPS()  {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Пожалуйста, включите GPS")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
