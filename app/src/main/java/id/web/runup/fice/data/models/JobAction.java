package id.web.runup.fice.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobAction {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id_trx")
    @Expose
    private Integer idTrx;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIdTrx() {
        return idTrx;
    }

    public void setIdTrx(Integer idTrx) {
        this.idTrx = idTrx;
    }
}
