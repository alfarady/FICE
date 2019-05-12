package id.web.runup.fice.mvp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;

import id.web.runup.fice.mvp.main.MainActivity;
import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;

public class SplashActivity extends AbstractView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishSplash();
            }
        }, 1000);
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void finishSplash()
    {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
