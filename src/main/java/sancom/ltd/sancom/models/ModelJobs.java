package sancom.ltd.sancom.models;

public class ModelJobs {
    private long id;
    private String name;
    private String description;
    private String type;
    private String status;
    private String interview_start_time;
    private String interview_end_time;
    private String years_of_experience;
    private long education_level;
    private String interview_date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInterview_start_time() {
        return interview_start_time;
    }

    public void setInterview_start_time(String interview_start_time) {
        this.interview_start_time = interview_start_time;
    }

    public String getInterview_end_time() {
        return interview_end_time;
    }

    public void setInterview_end_time(String interview_end_time) {
        this.interview_end_time = interview_end_time;
    }

    public String getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(String years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public long getEducation_level() {
        return education_level;
    }

    public void setEducation_level(long education_level) {
        this.education_level = education_level;
    }

    public String getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(String interview_date) {
        this.interview_date = interview_date;
    }
}
