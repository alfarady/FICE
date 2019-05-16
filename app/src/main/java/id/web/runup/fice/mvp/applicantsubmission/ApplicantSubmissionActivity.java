package id.web.runup.fice.mvp.applicantsubmission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import id.web.runup.fice.R;
import id.web.runup.fice.helpers.WebviewActivity;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;

public class ApplicantSubmissionActivity extends AbstractView implements IApplicantSubmissionView {

    TextView mAsJobAppName, mAsvEmail, mAsvMsisdn, mAsvAge, mAsvAddress, mAsvCV;
    RoundedImageView mRoundedImageView;
    ApplicantSubmissionPresenter mPresenter = new ApplicantSubmissionPresenter(this);

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

        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void initDataAS(String name, String email, String msisdn, String age, String address, String cv, String ava_url, final String cv_url) {
        Glide.with(this)
                .asBitmap()
                .load(ava_url)
                .into(mRoundedImageView);
        mAsJobAppName.setText(name);
        mAsvEmail.setText(email);
        mAsvMsisdn.setText(msisdn);
        mAsvAge.setText(age);
        mAsvAddress.setText(address);
        mAsvCV.setText(cv);
        mAsvCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeMasuk = new Intent(ApplicantSubmissionActivity.this, WebviewActivity.class);
                tipeMasuk.putExtra("url", cv_url);
                startActivity(tipeMasuk);
            }
        });
    }
}
