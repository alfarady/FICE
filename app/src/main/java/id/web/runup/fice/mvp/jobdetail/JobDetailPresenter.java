package id.web.runup.fice.mvp.jobdetail;

import android.content.Intent;
import android.util.Log;

import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.Job;
import id.web.runup.fice.data.models.JobAction;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailPresenter extends AbstractPresenter {
    private IJobDetailView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;

    public JobDetailPresenter(IJobDetailView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        String id_job = String.valueOf(intent.getIntExtra("id_job", 1));
        setDataJobDetail(id_job);
    }

    public void setDataJobDetail(String id_job){
        Call<Job> daftarCall = mApiClient.getJob(mDatabase.getUserToken(), id_job);
        daftarCall.enqueue(new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        Job data = response.body();
                        Log.i("JD", data.getHrdName());
                        mView.initDataJobDetail(data.getHrdName(), data.getJudul(), data.getDeskripsi(), (data.getIdTipe().equals("1")) ? "Fulltime" : "Freelance", (data.getIdKategori().equals("1")) ? "IT" : "Education", "Rp "+String.format("%,.0f", Double.parseDouble(String.valueOf(data.getGaji()))), data.getAlamat(), data.getHrdAva());
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void apply(String id_job){
        this.mView.startLoading();
        Call<JobAction> daftarCall = mApiClient.applyJob(mDatabase.getUserToken(), Integer.parseInt(id_job));
        daftarCall.enqueue(new Callback<JobAction>() {
            @Override
            public void onResponse(Call<JobAction> call, Response<JobAction> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mView.showComplateActivity();
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
