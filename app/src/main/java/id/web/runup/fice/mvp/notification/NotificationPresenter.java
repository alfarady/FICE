package id.web.runup.fice.mvp.notification;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.data.adapter.MNotificationAdapter;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.AbstractPresenter;

public class NotificationPresenter extends AbstractPresenter {
    private INotificationView mView;
    UserPreferences mDatabase = new UserPreferences();
    List<MNotificationAdapter> mNotif;

    public NotificationPresenter(INotificationView mView) {
        this.mView = mView;
    }

    public void onCreate(Intent intent) {
        setNotificationData();
    }

    public void setNotificationData() {
        mNotif = new ArrayList<>();
        mNotif.add(new MNotificationAdapter(1, "Congratulations, your application has been received, please contact your boss to ask about your job", "Now"));
        mNotif.add(new MNotificationAdapter(2, "Sorry, your application has been rejected, please find another job based on your passion", "Yesterday"));
        mNotif.add(new MNotificationAdapter(3, "Someone has applied for your vacancy, please click to accept or reject the application", "Yesterday"));
        mNotif.add(new MNotificationAdapter(4, "Sorry, your application has been rejected, please find another job based on your passion", "26/3"));
        this.mView.initRecyclerView(mNotif);
    }
}
