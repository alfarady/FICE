package id.web.runup.fice.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Job {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kategori")
    @Expose
    private String idKategori;
    @SerializedName("id_tipe")
    @Expose
    private String idTipe;
    @SerializedName("id_hrd")
    @Expose
    private String idHrd;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("gaji")
    @Expose
    private String gaji;
    @SerializedName("durasi")
    @Expose
    private String durasi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("hrd_name")
    @Expose
    private String hrdName;
    @SerializedName("hrd_ava")
    @Expose
    private String hrdAva;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getIdTipe() {
        return idTipe;
    }

    public void setIdTipe(String idTipe) {
        this.idTipe = idTipe;
    }

    public String getIdHrd() {
        return idHrd;
    }

    public void setIdHrd(String idHrd) {
        this.idHrd = idHrd;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
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

    public String getHrdName() {
        return hrdName;
    }

    public void setHrdName(String hrdName) {
        this.hrdName = hrdName;
    }

    public String getHrdAva() {
        return hrdAva;
    }

    public void setHrdAva(String hrdAva) {
        this.hrdAva = hrdAva;
    }
}
