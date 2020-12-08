package sancom.ltd.sancom.models;

import java.sql.Time;
import java.util.Date;

public class JobApplications {
    private String name;
    private String description;;
    private String status;
    private Time interview_start_time;
    private Time interview_end_time;
    private String interview_date;

    public String getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(String interview_date) {
        this.interview_date = interview_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Time getInterview_start_time() {
        return interview_start_time;
    }

    public void setInterview_start_time(Time interview_start_time) {
        this.interview_start_time = interview_start_time;
    }

    public Time getInterview_end_time() {
        return interview_end_time;
    }

    public void setInterview_end_time(Time interview_end_time) {
        this.interview_end_time = interview_end_time;
    }


}
