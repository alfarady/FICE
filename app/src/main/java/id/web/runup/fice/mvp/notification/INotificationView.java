package id.web.runup.fice.mvp.notification;

import java.util.List;

import id.web.runup.fice.data.adapter.MNotificationAdapter;

public interface INotificationView {
    void showMainActivity();
    void initRecyclerView(List<MNotificationAdapter> mNotif);
    void startWelcomeActivity();
}
