package id.web.runup.fice.mvp.login;

import android.content.Intent;
import android.text.TextUtils;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.Register;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends AbstractPresenter {
    private ILoginView mView;
    UserPreferences mDatabase = new UserPreferences();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    IApiService mApiClient;

    public LoginPresenter(ILoginView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
    }

    public void doLogin(String email, String password){
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            this.mView.setFocus("email");
            this.mView.showMsg("You must fill the form above");
        } else if(!email.matches(emailPattern)){
            this.mView.setFocus("email");
            this.mView.showMsg("Invalid email address");
        } else if(password.length() < 6){
            this.mView.setFocus("password");
            this.mView.showMsg("Your password must be at least 6 latters");
        } else {
            this.mView.startLoading();
            String notifToken = mDatabase.getUserNotifToken();
            this.login(email, password, notifToken);
        }
    }

    private void login(String email, String password, String notifToken){
        Call<Register> daftarCall = mApiClient.doLogin(email, password, notifToken, mDatabase.getUserLocation());
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
