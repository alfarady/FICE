package id.web.runup.fice.mvp.applicantsubmission;

import android.content.Intent;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class ApplicantSubmissionPresenter extends AbstractPresenter {
    private IApplicantSubmissionView mView;
    UserPreferences mDatabase = new UserPreferences();

    public ApplicantSubmissionPresenter(IApplicantSubmissionView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        setData();
    }

    public void setData(){
        this.mView.initDataAS("Alfarady", "radenalfa9@gmail.com", "082240534957", "20", "Jl Raya Tawar No 001 Jombang", "Alfa.pdf", "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/03/03110aa204cca8dd27c0257f984b1b640148cd0c_full.jpg", "http://google.com");
    }
}
