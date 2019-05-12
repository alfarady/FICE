package id.web.runup.fice.mvp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import id.web.runup.fice.R;
import id.web.runup.fice.data.preferences.IUserPreferences;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.feed.FeedActivity;
import id.web.runup.fice.mvp.jobpost.JobPostActivity;
import id.web.runup.fice.mvp.login.LoginActivity;
import id.web.runup.fice.mvp.notification.NotificationActivity;
import id.web.runup.fice.mvp.register.RegisterActivity;

public class HomeFragment extends Fragment {

    RoundedImageView mFindJobRoundedImgView;
    TextView mTxtLeftButton, mTxtRightButton;
    Button mBtnChangeRoles;
    IUserPreferences mDatabase;
    LinearLayout mBellNotif;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        mDatabase = new UserPreferences();

        mFindJobRoundedImgView = v.findViewById(R.id.findJobRoundedImgView);
        mBtnChangeRoles = v.findViewById(R.id.btnChangeRoles);
        mTxtLeftButton = v.findViewById(R.id.txtLeftButton);
        mTxtRightButton = v.findViewById(R.id.txtRightButton);
        mBellNotif = v.findViewById(R.id.bellNotif);

        this.initListener();

        return v;
    }

    private void initListener(){
        if(mDatabase.getRoles().equals("worker")) {
            mTxtLeftButton.setText("Part \nTime Job");
            mTxtRightButton.setText("Find Worker");
            mFindJobRoundedImgView.setImageResource(R.drawable.bg_findwork);
        } else if(mDatabase.getRoles().equals("hrd")) {
            mTxtLeftButton.setText("Find \nFreelance");
            mTxtRightButton.setText("Find a Job");
            mFindJobRoundedImgView.setImageResource(R.drawable.bg_findwork_hrd);
        }

        mFindJobRoundedImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatabase.getRoles().equals("worker")) {
                    startActivity(new Intent(getActivity(), FeedActivity.class));
                } else if(mDatabase.getRoles().equals("hrd")) {
                    startActivity(new Intent(getActivity(), JobPostActivity.class));
                }
            }
        });

        mBtnChangeRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatabase.getRoles().equals("worker")) {
                    mDatabase.setRoles("hrd");
                    mTxtLeftButton.setText("Find \nFreelance");
                    mTxtRightButton.setText("Find a Job");
                    mFindJobRoundedImgView.setImageResource(R.drawable.bg_findwork_hrd);
                } else if(mDatabase.getRoles().equals("hrd")) {
                    mDatabase.setRoles("worker");
                    mTxtLeftButton.setText("Part \nTime Job");
                    mTxtRightButton.setText("Find Worker");
                    mFindJobRoundedImgView.setImageResource(R.drawable.bg_findwork);
                }
            }
        });

        mBellNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });
    }

}
