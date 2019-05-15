package id.web.runup.fice.mvp.jobdetail;

import android.content.Intent;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class JobDetailPresenter extends AbstractPresenter {
    private IJobDetailView mView;
    UserPreferences mDatabase = new UserPreferences();

    public JobDetailPresenter(IJobDetailView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        //
    }

    public void postDataJob(){

    }
}
