package id.web.runup.fice.mvp.applicantsubmission;

import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import id.web.runup.fice.R;
import id.web.runup.fice.helpers.WebviewActivity;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.done.DoneActivity;
import id.web.runup.fice.mvp.jobdetail.JobDetailActivity;
import id.web.runup.fice.mvp.jobpost.JobPostActivity;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;

public class ApplicantSubmissionActivity extends AbstractView implements IApplicantSubmissionView {

    TextView mAsJobAppName, mAsvEmail, mAsvMsisdn, mAsvAge, mAsvAddress, mAsvCV;
    RoundedImageView mRoundedImageView;
    ApplicantSubmissionPresenter mPresenter = new ApplicantSubmissionPresenter(this);
    SwipeRefreshLayout refreshFeed;
    ProgressBar mProgressBar;
    Button mApprove, mReject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_submission);

        mRoundedImageView = findViewById(R.id.roundedImageView);
        mAsJobAppName = findViewById(R.id.asJobAppName);
        mAsvEmail = findViewById(R.id.asvEmail);
        mAsvMsisdn = findViewById(R.id.asvMsisdn);
        mAsvAge = findViewById(R.id.asvAge);
        mAsvAddress = findViewById(R.id.asvAddress);
        mAsvCV = findViewById(R.id.asvCV);
        mProgressBar = findViewById(R.id.asProgressBar);
        refreshFeed = findViewById(R.id.refreshAS);
        mApprove = findViewById(R.id.approve);
        mReject = findViewById(R.id.reject);

        final int id_trx = getIntent().getIntExtra("id_trx", 1);

        refreshFeed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setData(id_trx);
            }
        });

        ImageView mBack = findViewById(R.id.btnBack);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.approve(id_trx);
            }
        });
        mReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.reject(id_trx);
            }
        });


        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void startWelcomeActivity() {
        startActivity(new Intent(ApplicantSubmissionActivity.this, WelcomeActivity.class));
        finish();
    }

    @Override
    public void showComplateActivity(String what) {
        Intent tipeMasuk = new Intent(ApplicantSubmissionActivity.this, DoneActivity.class);
        tipeMasuk.putExtra("isHrd", what);
        startActivity(tipeMasuk);
        finish();
    }

    @Override
    public void showMsg(String msg) {
        showMessage(msg);
    }

    @Override
    public void initDataAS(String name, String email, String msisdn, String age, String address, String cv, String ava_url, final String cv_url, String progress) {
        mProgressBar.setVisibility(View.GONE);
        refreshFeed.setRefreshing(false);
        if(progress.equals("approved") || progress.equals("rejected")) {
            mApprove.setVisibility(View.GONE);
            mReject.setVisibility(View.GONE);
        } else if(progress.equals("pending")){
            mApprove.setVisibility(View.VISIBLE);
            mReject.setVisibility(View.VISIBLE);
        }
        if(!ava_url.equals("default.jpg")) {
            Glide.with(this)
                    .asBitmap()
                    .load(ava_url)
                    .into(mRoundedImageView);
        }
        mAsJobAppName.setText(name);
        mAsvEmail.setText(email);
        mAsvMsisdn.setText(msisdn);
        mAsvAge.setText(age);
        mAsvAddress.setText(address);
        mAsvCV.setText(cv);
        mAsvCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(ApplicantSubmissionActivity.this, Uri.parse(cv_url));
            }
        });
    }
}
