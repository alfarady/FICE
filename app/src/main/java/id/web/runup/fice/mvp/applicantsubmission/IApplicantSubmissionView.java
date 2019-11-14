package id.web.runup.fice.mvp.applicantsubmission;

public interface IApplicantSubmissionView {
    void startWelcomeActivity();
    void showComplateActivity(String what);
    void showMsg(String msg);
    void startLoading();
    void stopLoading();
    void initDataAS(String name, String email, String msisdn, String age, String address, String cv, String ava_url, String cv_url, String progress);
}
