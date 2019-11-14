package id.web.runup.fice.mvp.home;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import id.web.runup.fice.R;
import id.web.runup.fice.data.preferences.IUserPreferences;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.helpers.WebviewActivity;
import id.web.runup.fice.mvp.applicantsubmission.ApplicantSubmissionActivity;
import id.web.runup.fice.mvp.done.DoneActivity;
import id.web.runup.fice.mvp.feed.FeedActivity;
import id.web.runup.fice.mvp.jobdetail.JobDetailActivity;
import id.web.runup.fice.mvp.jobpost.JobPostActivity;
import id.web.runup.fice.mvp.notification.NotificationActivity;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;

public class HomeView extends Fragment implements IHomeView {

    RoundedImageView mFindJobRoundedImgView, mPartTimeJob, mCatEdu, mCatIT, mNews, mArticle, mImgDp;
    TextView mTxtLeftButton, mTxtRightButton, mGreetingName;
    Button mBtnChangeRoles;
    IUserPreferences mDatabase;
    LinearLayout mBellNotif;
    ProgressBar mProgressBar;
    HomePresenter mPresenter = new HomePresenter(this);
    SwipeRefreshLayout refreshBeranda;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        mDatabase = new UserPreferences();

        mFindJobRoundedImgView = v.findViewById(R.id.findJobRoundedImgView);
        mPartTimeJob = v.findViewById(R.id.partTimeRoundedImgView);
        mCatEdu = v.findViewById(R.id.catEduRoundedImgView);
        mCatIT = v.findViewById(R.id.catITRoundedImgView);
        mNews = v.findViewById(R.id.newsRoundedImgView);
        mArticle = v.findViewById(R.id.gridArticleRoundedImgView);
        mBtnChangeRoles = v.findViewById(R.id.btnChangeRoles);
        mTxtLeftButton = v.findViewById(R.id.txtLeftButton);
        mTxtRightButton = v.findViewById(R.id.txtRightButton);
        mBellNotif = v.findViewById(R.id.bellNotif);
        mGreetingName = v.findViewById(R.id.greeting_name);
        mImgDp = v.findViewById(R.id.homeDp);
        mProgressBar = v.findViewById(R.id.homeProgressBar);
        refreshBeranda = v.findViewById(R.id.refreshHome);

        this.initListener(v);
        mPresenter.onCreate(getActivity().getIntent());
        return v;
    }

    private void initListener(View v){
        if(mDatabase.getRoles().equals("worker")) {
            mTxtLeftButton.setText("Be Mentor");
            mTxtRightButton.setText("Be Teacher");
            mFindJobRoundedImgView.setImageResource(R.drawable.bg_findwork);
        } else if(mDatabase.getRoles().equals("hrd")) {
            mTxtLeftButton.setText("Find Mentor");
            mTxtRightButton.setText("Find Teacher");
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
                    mTxtLeftButton.setText("Find Mentor");
                    mTxtRightButton.setText("Find Teacher");
                    mFindJobRoundedImgView.setImageResource(R.drawable.bg_findwork_hrd);
                } else if(mDatabase.getRoles().equals("hrd")) {
                    mDatabase.setRoles("worker");
                    mTxtLeftButton.setText("Be Mentor");
                    mTxtRightButton.setText("Be Teacher");
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

        mCatEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeMasuk = new Intent(getContext(), FeedActivity.class);
                tipeMasuk.putExtra("cat", 2);
                startActivity(tipeMasuk);
            }
        });

        mCatIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeMasuk = new Intent(getContext(), FeedActivity.class);
                tipeMasuk.putExtra("cat", 1);
                startActivity(tipeMasuk);
            }
        });

        mPartTimeJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDatabase.getRoles().equals("worker")) {
                    Intent tipeMasuk = new Intent(getContext(), FeedActivity.class);
                    tipeMasuk.putExtra("type", 2);
                    startActivity(tipeMasuk);
                } else if(mDatabase.getRoles().equals("hrd")) {
                    startActivity(new Intent(getActivity(), JobPostActivity.class));
                }
            }
        });

        mNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeMasuk = new Intent(getActivity(), WebviewActivity.class);
                tipeMasuk.putExtra("url", "https://m.liputan6.com/latest");
                startActivity(tipeMasuk);
            }
        });

        mArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeMasuk = new Intent(getActivity(), WebviewActivity.class);
                tipeMasuk.putExtra("url", "https://m.liputan6.com/tag/pekerjaan");
                startActivity(tipeMasuk);
            }
        });

        refreshBeranda.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setDataHome();
            }
        });
    }

    @Override
    public void initDataHome(String greeting_name, String ava_url){
        mProgressBar.setVisibility(View.GONE);
        refreshBeranda.setRefreshing(false);
        final String firstName;
        if(greeting_name.contains(" ")){
            String temp[] = greeting_name.split(" ");
            firstName = temp[0];
        } else {
            firstName = greeting_name;
        }

        mGreetingName.setText(firstName);
        if(!ava_url.equals("default.jpg")) {
            Glide.with(getContext())
                    .asBitmap()
                    .load(ava_url)
                    .into(mImgDp);
        }
    }

    @Override
    public void startWelcomeActivity() {
        startActivity(new Intent(getActivity(), WelcomeActivity.class));
        getActivity().finish();
    }
}
