package sancom.ltd.sancom.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sancom.ltd.sancom.entities.EntityEducationLevels;
import sancom.ltd.sancom.entities.JobsEntity;
import sancom.ltd.sancom.models.ModelJobs;
import sancom.ltd.sancom.repositories.EducationLevelsRepository;
import sancom.ltd.sancom.repositories.JobsRepository;
import sancom.ltd.sancom.utils.AppFunctions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static sancom.ltd.sancom.utils.AppFunctions.convertStringToTime;

@Service
public class JobsService {
    private HashMap<String, Object> response;

    private JobsRepository jobsRepository;
    private EducationLevelsRepository educationLevelsRepository;

    public JobsService(JobsRepository jobsRepository, EducationLevelsRepository educationLevelsRepository) {
        this.jobsRepository = jobsRepository;
        this.educationLevelsRepository = educationLevelsRepository;
    }


    public HashMap<String, Object> addUpdateJob(ModelJobs modelJobs, String type) {
        response = new HashMap<>();

        if (type.equalsIgnoreCase("add")) {
            JobsEntity jobsEntity = new JobsEntity();
            jobsEntity.setDescription(modelJobs.getDescription());
            jobsEntity.setCreatedOn(new Date());
            jobsEntity.setEducation_level(Long.parseLong(modelJobs.getEducation_level()));
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


        JobsEntity jobsEntityToEdit = jobsRepository.findDistinctById(1);

        if (jobsEntityToEdit == null) {
            response.put("status", "01");
            response.put("message", "Jon could not be found");
            return response;
        }

        jobsEntityToEdit.setDescription(modelJobs.getDescription());
        jobsEntityToEdit.setEducation_level(Long.parseLong(modelJobs.getEducation_level()));
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

        List<ModelJobs> modelJobs = new ArrayList<>();

        List<JobsEntity> jobsEntity = jobsRepository.findAll();

        for (JobsEntity singleJob : jobsEntity)
        {
            ModelJobs job = new ModelJobs();

            EntityEducationLevels entityEducationLevels = educationLevelsRepository.findDistinctById(singleJob.getEducation_level());

            job.setName(singleJob.getName());
            job.setDescription(singleJob.getDescription());
            job.setStatus(singleJob.getStatus());
            job.setEducation_level(entityEducationLevels.getName());
            job.setYears_of_experience(singleJob.getYears_of_experience());
            job.setInterview_date(singleJob.getInterview_date().toString());
            job.setInterview_start_time(singleJob.getInterview_start_time().toString());
            job.setInterview_end_time(singleJob.getInterview_end_time().toString());
            job.setType(singleJob.getType());
//            job.setId(singleJob.getId());
            modelJobs.add(job);
        }


        response = new HashMap<>();
        response.put("data", modelJobs);
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
