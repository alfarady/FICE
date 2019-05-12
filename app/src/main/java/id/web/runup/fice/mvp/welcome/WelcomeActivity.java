package id.web.runup.fice.mvp.welcome;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import id.web.runup.fice.R;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.login.LoginActivity;

public class WelcomeActivity extends AbstractView implements IWelcomeView {

    CarouselView carouselView;
    int images[] = { R.drawable.bg_1,
            R.drawable.bg_2,
            R.drawable.bg_3};

    Button mGetStarted;
    WelcomePresenter mPresenter = new WelcomePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        carouselView = findViewById(R.id.carouselWelcome);
        carouselView.setPageCount(images.length);
        carouselView.setImageListener(imageListener);
        mGetStarted = findViewById(R.id.getStarted);

        this.initListener();
        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    private void initListener(){
        mGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(images[position]);
        }
    };

    @Override
    public void showMainActivity() {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void showMsg(String errorMsg) {
        showMessage(errorMsg);
    }
}
