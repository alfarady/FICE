package id.web.runup.fice.mvp.profile;

public interface IProfileView {
    void showMsg(String errorMsg);

    void setFocus(String what);

    void setDone();
}
