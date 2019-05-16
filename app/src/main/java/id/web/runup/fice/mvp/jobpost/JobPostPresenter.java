package id.web.runup.fice.mvp.jobpost;

import android.content.Intent;
import android.text.TextUtils;

import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class JobPostPresenter extends AbstractPresenter {
    private IJobPostView mView;
    UserPreferences mDatabase = new UserPreferences();

    public JobPostPresenter(IJobPostView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        //
    }

    public void postDataJob(String jobtitle, String jobdesc, String type, String category, String address, String duration, String salary){
        if(TextUtils.isEmpty(jobtitle) || TextUtils.isEmpty(jobdesc) || TextUtils.isEmpty(type) || TextUtils.isEmpty(category) || TextUtils.isEmpty(address) || TextUtils.isEmpty(duration) || TextUtils.isEmpty(salary)){
            this.mView.showMsg("You must fill the form above");
            this.mView.setFocus("title");
        } else if(jobtitle.length() > 50){
            this.mView.showMsg("Your job title exceed the maximum character");
            this.mView.setFocus("title");
        } else if(jobdesc.length() > 250){
            this.mView.showMsg("Your job description exceed the maximum character");
            this.mView.setFocus("desc");
        } else {
            this.mView.showComplateActivity();
        }
    }
}
