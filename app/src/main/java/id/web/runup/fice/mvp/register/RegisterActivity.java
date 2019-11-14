package id.web.runup.fice.mvp.register;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import dmax.dialog.SpotsDialog;
import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.login.LoginActivity;
import id.web.runup.fice.mvp.main.MainActivity;

public class RegisterActivity extends AbstractView implements IRegisterView {

    RegisterPresenter mPresenter = new RegisterPresenter(this);
    EditText mRegisterEmail, mRegisterPassword, mRegisterMsisdn, mRegisterFname, mRegisterAge, mRegisterCountry;
    Button mRegister;
    TextView mRegisterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterEmail = findViewById(R.id.register_email);
        mRegisterPassword = findViewById(R.id.register_password);
        mRegisterMsisdn = findViewById(R.id.register_msisdn);
        mRegisterFname = findViewById(R.id.register_fname);
        mRegisterAge = findViewById(R.id.register_age);
        mRegisterCountry = findViewById(R.id.register_country);
        mRegister = findViewById(R.id.btnRegister);
        mRegisterLogin = findViewById(R.id.register_login);

        this.initListener();
        mPresenter.onCreate(getIntent());
    }

    private void initListener(){
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doRegister(mRegisterEmail.getText().toString().trim(), mRegisterPassword.getText().toString().trim(), mRegisterMsisdn.getText().toString().trim(),
                        mRegisterFname.getText().toString().trim(), mRegisterAge.getText().toString().trim(), mRegisterCountry.getText().toString().trim());
            }
        });
        mRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void showMainActivity() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showMsg(String errorMsg) {
        showMessage(errorMsg);
    }

    @Override
    public void setFocus(String what){
        switch (what){
            case "email": mRegisterEmail.requestFocus();
                        break;
            case "password": mRegisterPassword.requestFocus();
                        break;
            case "msisdn": mRegisterMsisdn.requestFocus();
                break;
            case "age": mRegisterAge.requestFocus();
                break;

        }
    }
}
