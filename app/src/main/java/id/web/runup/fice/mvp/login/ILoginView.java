package id.web.runup.fice.mvp.login;

public interface ILoginView {
    void showMainActivity();

    void startLoading();
    void stopLoading();

    void showMsg(String errorMsg);

    void setFocus(String what);
}
