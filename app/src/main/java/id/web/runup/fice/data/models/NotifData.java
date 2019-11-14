package id.web.runup.fice.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotifData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("notif_type")
    @Expose
    private String notifType;
    @SerializedName("notif_message")
    @Expose
    private String notifMessage;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_trx")
    @Expose
    private String idTrx;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotifType() {
        return notifType;
    }

    public void setNotifType(String notifType) {
        this.notifType = notifType;
    }

    public String getNotifMessage() {
        return notifMessage;
    }

    public void setNotifMessage(String notifMessage) {
        this.notifMessage = notifMessage;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdTrx() {
        return idTrx;
    }

    public void setIdTrx(String idTrx) {
        this.idTrx = idTrx;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
