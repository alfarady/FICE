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

public class FeedActivity extends AppCompatActivity {

    RecyclerView mRVListFeed;
    List<MFeedAdapter> mFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mRVListFeed = findViewById(R.id.rvFeed);
        mFeed = new ArrayList<>();
        mFeed.add(new MFeedAdapter(1, "https://scontent-sin6-1.xx.fbcdn.net/v/t1.0-9/43052446_904401413102483_6865659330377547776_n.jpg?_nc_cat=108&_nc_ht=scontent-sin6-1.xx&oh=2eb07746d29c763885062002e80f4385&oe=5D6FF979", "Fullstack Programming", "We are hiring fullstack programming with JS Expert", "Bambank", "Jl Klapa Gading No.140 Jakarta Selatan", "10000000"));
        mFeed.add(new MFeedAdapter(2, "https://scontent-sin6-1.xx.fbcdn.net/v/t1.0-9/24301188_1519056541534532_7986894186340007147_n.jpg?_nc_cat=106&_nc_ht=scontent-sin6-1.xx&oh=2311d96e8e8e76e1e31ebe48325c217f&oe=5D707DC6", "UI Designer", "We are hiring UI designer who familiar with Adobe XD", "Sujatmiko", "Jl Klapa Gading No.140 Jakarta Selatan", "5000000"));
        mFeed.add(new MFeedAdapter(3, "https://scontent-sin6-1.cdninstagram.com/vp/3a4d497f775252a2d8004796ba2201ef/5D5B3C29/t51.2885-19/s150x150/51172784_802392790127874_373482165557526528_n.jpg?_nc_ht=scontent-sin6-1.cdninstagram.com&_nc_cat=106", "Data Scientis", "We are hiring Data Scientis with more than 5 years experience", "Sunary", "Jl Klapa Gading No.140 Jakarta Selatan", "3000000"));

        FeedAdapter adapter = new FeedAdapter(mFeed, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FeedActivity.this);
        mRVListFeed.setLayoutManager(layoutManager);
        mRVListFeed.setAdapter(adapter);
    }
}
