package id.web.runup.fice.mvp.home;

import android.content.Intent;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class HomePresenter extends AbstractPresenter {
    private IHomeView mView;
    UserPreferences mDatabase = new UserPreferences();

    public HomePresenter(IHomeView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        setDataHome();
    }

    public void setDataHome(){
        this.mView.initDataHome("Alfarady", "https://instagram.fcgk4-1.fna.fbcdn.net/vp/975f8e0957d972672b866c16bc44d595/5D706051/t51.2885-15/e35/57425301_134013721044083_8205737589643631267_n.jpg?_nc_ht=instagram.fcgk4-1.fna.fbcdn.net&_nc_cat=111");
    }
}
