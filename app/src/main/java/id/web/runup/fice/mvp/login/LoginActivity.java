package id.web.runup.fice.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.main.MainActivity;
import id.web.runup.fice.mvp.register.RegisterActivity;

public class LoginActivity extends AbstractView implements ILoginView {

    LoginPresenter mPresenter = new LoginPresenter(this);
    EditText mLoginEmail, mLoginPassword;
    TextView mLoginRegister;
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        mLogin = findViewById(R.id.btnLogin);
        mLoginRegister = findViewById(R.id.login_register);

        this.initListener();
        mPresenter.onCreate(getIntent());
    }

    private void initListener(){
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainActivity();
            }
        });
        mLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showMsg(String errorMsg) {
        showMessage(errorMsg);
    }
}
