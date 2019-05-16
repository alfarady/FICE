package id.web.runup.fice.mvp.jobpost;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.done.DoneActivity;
import id.web.runup.fice.mvp.jobdetail.IJobDetailView;
import id.web.runup.fice.mvp.jobdetail.JobDetailActivity;
import id.web.runup.fice.mvp.main.MainActivity;
import id.web.runup.fice.mvp.register.RegisterActivity;

public class JobPostActivity extends AbstractView implements IJobPostView {

    EditText mEtJobTitle, mEtJobDesc, mEtAddress, mEtDuraValue, mEtSalary;
    TextView mTxtDura;
    RadioGroup mRbGType, mRbGCategory;
    RadioButton mRbFulltime, mRbFreelance, mRbIT, mRbEducaation;
    Button mBtnHour, mBtnDay, mBtnMonth, mBtnProject, mBtnPost;
    String szDuration, szCategory, szType;
    JobPostPresenter mPresenter = new JobPostPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);

        mEtJobTitle = findViewById(R.id.etJobTitle);
        mEtJobDesc = findViewById(R.id.etJobDesc);
        mEtAddress = findViewById(R.id.etAddress);
        mEtDuraValue = findViewById(R.id.etDuraValue);
        mEtSalary = findViewById(R.id.etSalary);
        mTxtDura = findViewById(R.id.txtDura);
        mRbFulltime = findViewById(R.id.rbFulltime);
        mRbFreelance = findViewById(R.id.rbFreelance);
        mRbIT = findViewById(R.id.rbIT);
        mRbEducaation = findViewById(R.id.rbEducation);
        mBtnHour = findViewById(R.id.btnHour);
        mBtnDay = findViewById(R.id.btnDay);
        mBtnMonth = findViewById(R.id.btnMonth);
        mBtnProject = findViewById(R.id.btnProject);
        mBtnPost = findViewById(R.id.btnPost);
        mRbGType = findViewById(R.id.rbgType);
        mRbGCategory = findViewById(R.id.rbgCategory);

        this.initListener();
        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void initListener(){
        mBtnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtDura.setText("Hour(s)");
                mBtnHour.setEnabled(false);
                mBtnHour.setBackground(getDrawable(R.drawable.bg_button_red));
                mBtnDay.setEnabled(true);
                mBtnDay.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnMonth.setEnabled(true);
                mBtnMonth.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnProject.setEnabled(true);
                mBtnProject.setBackground(getDrawable(R.drawable.bg_button_disable));
            }
        });
        mBtnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtDura.setText("Day(s)");
                mBtnHour.setEnabled(true);
                mBtnHour.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnDay.setEnabled(false);
                mBtnDay.setBackground(getDrawable(R.drawable.bg_button_red));
                mBtnMonth.setEnabled(true);
                mBtnMonth.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnProject.setEnabled(true);
                mBtnProject.setBackground(getDrawable(R.drawable.bg_button_disable));
            }
        });
        mBtnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtDura.setText("Month(s)");
                mBtnHour.setEnabled(true);
                mBtnHour.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnDay.setEnabled(true);
                mBtnDay.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnMonth.setEnabled(false);
                mBtnMonth.setBackground(getDrawable(R.drawable.bg_button_red));
                mBtnProject.setEnabled(true);
                mBtnProject.setBackground(getDrawable(R.drawable.bg_button_disable));
            }
        });
        mBtnProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtDura.setText("Project(s)");
                mBtnHour.setEnabled(true);
                mBtnHour.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnDay.setEnabled(true);
                mBtnDay.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnMonth.setEnabled(true);
                mBtnMonth.setBackground(getDrawable(R.drawable.bg_button_disable));
                mBtnProject.setEnabled(false);
                mBtnProject.setBackground(getDrawable(R.drawable.bg_button_red));
            }
        });

        mRbGType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbFulltime:
                        szType = "Fulltime";
                        break;
                    case R.id.rbFreelance:
                        szType = "Freelance";
                        break;
                }
            }
        });

        mRbGCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbIT:
                        szCategory = "IT";
                        break;
                    case R.id.rbEducation:
                        szCategory = "Education";
                        break;
                }
            }
        });

        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                szDuration = mEtDuraValue.getText().toString().trim() + " " + mTxtDura.getText().toString().trim();
                mPresenter.postDataJob(mEtJobTitle.getText().toString().trim(), mEtJobDesc.getText().toString().trim(), szType, szCategory, mEtAddress.getText().toString().trim(), szDuration, mEtSalary.getText().toString().trim());
            }
        });
    }

    @Override
    public void showComplateActivity() {
        Intent tipeMasuk = new Intent(JobPostActivity.this, DoneActivity.class);
        tipeMasuk.putExtra("hrd", true);
        startActivity(tipeMasuk);
        finish();
    }

    @Override
    public void showMsg(String errorMsg) {
        showMessage(errorMsg);
    }

    @Override
    public void setFocus(String what){
        switch (what){
            case "title": mEtJobTitle.requestFocus();
                break;
            case "desc": mEtJobDesc.requestFocus();
                break;
            case "type": mRbGType.requestFocus();
                break;
            case "cat": mRbGCategory.requestFocus();
                break;
            case "address": mEtAddress.requestFocus();
                break;
            case "duraval": mEtDuraValue.requestFocus();
                break;
            case "salary": mEtSalary.requestFocus();
                break;

        }
    }
}
