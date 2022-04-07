package Database;

public class Job {
    private int jobID;
    private String description;
    private String estimatedTime;
    private String jobStatus;
    private String registrationNo;

    public Job(int jobID, String description, String estimatedTime, String jobStatus, String registrationNo) {
        this.jobID = jobID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.jobStatus = jobStatus;
        this.registrationNo = registrationNo;
    }

    public int getJobID() {
        return jobID;
    }

    public String getDescription() {
        return description;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }
}
