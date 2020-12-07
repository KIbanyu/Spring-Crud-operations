package sancom.ltd.sancom.models;

public class ModelCandidates {
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private long education_level;
    private int years_of_experience;
    private long id;
    private long selected_job;

    public long getSelected_job() {
        return selected_job;
    }

    public void setSelected_job(long selected_job) {
        this.selected_job = selected_job;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getEducation_level() {
        return education_level;
    }

    public void setEducation_level(long education_level) {
        this.education_level = education_level;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }
}
