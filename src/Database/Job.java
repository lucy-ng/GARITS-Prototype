package Database;

public class Job {
    private int jobID;
    private String description;
    private String estimatedTime;
    private String actualTime;
    private String jobStatus;

    public Job(int jobID, String description, String estimatedTime, String jobStatus) {
        this.jobID = jobID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.jobStatus = jobStatus;
    }

    public Job(int jobID, String description, String estimatedTime, String actualTime,String jobStatus) {
        this.jobID = jobID;
        this.description = description;
        this.estimatedTime = estimatedTime;
        this.actualTime = actualTime;
        this.jobStatus = jobStatus;
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

    public String getActualTime() { return actualTime; }
}
