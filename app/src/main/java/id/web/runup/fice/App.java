package id.web.runup.fice;

import android.app.Application;
import android.util.Log;

import com.onesignal.OneSignal;

import id.web.runup.fice.data.preferences.IUserPreferences;
import id.web.runup.fice.data.preferences.PreferencesProvider;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.notification.NotificationActivity;
import id.web.runup.fice.mvp.splash.SplashActivity;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesProvider.init(this);
        final IUserPreferences mDatabase = new UserPreferences();

        // LumbungDesa Initialization
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.startInit(this)
                .autoPromptLocation(true)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Log.d("debug", "User:" + userId);
                if (registrationId != null)
                    Log.d("debug", "registrationId:" + registrationId);
                mDatabase.setUserNotifToken(userId);
            }
        });
    }

}
