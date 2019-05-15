package id.web.runup.fice.mvp.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.data.adapter.MNotificationAdapter;
import id.web.runup.fice.data.adapter.NotificationAdapter;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.main.MainActivity;

public class NotificationActivity extends AbstractView implements INotificationView {

    NotificationPresenter mPresenter = new NotificationPresenter(this);
    RecyclerView mRVListNotif;
    TextView mNotifClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        mNotifClose = findViewById(R.id.notifClose);
        mRVListNotif = findViewById(R.id.rvNotif);

        this.initListener();
        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void initListener(){
        mNotifClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainActivity();
            }
        });
    }

    @Override
    public void initRecyclerView(List<MNotificationAdapter> mNotif){
        NotificationAdapter adapter = new NotificationAdapter(mNotif, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        mRVListNotif.setLayoutManager(layoutManager);
        mRVListNotif.setAdapter(adapter);
    }

    @Override
    public void showMainActivity() {
        startActivity(new Intent(NotificationActivity.this, MainActivity.class));
        finish();
    }
}
