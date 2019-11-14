package id.web.runup.fice.mvp.profile;

public interface IProfileView {
    void showMsg(String errorMsg);

    void setFocus(String what);

    void setDone();

    void startWelcomeActivity();

    void stopProgressBar();

    void initDataProfile(String email, String password, String msisdn, String fname, String age, String country, String ava_ulr);
}
