package id.web.runup.fice.mvp.find;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.data.adapter.MFeedAdapter;
import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.JobFeed;
import id.web.runup.fice.data.models.JobFeedData;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPresenter extends AbstractPresenter {
    private IFindView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;

    public FindPresenter(IFindView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        setFeedData();
    }

    public void setFeedData(){
        Call<JobFeed> daftarCall = mApiClient.getFeed(mDatabase.getUserToken());
        daftarCall.enqueue(new Callback<JobFeed>() {
            @Override
            public void onResponse(Call<JobFeed> call, Response<JobFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<MFeedAdapter> mFeed;
                        mFeed = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            JobFeedData data = response.body().getData().get(i);
                            mFeed.add(new MFeedAdapter(data.getId(), data.getHrdAva(), data.getJudul(), data.getDeskripsi(), data.getHrdName(), data.getAlamat(), data.getGaji()));
                        }
                        mView.initRecyclerView(mFeed);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<JobFeed> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void searchFeedData(String what){
        Call<JobFeed> daftarCall = mApiClient.searchFeed(mDatabase.getUserToken(), what);
        daftarCall.enqueue(new Callback<JobFeed>() {
            @Override
            public void onResponse(Call<JobFeed> call, Response<JobFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<MFeedAdapter> mFeed;
                        mFeed = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            JobFeedData data = response.body().getData().get(i);
                            mFeed.add(new MFeedAdapter(data.getId(), data.getHrdAva(), data.getJudul(), data.getDeskripsi(), data.getHrdName(), data.getAlamat(), data.getGaji()));
                        }
                        mView.initRecyclerView(mFeed);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<JobFeed> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void setFeedDataNear(){
        Call<JobFeed> daftarCall = mApiClient.getFeedNearby(mDatabase.getUserToken(), mDatabase.getUserLocation());
        daftarCall.enqueue(new Callback<JobFeed>() {
            @Override
            public void onResponse(Call<JobFeed> call, Response<JobFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<MFeedAdapter> mFeed;
                        mFeed = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            JobFeedData data = response.body().getData().get(i);
                            mFeed.add(new MFeedAdapter(data.getId(), data.getHrdAva(), data.getJudul(), data.getDeskripsi(), data.getHrdName(), data.getAlamat(), data.getGaji()));
                        }
                        mView.initRecyclerView(mFeed);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<JobFeed> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void searchFeedDataNear(String what){
        Call<JobFeed> daftarCall = mApiClient.searchFeedNeary(mDatabase.getUserToken(), mDatabase.getUserLocation(), what);
        daftarCall.enqueue(new Callback<JobFeed>() {
            @Override
            public void onResponse(Call<JobFeed> call, Response<JobFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<MFeedAdapter> mFeed;
                        mFeed = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            JobFeedData data = response.body().getData().get(i);
                            mFeed.add(new MFeedAdapter(data.getId(), data.getHrdAva(), data.getJudul(), data.getDeskripsi(), data.getHrdName(), data.getAlamat(), data.getGaji()));
                        }
                        mView.initRecyclerView(mFeed);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<JobFeed> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void setFeedDataMostView(){
        Call<JobFeed> daftarCall = mApiClient.getFeedMostView(mDatabase.getUserToken(), 1);
        daftarCall.enqueue(new Callback<JobFeed>() {
            @Override
            public void onResponse(Call<JobFeed> call, Response<JobFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<MFeedAdapter> mFeed;
                        mFeed = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            JobFeedData data = response.body().getData().get(i);
                            mFeed.add(new MFeedAdapter(data.getId(), data.getHrdAva(), data.getJudul(), data.getDeskripsi(), data.getHrdName(), data.getAlamat(), data.getGaji()));
                        }
                        mView.initRecyclerView(mFeed);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<JobFeed> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }

    public void searchFeedDataMostView(String what){
        Call<JobFeed> daftarCall = mApiClient.searchFeedMostView(mDatabase.getUserToken(), 1, what);
        daftarCall.enqueue(new Callback<JobFeed>() {
            @Override
            public void onResponse(Call<JobFeed> call, Response<JobFeed> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        List<MFeedAdapter> mFeed;
                        mFeed = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            JobFeedData data = response.body().getData().get(i);
                            mFeed.add(new MFeedAdapter(data.getId(), data.getHrdAva(), data.getJudul(), data.getDeskripsi(), data.getHrdName(), data.getAlamat(), data.getGaji()));
                        }
                        mView.initRecyclerView(mFeed);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<JobFeed> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }
}
