package id.web.runup.fice.mvp.home;

import android.content.Intent;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.User;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import id.web.runup.fice.mvp.main.MainActivity;
import id.web.runup.fice.mvp.splash.SplashActivity;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends AbstractPresenter {
    private IHomeView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;

    public HomePresenter(IHomeView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        setDataHome();
    }

    public void setDataHome(){
        Call<User> daftarCall = mApiClient.getUser(mDatabase.getUserToken(), mDatabase.getUserLocation());
        daftarCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.initDataHome(response.body().getName(), response.body().getAvaUrl());
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }
}
