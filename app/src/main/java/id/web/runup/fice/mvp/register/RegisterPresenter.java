package id.web.runup.fice.mvp.register;

import android.content.Intent;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class RegisterPresenter extends AbstractPresenter {
    private IRegisterView mView;
    UserPreferences mDatabase = new UserPreferences();

    public RegisterPresenter(IRegisterView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        //
    }
}
