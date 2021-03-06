package id.web.runup.fice.data.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import id.web.runup.fice.R;
import id.web.runup.fice.data.preferences.IUserPreferences;
import id.web.runup.fice.data.preferences.UserPreferences;
import id.web.runup.fice.mvp.applicantsubmission.ApplicantSubmissionActivity;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<MNotificationAdapter> mNotif;
    private Context mContext;

    public NotificationAdapter(List<MNotificationAdapter> notif, Context mContext) {
        this.mNotif = notif;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_notif, parent, false);
        return new NotificationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final MNotificationAdapter list = mNotif.get(position);

        holder.mNotifMsg.setText(list.getNotifMsg());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datenow = new Date();
        final String now = dateFormat.format(datenow);
        final String notifDate = list.getNotifDate();
        Date datenotif = null;
        long diffday = 0;
        try {
            datenotif = dateFormat.parse(notifDate);//in milliseconds
            long diff = datenotif.getTime() - datenow.getTime();
            diffday = diff / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(diffday == 0){
            holder.mNotifDate.setText("Today");
        } else if(diffday == 1) {
            holder.mNotifDate.setText("Yesterday");
        } else {
            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM");
            String szShowDate = "";
            try {
                final Date showDate = dateFormat.parse(list.getNotifDate());
                szShowDate = dateFormat2.format(showDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.mNotifDate.setText(szShowDate);
        }

        final Boolean isReaded = holder.mDatabase.getNotifReaded(list.getNotifId());
        if(isReaded) holder.mLnNotifIsRead.setVisibility(View.GONE);
        else holder.mLnNotifIsRead.setVisibility(View.VISIBLE);

        holder.mLnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isReaded) holder.mDatabase.setNotifReaded(list.getNotifId(), true);
                if(list.getNotifType().equals("application")) {
                    Intent tipeMasuk = new Intent(mContext, ApplicantSubmissionActivity.class);
                    tipeMasuk.putExtra("id_trx", list.getNotifIdTrx());
                    mContext.startActivity(tipeMasuk);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotif.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mNotifMsg, mNotifDate;
        LinearLayout mLnNotif, mLnNotifIsRead;
        IUserPreferences mDatabase;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mDatabase = new UserPreferences();
            mNotifMsg = itemView.findViewById(R.id.notifMsg);
            mNotifDate = itemView.findViewById(R.id.notifDate);
            mLnNotif = itemView.findViewById(R.id.lnNotif);
            mLnNotifIsRead = itemView.findViewById(R.id.notifIsRead);
        }
    }
}
