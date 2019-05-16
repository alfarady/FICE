package id.web.runup.fice.mvp.done;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.web.runup.fice.R;
import id.web.runup.fice.mvp.login.LoginActivity;
import id.web.runup.fice.mvp.main.MainActivity;

public class DoneActivity extends AppCompatActivity {

    TextView mDoneText1, mDoneText2;
    Button mBtnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        mDoneText1 = findViewById(R.id.doneText1);
        mDoneText2 = findViewById(R.id.doneText2);
        mBtnHome = findViewById(R.id.doneBtnHome);

        Boolean isHrd = getIntent().getBooleanExtra("hrd", false);
        if(isHrd){
            mDoneText1.setText("Your Jobs Post Submitted");
            mDoneText2.setText("Please wait for worker apply to your jobs");
        } else {
            mDoneText1.setText("Your Jobs Application Submitted");
            mDoneText2.setText("Please wait for notifications progress of your submission");
        }

        mBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoneActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
