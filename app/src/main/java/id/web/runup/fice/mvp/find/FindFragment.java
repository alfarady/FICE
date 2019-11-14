package id.web.runup.fice.mvp.find;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.data.adapter.FeedAdapter;
import id.web.runup.fice.data.adapter.MFeedAdapter;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.feed.FeedActivity;
import id.web.runup.fice.mvp.welcome.WelcomeActivity;

import static android.support.v4.content.ContextCompat.getSystemService;

public class FindFragment extends Fragment implements IFindView{

    TabHost host;
    RecyclerView mRVListFind;
    EditText mSearchBar;
    FindPresenter mPresenter = new FindPresenter(this);
    SwipeRefreshLayout refreshFeed;
    ProgressBar mProgressBar;
    int imOn = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find, null);

        mSearchBar = v.findViewById(R.id.textView12);
        mRVListFind = v.findViewById(R.id.rvFind);

        mProgressBar = v.findViewById(R.id.findProgressBar);
        refreshFeed = v.findViewById(R.id.refreshFind);

        this.initTabHost(v);
        this.initListener();

        mPresenter.onCreate(getActivity().getIntent());
        return v;
    }

    private void initTabHost(View v){
        host = v.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Newest");
        spec.setContent(R.id.tabNewest);
        spec.setIndicator("Newest");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Nearby");
        spec.setContent(R.id.tabNearby);
        spec.setIndicator("Nearby");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("MostViewed");
        spec.setContent(R.id.tabMostViewed);
        spec.setIndicator("Most\nViewed");
        host.addTab(spec);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                if ("Newest".equals(tabId)) {
                    imOn = 1;
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPresenter.setFeedData();
                } else if ("Nearby".equals(tabId)) {
                    imOn = 2;
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPresenter.setFeedDataNear();
                } else if ("MostViewed".equals(tabId)) {
                    imOn = 3;
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPresenter.setFeedDataMostView();
                }
            }
        });

        refreshFeed.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mProgressBar.setVisibility(View.VISIBLE);
                if(imOn==1)
                    mPresenter.setFeedData();
                else if(imOn==2)
                    mPresenter.setFeedDataNear();
                else if(imOn==3)
                    mPresenter.setFeedDataMostView();
            }
        });

        int tabCount = host.getTabWidget().getTabCount();
        for (int i = 0; i < tabCount; i++) {
            final View view = host.getTabWidget().getChildTabViewAt(i);
            if ( view != null ) {
                // reduce height of the tab
                view.getLayoutParams().height *= 0.66;

                //  get title text view
                final View textView = view.findViewById(android.R.id.title);
                if ( textView instanceof TextView ) {
                    // just in case check the type

                    // center text
                    ((TextView) textView).setGravity(Gravity.CENTER);
                    // wrap text
                    ((TextView) textView).setSingleLine(false);

                    // explicitly set layout parameters
                    textView.getLayoutParams().height = ViewGroup.LayoutParams.FILL_PARENT;
                    textView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
            }
        }
    }

    @Override
    public void initRecyclerView(List<MFeedAdapter> mFeed){
        refreshFeed.setRefreshing(false);
        mProgressBar.setVisibility(View.GONE);
        FeedAdapter adapter = new FeedAdapter(mFeed, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRVListFind.setLayoutManager(layoutManager);
        mRVListFind.setAdapter(adapter);
    }

    private void initListener(){
        mSearchBar.setFocusableInTouchMode(true);
        mSearchBar.requestFocus();
        mSearchBar.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER) && mSearchBar.getText().length() >= 3) {
                    // Perform action on key press
                    mProgressBar.setVisibility(View.VISIBLE);
                    if(imOn==1)
                        mPresenter.searchFeedData(mSearchBar.getText().toString());
                    else if(imOn==2)
                        mPresenter.searchFeedDataNear(mSearchBar.getText().toString());
                    else if(imOn==3)
                        mPresenter.searchFeedDataMostView(mSearchBar.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void startWelcomeActivity() {
        startActivity(new Intent(getActivity(), WelcomeActivity.class));
        getActivity().finish();
    }
}
