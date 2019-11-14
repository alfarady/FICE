package id.web.runup.fice.mvp.applicantsubmission;

import android.content.Intent;
import android.util.Log;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.Application;
import id.web.runup.fice.data.models.Job;
import id.web.runup.fice.data.models.JobAction;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicantSubmissionPresenter extends AbstractPresenter {
    private IApplicantSubmissionView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;

    public ApplicantSubmissionPresenter(IApplicantSubmissionView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        final int id_trx = intent.getIntExtra("id_trx", 1);
        setData(id_trx);
    }

    public void setData(int id_trx){
        Call<Application> daftarCall = mApiClient.getApplication(mDatabase.getUserToken(), id_trx);
        daftarCall.enqueue(new Callback<Application>() {
            @Override
            public void onResponse(Call<Application> call, Response<Application> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Application data = response.body();
                        mView.initDataAS(data.getName(), data.getEmail(), data.getPhone(), data.getAge(), data.getAddress(), "Show CV", data.getAvaUrl(), data.getCvUrl(), data.getProgress());
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<Application> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void approve(int id_job){
        this.mView.startLoading();
        Call<JobAction> daftarCall = mApiClient.approveJob(mDatabase.getUserToken(), id_job);
        daftarCall.enqueue(new Callback<JobAction>() {
            @Override
            public void onResponse(Call<JobAction> call, Response<JobAction> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showComplateActivity("approved");
                    } else {
                        mView.showMsg(response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {
                    String errorBody = response.errorBody().toString();
                    mView.showMsg(errorBody);
                }
                mView.stopLoading();
            }

            @Override
            public void onFailure(Call<JobAction> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.stopLoading();
            }
        });
    }

    public void reject(int id_job){
        this.mView.startLoading();
        Call<JobAction> daftarCall = mApiClient.rejectJob(mDatabase.getUserToken(), id_job);
        daftarCall.enqueue(new Callback<JobAction>() {
            @Override
            public void onResponse(Call<JobAction> call, Response<JobAction> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showComplateActivity("rejected");
                    } else {
                        mView.showMsg(response.body().getMessage());
                    }
                } else if (response.errorBody() != null) {
                    String errorBody = response.errorBody().toString();
                    mView.showMsg(errorBody);
                }
                mView.stopLoading();
            }

            @Override
            public void onFailure(Call<JobAction> call, Throwable t) {
                mView.showMsg(t.getMessage());
                mView.stopLoading();
            }
        });
    }
}
