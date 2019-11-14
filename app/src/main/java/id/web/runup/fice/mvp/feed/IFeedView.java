package id.web.runup.fice.mvp.feed;

import java.util.List;

import id.web.runup.fice.data.adapter.MFeedAdapter;

public interface IFeedView {
    void initRecyclerView(List<MFeedAdapter> mFeed);
    void startWelcomeActivity();
}
