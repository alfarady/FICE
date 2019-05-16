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
        setDataJobDetail();
    }

    public void setDataJobDetail(){
        this.mView.initDataJobDetail("Sunari de Albert Queue", "Fullstack Programming", "We are hiring fullstack web programming who has good experience at Nodejs and Vuejs", "Fulltime", "IT", "Rp 10.000.000", "Jl Kengangan No 100 Bandung");
    }
}
