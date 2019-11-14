package id.web.runup.fice.mvp.splash;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import java.util.HashMap;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.User;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.main.MainActivity;
import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.notification.NotificationActivity;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AbstractView{

    IApiService mApiClient;
    UserPreferences mDatabase = new UserPreferences();
    private LocationManager locationManager;
    private android.location.LocationListener myLocationListener;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String str = "https://ws.tokopedia.com/v4/notification/get_notification.pl?os_type=1&device_id=dotI8hBTyPc:APA91bGYnQvIJA04DS4USs00hTAWjQH632A0yaaRo2oxEpzFfpxXT-pOlZjntiNk8-QuTnXp3R-ZIx_pT69QKvW9ZIdn7wamr8UvbDCFilpidvqloAUVn9oOo79b4jMLljBO4-SBZLxI&device_time=1559917580&hash=f187b36dd8b671de254961023dcb6d39&type=2&user_id=7965815";
        String query = Uri.parse(str).getQuery();
        String path = Uri.parse(str).getPath();
        Log.e("[JEK] Uri ->", ""+str);
        Log.e("[JEK] Query ->", ""+query);
        Log.e("[JEK] Path ->", ""+path);

            checkLocation();
        OneSignal.startInit(this).setNotificationOpenedHandler(new NotificationOpenHandler()).init();

        mApiClient = ApiService.getClient().create(IApiService.class);
        finishSplash();
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void finishSplash()
    {
        Call<User> daftarCall = mApiClient.getUser(mDatabase.getUserToken(), mDatabase.getUserLocation());
        daftarCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                        finish();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                finish();
            }
        });
    }

    public void checkLocation() {

        String serviceString = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceString);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }


        myLocationListener = new android.location.LocationListener() {
            public void onLocationChanged(Location locationListener) {

                Location location;
                if (isGPSEnabled(SplashActivity.this)) {
                    if (locationListener != null) {
                        if (ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SplashActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                String aLocation = latitude + "," + longitude;
                                mDatabase.setLocation(aLocation);
                            }
                        }
                    }
                } else if (isInternetConnected(SplashActivity.this)) {
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            String aLocation = latitude + "," + longitude;
                            mDatabase.setLocation(aLocation);
                        }
                    }
                }
            }

            public void onProviderDisabled(String provider) {

            }

            public void onProviderEnabled(String provider) {

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, myLocationListener);
    }

    public static boolean isInternetConnected(Context ctx) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        // Check if wifi or mobile network is available or not. If any of them is
        // available or connected then it will return true, otherwise false;
        if (wifi != null) {
            if (wifi.isConnected()) {
                return true;
            }
        }
        if (mobile != null) {
            if (mobile.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public boolean isGPSEnabled(Context mContext) {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    class NotificationOpenHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
