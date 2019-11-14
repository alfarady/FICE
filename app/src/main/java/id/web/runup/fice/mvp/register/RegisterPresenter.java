package id.web.runup.fice.mvp.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import dmax.dialog.SpotsDialog;
import id.web.runup.fice.R;
import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.Register;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter extends AbstractPresenter {
    private IRegisterView mView;
    UserPreferences mDatabase = new UserPreferences();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    IApiService mApiClient;

    public RegisterPresenter(IRegisterView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
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
            this.mView.startLoading();
            String notifToken = mDatabase.getUserNotifToken();
            this.register(email, password, msisdn, fName, age, country, notifToken);
        }
    }

    private void register(String email, String password, String msisdn, String fName, String age, String country, String notifToken) {
        Call<Register> daftarCall = mApiClient.doRegister(email, password, msisdn, fName, age, country, notifToken, mDatabase.getUserLocation());
        daftarCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mDatabase.setUserToken(response.body().getToken());
                        mView.showMainActivity();
                    } else {
                        mView.showMsg(response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    String errorBody = response.errorBody().toString();
                    mView.showMsg(errorBody);
                }
                mView.stopLoading();
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.stopLoading();
            }
        });
    }
}
