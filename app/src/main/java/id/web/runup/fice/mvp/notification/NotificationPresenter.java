package id.web.runup.fice.mvp.notification;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.data.adapter.MFeedAdapter;
import id.web.runup.fice.data.adapter.MNotificationAdapter;
import id.web.runup.fice.data.api.ApiService;
import id.web.runup.fice.data.api.IApiService;
import id.web.runup.fice.data.models.JobFeed;
import id.web.runup.fice.data.models.JobFeedData;
import id.web.runup.fice.data.models.Notif;
import id.web.runup.fice.data.models.NotifData;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationPresenter extends AbstractPresenter {
    private INotificationView mView;
    UserPreferences mDatabase = new UserPreferences();
    IApiService mApiClient;
    List<MNotificationAdapter> mNotif;

    public NotificationPresenter(INotificationView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        mApiClient = ApiService.getClient().create(IApiService.class);
        setNotificationData();
    }

    public void setNotificationData() {
        Call<Notif> daftarCall = mApiClient.getNotif(mDatabase.getUserToken());
        daftarCall.enqueue(new Callback<Notif>() {
            @Override
            public void onResponse(Call<Notif> call, Response<Notif> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        mNotif = new ArrayList<>();
                        for(int i=0; i<response.body().getData().size(); i++){
                            NotifData data = response.body().getData().get(i);
                            mNotif.add(new MNotificationAdapter(data.getId(), data.getNotifType(), data.getNotifMessage(), data.getCreatedAt(), Integer.parseInt(data.getIdTrx())));
                        }
                        mView.initRecyclerView(mNotif);
                    } else {
                        mView.startWelcomeActivity();
                    }
                } else if (response.errorBody() != null) {
                    // Get response errorBody
                    mView.startWelcomeActivity();
                }
            }

            @Override
            public void onFailure(Call<Notif> call, Throwable t) {
                mView.startWelcomeActivity();
            }
        });
    }
}
