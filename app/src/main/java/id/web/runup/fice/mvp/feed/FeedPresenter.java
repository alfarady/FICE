package id.web.runup.fice.mvp.feed;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.data.adapter.MFeedAdapter;
import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.JobFeed;
import id.web.runup.fice.data.models.JobFeedData;
import id.web.runup.fice.data.models.User;
import id.web.runup.fice.data.preferences.UserPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPresenter {
    private IFeedView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;

    public FeedPresenter(IFeedView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        final int cat = intent.getIntExtra("cat", 0);
        final int type = intent.getIntExtra("type", 0);
        if(cat>=1){
            setFeedDataByCat(cat);
        } else if(type>=1){
            setFeedDataByType(type);
        } else {
            setFeedData();
        }
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

    public void setFeedDataByCat(int what){
        Call<JobFeed> daftarCall = mApiClient.getFeedByCat(mDatabase.getUserToken(), what);
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

    public void setFeedDataByType(int what){
        Call<JobFeed> daftarCall = mApiClient.getFeedByType(mDatabase.getUserToken(), what);
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
