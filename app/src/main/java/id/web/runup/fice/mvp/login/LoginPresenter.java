package id.web.runup.fice.mvp.login;

import android.content.Intent;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class LoginPresenter extends AbstractPresenter {
    private ILoginView mView;
    UserPreferences mDatabase = new UserPreferences();

    public LoginPresenter(ILoginView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        //
    }
}
