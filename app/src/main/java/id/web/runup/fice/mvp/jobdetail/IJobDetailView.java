package id.web.runup.fice.mvp.jobdetail;

public interface IJobDetailView {
    void showComplateActivity();

    void startLoading();

    void stopLoading();

    void showMsg(String errorMsg);

    void startWelcomeActivity();

    void initDataJobDetail(String jobseeker_name, String job_name, String job_desc, String job_type, String job_cat, String job_sal, String job_address, String ava_url);
}
