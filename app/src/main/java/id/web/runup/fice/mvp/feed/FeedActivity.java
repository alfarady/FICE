package id.web.runup.fice.mvp.feed;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.data.adapter.FeedAdapter;
import id.web.runup.fice.data.adapter.MFeedAdapter;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.find.IFindView;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;

public class FeedActivity extends AbstractView implements IFeedView {

    RecyclerView mRVListFeed;
    FeedPresenter mPresenter = new FeedPresenter(this);
    SwipeRefreshLayout refreshFeed;
    ProgressBar mProgressBar;
    TextView mTxtNewest, mTxtNearby, mTxtMostViewed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mRVListFeed = findViewById(R.id.rvFeed);
        mProgressBar = findViewById(R.id.feedProgressBar);
        refreshFeed = findViewById(R.id.refreshFeed);
        mTxtNewest = findViewById(R.id.txtNewest);
        mTxtNearby = findViewById(R.id.txtNearby);
        mTxtMostViewed = findViewById(R.id.txtMostViewed);

        refreshFeed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setFeedData();
            }
        });

        mTxtNewest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setFeedData();
            }
        });

        mTxtNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setFeedDataNear();
            }
        });

        mTxtMostViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mPresenter.setFeedDataMostView();
            }
        });

        ImageView mBack = findViewById(R.id.btnBack);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void initRecyclerView(List<MFeedAdapter> mFeed) {
        refreshFeed.setRefreshing(false);
        mProgressBar.setVisibility(View.GONE);
        FeedAdapter adapter = new FeedAdapter(mFeed, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FeedActivity.this);
        mRVListFeed.setLayoutManager(layoutManager);
        mRVListFeed.setAdapter(adapter);
    }

    @Override
    public void startWelcomeActivity() {
        startActivity(new Intent(FeedActivity.this, WelcomeActivity.class));
        finish();
    }
}
