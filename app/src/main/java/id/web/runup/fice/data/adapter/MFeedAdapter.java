package id.web.runup.fice.data.adapter;

public class MFeedAdapter {
    private int feedId;
    private String feedAva;
    private String feedJobName;
    private String feedJobDesc;
    private String feedHrdName;
    private String feedHrdAddress;
    private String feedSalary;

    public MFeedAdapter(int id, String ava, String jobName, String jobDesc, String hrdName, String hrdAddress, String salary)
    {
        this.feedId = id;
        this.feedAva = ava;
        this.feedJobName = jobName;
        this.feedJobDesc = jobDesc;
        this.feedHrdName = hrdName;
        this.feedHrdAddress = hrdAddress;
        this.feedSalary = salary;
    }

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getFeedAva() {
        return feedAva;
    }

    public void setFeedAva(String feedAva) {
        this.feedAva = feedAva;
    }

    public String getFeedJobName() {
        return feedJobName;
    }

    public void setFeedJobName(String feedJobName) {
        this.feedJobName = feedJobName;
    }

    public String getFeedJobDesc() {
        return feedJobDesc;
    }

    public void setFeedJobDesc(String feedJobDesc) {
        this.feedJobDesc = feedJobDesc;
    }

    public String getFeedHrdName() {
        return feedHrdName;
    }

    public void setFeedHrdName(String feedHrdName) {
        this.feedHrdName = feedHrdName;
    }

    public String getFeedHrdAddress() {
        return feedHrdAddress;
    }

    public void setFeedHrdAddress(String feedHrdAddress) {
        this.feedHrdAddress = feedHrdAddress;
    }

    public String getFeedSalary() {
        return feedSalary;
    }

    public void setFeedSalary(String feedSalary) {
        this.feedSalary = feedSalary;
    }
}
