package id.web.runup.fice.mvp.feed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.data.adapter.FeedAdapter;
import id.web.runup.fice.data.adapter.MFeedAdapter;
import id.web.runup.fice.mvp.AbstractView;
import id.web.runup.fice.mvp.IPresenter;
import id.web.runup.fice.mvp.find.IFindView;

public class FeedActivity extends AbstractView implements IFeedView {

    RecyclerView mRVListFeed;
    FeedPresenter mPresenter = new FeedPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mRVListFeed = findViewById(R.id.rvFeed);

        mPresenter.onCreate(getIntent());
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    public void initRecyclerView(List<MFeedAdapter> mFeed) {
        FeedAdapter adapter = new FeedAdapter(mFeed, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FeedActivity.this);
        mRVListFeed.setLayoutManager(layoutManager);
        mRVListFeed.setAdapter(adapter);
    }
}
