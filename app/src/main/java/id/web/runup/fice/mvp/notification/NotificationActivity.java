package id.web.runup.fice.mvp.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.data.adapter.MNotificationAdapter;
import id.web.runup.fice.data.adapter.NotificationAdapter;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView mRVListNotif;
    List<MNotificationAdapter> mNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mRVListNotif = findViewById(R.id.rvNotif);
        mNotif = new ArrayList<>();
        mNotif.add(new MNotificationAdapter(1, "Congratulations, your application has been received, please contact your boss to ask about your job", "Now"));
        mNotif.add(new MNotificationAdapter(2, "Sorry, your application has been rejected, please find another job based on your passion", "Yesterday"));
        mNotif.add(new MNotificationAdapter(3, "Someone has applied for your vacancy, please click to accept or reject the application", "Yesterday"));
        mNotif.add(new MNotificationAdapter(4, "Sorry, your application has been rejected, please find another job based on your passion", "26/3"));

        NotificationAdapter adapter = new NotificationAdapter(mNotif, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        mRVListNotif.setLayoutManager(layoutManager);
        mRVListNotif.setAdapter(adapter);
    }
}
