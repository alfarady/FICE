package id.web.runup.fice;

import android.app.Application;
import id.web.runup.fice.data.preferences.PreferencesProvider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesProvider.init(this);
    }

}
