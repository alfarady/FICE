package id.web.runup.fice.mvp.jobpost;

public interface IJobPostView {
    void showComplateActivity();

    void startLoading();

    void stopLoading();

    void showMsg(String errorMsg);

    void setFocus(String what);
}
