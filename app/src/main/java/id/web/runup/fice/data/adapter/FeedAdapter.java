package id.web.runup.fice.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.helpers.WebviewActivity;
import id.web.runup.fice.mvp.jobdetail.JobDetailActivity;
import id.web.runup.fice.mvp.login.LoginActivity;
import id.web.runup.fice.mvp.register.RegisterActivity;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<MFeedAdapter> mFeed;
    private Context mContext;

    public FeedAdapter(List<MFeedAdapter> feed, Context mContext) {
        this.mFeed = feed;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_feed, parent, false);
        return new FeedAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MFeedAdapter list = mFeed.get(position);

        if(!list.getFeedAva().equals("default.jpg")) {
            Glide.with(mContext)
                    .asBitmap()
                    .load(list.getFeedAva())
                    .into(holder.mFeedHrdAva);
        }
        holder.mFeedJobName.setText(list.getFeedJobName());
        holder.mFeedJobDesc.setText(list.getFeedJobDesc());
        holder.mFeedHrdName.setText(list.getFeedHrdName());
        holder.mFeedHrdAddress.setText(list.getFeedHrdAddress());
        final String tempSalary = "Rp "+String.format("%,.0f", Double.parseDouble(String.valueOf(list.getFeedSalary())));
        holder.mFeedSalary.setText(tempSalary);

        holder.mLnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tipeMasuk = new Intent(mContext, JobDetailActivity.class);
                tipeMasuk.putExtra("id_job", list.getFeedId());
                mContext.startActivity(tipeMasuk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView mFeedHrdAva;
        TextView mFeedJobName, mFeedJobDesc, mFeedHrdName, mFeedHrdAddress, mFeedSalary;
        LinearLayout mLnCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mFeedHrdAva = itemView.findViewById(R.id.feedHrdAva);
            mFeedJobName = itemView.findViewById(R.id.feedJobName);
            mFeedJobDesc = itemView.findViewById(R.id.feedJobDesc);
            mFeedHrdName = itemView.findViewById(R.id.feedHrdName);
            mFeedHrdAddress = itemView.findViewById(R.id.feedHrdAddress);
            mFeedSalary = itemView.findViewById(R.id.feedSalary);
            mLnCardView = itemView.findViewById(R.id.lnFeed);
        }
    }
}
