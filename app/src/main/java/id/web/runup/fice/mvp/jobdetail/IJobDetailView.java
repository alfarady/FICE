package id.web.runup.fice.mvp.jobdetail;

public interface IJobDetailView {
    void showComplateActivity();

    void initDataJobDetail(String jobseeker_name, String job_name, String job_desc, String job_type, String job_cat, String job_sal, String job_address);
}
