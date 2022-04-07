package Database;

public class Job {
    private String description;
    private String estimatedTime;
    private String jobStatus;
    private String registrationNo;

    public Job(String description, String estimatedTime, String jobStatus, String registrationNo) {
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.jobStatus = jobStatus;
        this.registrationNo = registrationNo;
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
