package sancom.ltd.sancom.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sancom.ltd.sancom.models.ModelEducationLevel;
import sancom.ltd.sancom.models.ModelJobType;
import sancom.ltd.sancom.models.ModelJobs;
import sancom.ltd.sancom.services.EducationLevelService;
import sancom.ltd.sancom.services.JobTypeService;
import sancom.ltd.sancom.services.JobsService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "api/web/v1/")
public class JobsController {

    private JobsService jobsService;
    private JobTypeService jobTypeService;
    private EducationLevelService educationLevelService;


    public JobsController(JobsService jobsService, JobTypeService jobTypeService, EducationLevelService educationLevelService) {
        this.jobTypeService = jobTypeService;
        this.jobsService = jobsService;
        this.educationLevelService = educationLevelService;
    }


    //Jobs requests
    @PostMapping("jobs/add")
    public HashMap<String, Object> addJob(@RequestBody ModelJobs modelJobs) {

        return jobsService.addUpdateJob(modelJobs, "add");
    }


    @GetMapping("jobs/get")
    public HashMap<String, Object> getJobs() {

        return jobsService.getJobs();
    }


    @PostMapping("jobs/update")
    public HashMap<String, Object> editJob(@RequestBody ModelJobs modelJobs) {

        return jobsService.addUpdateJob(modelJobs, "update");
    }

    @GetMapping("jobs/delete")
    public HashMap<String, Object> deleteJob(long jobId) {

        return jobsService.deleteJob(jobId);
    }


    //Job type requests
    @PostMapping("jobs/addJobType")
    public HashMap<String, Object> addJobType(@RequestBody ModelJobType modelJobType) {

        return jobTypeService.addJobType(modelJobType, "add");
    }

    @GetMapping("jobs/deleteJobType")
    public HashMap<String, Object> deleteJobType(long id) {

        return jobTypeService.deleteJobType(id);
    }

    @PostMapping("jobs/editJobType")
    public HashMap<String, Object> editJobType(@RequestBody ModelJobType modelJobType) {

        return jobTypeService.addJobType(modelJobType, "update");
    }

    @GetMapping("jobs/getJobType")
    public HashMap<String, Object> getJobType() {

        return jobTypeService.getJobTypes();
    }


    //Education level requests
    @PostMapping("jobs/addEducationLevel")
    public HashMap<String, Object> addEducationLevel(@RequestBody ModelEducationLevel educationLevel) {

        return educationLevelService.addEducationLevel(educationLevel, "add");
    }

    @GetMapping("jobs/deleteEducationLevel")
    public HashMap<String, Object> deleteEducationLevel(long id) {

        return educationLevelService.deleteEducationLevel(id);
    }


    @PostMapping("jobs/editEducationLevel")
    public HashMap<String, Object> editEducationLevel(@RequestBody ModelEducationLevel educationLevel) {

        return educationLevelService.addEducationLevel(educationLevel, "update");
    }


    @GetMapping("jobs/getEducationLevel")
    public HashMap<String, Object> getEducationLevel() {

        return educationLevelService.getEducationLevel();
    }


}
