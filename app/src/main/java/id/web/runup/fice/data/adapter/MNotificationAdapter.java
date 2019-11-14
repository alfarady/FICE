package id.web.runup.fice.data.adapter;

public class MNotificationAdapter {
    private int notifId;
    private String notifType;
    private String notifMsg;
    private String notifDate;
    private int notifIdTrx;

    public MNotificationAdapter(int notifId, String notifType, String notifMsg, String notifDate, int notifIdTrx)
    {
        this.notifId = notifId;
        this.notifType = notifType;
        this.notifMsg = notifMsg;
        this.notifDate = notifDate;
        this.notifIdTrx = notifIdTrx;
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

    public String getNotifType() {
        return notifType;
    }

    public void setNotifType(String notifType) {
        this.notifType = notifType;
    }

    public int getNotifIdTrx() {
        return notifIdTrx;
    }

    public void setNotifIdTrx(int notifIdTrx) {
        this.notifIdTrx = notifIdTrx;
    }
}
