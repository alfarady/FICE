package id.web.runup.fice.mvp.jobdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.done.DoneActivity;
import id.web.runup.fice.mvp.jobpost.JobPostActivity;

public class JobDetailActivity extends AbstractView implements IJobDetailView {
    JobDetailPresenter mPresenter = new JobDetailPresenter(this);
    TextView mJobDetailName, mJobDetailJobName, mJobDetailJobDesc, mJobDetailJobType, mJobDetailJobCat, mJobDetailJobSal, mJobDetailJobAddress;
    Button mBtnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        mJobDetailName = findViewById(R.id.jobDetailName);
        mJobDetailJobName = findViewById(R.id.jobDetailJobName);
        mJobDetailJobDesc = findViewById(R.id.jobDetailJobDesc);
        mJobDetailJobType = findViewById(R.id.jobDetailJobType);
        mJobDetailJobCat = findViewById(R.id.jobDetailJobCat);
        mJobDetailJobSal = findViewById(R.id.jobDetailJobSal);
        mJobDetailJobAddress = findViewById(R.id.jobDetailJobAddress);
        mBtnApply = findViewById(R.id.btnApply);

        this.initListener();
        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void initListener(){
        mBtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showComplateActivity();
            }
        });
    }

    @Override
    public void showComplateActivity() {
        Intent tipeMasuk = new Intent(JobDetailActivity.this, DoneActivity.class);
        tipeMasuk.putExtra("hrd", false);
        startActivity(tipeMasuk);
        finish();
    }

    @Override
    public void initDataJobDetail(String jobseeker_name, String job_name, String job_desc, String job_type, String job_cat, String job_sal, String job_address) {
        mJobDetailName.setText(jobseeker_name);
        mJobDetailJobName.setText(job_name);
        mJobDetailJobDesc.setText(job_desc);
        mJobDetailJobType.setText(job_type);
        mJobDetailJobCat.setText(job_cat);
        mJobDetailJobSal.setText(job_sal);
        mJobDetailJobAddress.setText(job_address);
    }
}
