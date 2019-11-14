package id.web.runup.fice.mvp.jobpost;

import android.content.Intent;
import android.text.TextUtils;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.JobPost;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobPostPresenter extends AbstractPresenter {
    private IJobPostView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;

    public JobPostPresenter(IJobPostView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
    }

    public void postDataJob(String jobtitle, String jobdesc, int type, int category, String address, String duration, String salary){
        if(TextUtils.isEmpty(jobtitle) || TextUtils.isEmpty(jobdesc) || (type == 0) || (category == 0) || TextUtils.isEmpty(address) || TextUtils.isEmpty(duration) || TextUtils.isEmpty(salary)){
            this.mView.showMsg("You must fill the form above");
            this.mView.setFocus("title");
        } else if(jobtitle.length() > 50){
            this.mView.showMsg("Your job title exceed the maximum character");
            this.mView.setFocus("title");
        } else if(jobdesc.length() > 250){
            this.mView.showMsg("Your job description exceed the maximum character");
            this.mView.setFocus("desc");
        } else {
            this.mView.startLoading();
            this.post(jobtitle, jobdesc, type, category, address, duration, salary);
        }
    }

    private void post(String jobtitle, String jobdesc, int type, int category, String address, String duration, String salary) {
        Call<JobPost> daftarCall = mApiClient.postJob(mDatabase.getUserToken(), category, type, jobtitle, jobdesc, address, salary, duration);
        daftarCall.enqueue(new Callback<JobPost>() {
            @Override
            public void onResponse(Call<JobPost> call, Response<JobPost> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showComplateActivity();
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
            public void onFailure(Call<JobPost> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.stopLoading();
            }
        });
    }
}
