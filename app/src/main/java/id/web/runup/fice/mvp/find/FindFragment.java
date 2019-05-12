package id.web.runup.fice.mvp.find;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import static android.support.v4.content.ContextCompat.getSystemService;

public class FindFragment extends Fragment {

    TabHost host;
    RecyclerView mRVListFind;
    List<MFeedAdapter> mFeed;
    EditText mSearchBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find, null);

        mSearchBar = v.findViewById(R.id.textView12);

        this.initTabHost(v);
        this.initRecyclerView(v);
        this.initListener();

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
        spec = host.newTabSpec("Most\nViewed");
        spec.setContent(R.id.tabMostViewed);
        spec.setIndicator("Most\nViewed");
        host.addTab(spec);

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

    private void initRecyclerView(View v){
        mRVListFind = v.findViewById(R.id.rvFind);
        mFeed = new ArrayList<>();
        mFeed.add(new MFeedAdapter(1, "https://scontent-sin6-1.xx.fbcdn.net/v/t1.0-9/43052446_904401413102483_6865659330377547776_n.jpg?_nc_cat=108&_nc_ht=scontent-sin6-1.xx&oh=2eb07746d29c763885062002e80f4385&oe=5D6FF979", "Fullstack Programming", "We are hiring fullstack programming with JS Expert", "Bambank", "Jl Klapa Gading No.140 Jakarta Selatan", "10000000"));
        mFeed.add(new MFeedAdapter(2, "https://scontent-sin6-1.xx.fbcdn.net/v/t1.0-9/24301188_1519056541534532_7986894186340007147_n.jpg?_nc_cat=106&_nc_ht=scontent-sin6-1.xx&oh=2311d96e8e8e76e1e31ebe48325c217f&oe=5D707DC6", "UI Designer", "We are hiring UI designer who familiar with Adobe XD", "Sujatmiko", "Jl Klapa Gading No.140 Jakarta Selatan", "5000000"));
        mFeed.add(new MFeedAdapter(3, "https://scontent-sin6-1.cdninstagram.com/vp/3a4d497f775252a2d8004796ba2201ef/5D5B3C29/t51.2885-19/s150x150/51172784_802392790127874_373482165557526528_n.jpg?_nc_ht=scontent-sin6-1.cdninstagram.com&_nc_cat=106", "Data Scientis", "We are hiring Data Scientis with more than 5 years experience", "Sunary", "Jl Klapa Gading No.140 Jakarta Selatan", "3000000"));

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
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getActivity(), mSearchBar.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}
