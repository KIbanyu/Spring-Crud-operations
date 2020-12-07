package sancom.ltd.sancom.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sancom.ltd.sancom.entities.JobsEntity;
import sancom.ltd.sancom.models.ModelJobs;
import sancom.ltd.sancom.repositories.JobsRepository;
import sancom.ltd.sancom.utils.AppFunctions;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import static sancom.ltd.sancom.utils.AppFunctions.convertStringToTime;

@Service
public class JobsService {
    private HashMap<String, Object> response;

    private JobsRepository jobsRepository;

    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }


    public HashMap<String, Object> addUpdateJob(ModelJobs modelJobs, String type) {
        response = new HashMap<>();

        if (type.equalsIgnoreCase("add")) {
            JobsEntity jobsEntity = new JobsEntity();
            jobsEntity.setDescription(modelJobs.getDescription());
            jobsEntity.setCreatedOn(new Date());
            jobsEntity.setEducation_level(modelJobs.getEducation_level());
            jobsEntity.setInterview_date(AppFunctions.convertToDate(modelJobs.getInterview_date()));
            try {
                jobsEntity.setInterview_end_time(convertStringToTime(modelJobs.getInterview_end_time()));
                jobsEntity.setInterview_start_time(convertStringToTime(modelJobs.getInterview_start_time()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            jobsEntity.setName(modelJobs.getName());
            jobsEntity.setStatus(modelJobs.getStatus());
            jobsEntity.setType(modelJobs.getType());
            jobsEntity.setYears_of_experience(modelJobs.getYears_of_experience());

            jobsRepository.save(jobsEntity);
            response.put("status", "00");
            response.put("message", "Job added successfully");
            return response;
        }


        JobsEntity jobsEntityToEdit = jobsRepository.findDistinctById(modelJobs.getId());

        if (jobsEntityToEdit == null) {
            response.put("status", "01");
            response.put("message", "Jon could not be found");
            return response;
        }

        jobsEntityToEdit.setDescription(modelJobs.getDescription());
        jobsEntityToEdit.setEducation_level(modelJobs.getEducation_level());
        jobsEntityToEdit.setInterview_date(AppFunctions.convertToDate(modelJobs.getInterview_date()));
        try {
            jobsEntityToEdit.setInterview_end_time(convertStringToTime(modelJobs.getInterview_end_time()));
            jobsEntityToEdit.setInterview_start_time(convertStringToTime(modelJobs.getInterview_start_time()));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        jobsEntityToEdit.setName(modelJobs.getName());
        jobsEntityToEdit.setStatus(modelJobs.getStatus());
        jobsEntityToEdit.setType(modelJobs.getType());
        jobsEntityToEdit.setYears_of_experience(modelJobs.getYears_of_experience());

        jobsRepository.save(jobsEntityToEdit);

        response.put("status", "00");
        response.put("message", "Job updated successfully");

        return response;
    }


    public HashMap<String, Object> getJobs() {

        response = new HashMap<>();
        response.put("data", jobsRepository.findAll());
        response.put("status", "00");
        response.put("message", "Success");

        return response;
    }

    public HashMap<String, Object> deleteJob(long jobId) {

        JobsEntity jobsEntity = jobsRepository.findDistinctById(jobId);
        response = new HashMap<>();

        if (jobsEntity == null) {
            response.put("status", "01");
            response.put("message", "Job could not be found");
            return response;
        }

        jobsRepository.delete(jobsEntity);
        response.put("status", "00");
        response.put("message", "Job successfully deleted");

        return response;
    }
}
