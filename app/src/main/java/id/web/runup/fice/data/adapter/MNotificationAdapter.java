package id.web.runup.fice.data.adapter;

public class MNotificationAdapter {
    private int notifId;
    private String notifMsg;
    private String notifDate;

    public MNotificationAdapter(int notifId, String notifMsg, String notifDate)
    {
        this.notifId = notifId;
        this.notifMsg = notifMsg;
        this.notifDate = notifDate;
    }

    public int getNotifId() {
        return notifId;
    }

    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }

    public String getNotifMsg() {
        return notifMsg;
    }

    public void setNotifMsg(String notifMsg) {
        this.notifMsg = notifMsg;
    }

    public String getNotifDate() {
        return notifDate;
    }

    public void setNotifDate(String notifDate) {
        this.notifDate = notifDate;
    }
}
