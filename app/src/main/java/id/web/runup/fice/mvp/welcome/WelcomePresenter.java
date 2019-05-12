package id.web.runup.fice.mvp.welcome;

import android.content.Intent;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class WelcomePresenter extends AbstractPresenter {
    private IWelcomeView mView;
    UserPreferences mDatabase = new UserPreferences();

    public WelcomePresenter(IWelcomeView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        //
    }
}
