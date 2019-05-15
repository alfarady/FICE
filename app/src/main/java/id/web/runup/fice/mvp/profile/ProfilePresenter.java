package id.web.runup.fice.mvp.profile;

import android.content.Intent;
import android.text.TextUtils;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class ProfilePresenter extends AbstractPresenter {
    private IProfileView mView;
    UserPreferences mDatabase = new UserPreferences();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public ProfilePresenter(IProfileView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        //
    }

    public void doRegister(String email, String password, String msisdn, String fName, String age, String country) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(msisdn) || TextUtils.isEmpty(fName) || TextUtils.isEmpty(age) || TextUtils.isEmpty(country)){
            this.mView.setFocus("email");
            this.mView.showMsg("You must fill the form above");
        } else if(!email.matches(emailPattern)){
            this.mView.setFocus("email");
            this.mView.showMsg("Invalid email address");
        } else if(password.length() < 6){
            this.mView.setFocus("password");
            this.mView.showMsg("Your password must be at least 6 latters");
        } else if(msisdn.length() < 10){
            this.mView.setFocus("msisdn");
            this.mView.showMsg("Your phone number must be at least 10 numbers");
        } else if(age.length() != 2 && !TextUtils.isDigitsOnly(age)){
            this.mView.setFocus("age");
            this.mView.showMsg("Invalid age number");
        } else {
            this.mView.setDone();
        }
    }
}
