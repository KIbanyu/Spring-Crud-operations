package sancom.ltd.sancom.services;


import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import sancom.ltd.sancom.entities.CandidatesEntity;
import sancom.ltd.sancom.entities.JobsEntity;
import sancom.ltd.sancom.models.GetMyApplications;
import sancom.ltd.sancom.models.JobApplications;
import sancom.ltd.sancom.models.ModelCandidates;
import sancom.ltd.sancom.repositories.CandidatesRepository;
import sancom.ltd.sancom.repositories.JobsRepository;
import sancom.ltd.sancom.utils.AppFunctions;

import java.util.*;

@Service
public class CandidateService {
    private HashMap<String, Object> response;
    private CandidatesRepository candidatesRepository;
    private JobsRepository jobsRepository;

    public CandidateService(CandidatesRepository candidatesRepository, JobsRepository jobsRepository) {
        this.candidatesRepository = candidatesRepository;
        this.jobsRepository = jobsRepository;
    }


    public HashMap<String, Object> registerCandidate(ModelCandidates modelCandidates, String type) {
        response = new HashMap<>();


        Optional<CandidatesEntity> findCandidateByPhone;
        Optional<CandidatesEntity> findCandidateByEmail;
        Optional<CandidatesEntity> findCandidateByJobSelected;
        if (type.equalsIgnoreCase("register")) {

            //Check if there is another candidate with the same email
            findCandidateByJobSelected = candidatesRepository.findDistinctBySelectedJob(modelCandidates.getSelected_job());

            if (findCandidateByJobSelected.isPresent()) {
                response.put("status", "01");
                response.put("message", "You already applied for this job");
                return response;
            }

//            //Check if there is another candidate with the same phone number.
//            findCandidateByPhone = candidatesRepository.findDistinctByPhone(modelCandidates.getPhone());
//
//            if (findCandidateByPhone.isPresent()) {
//                response.put("status", "01");
//                response.put("message", "Phone number " + modelCandidates.getPhone() + " is already registered");
//                return response;
//            }


            //Store the candidate.
            CandidatesEntity candidatesEntity = new CandidatesEntity();
            candidatesEntity.setEducation_level(modelCandidates.getEducation_level());
            candidatesEntity.setYears_of_experience(modelCandidates.getYears_of_experience());
            candidatesEntity.setPhone(modelCandidates.getPhone());
            candidatesEntity.setEmail(modelCandidates.getEmail());
            candidatesEntity.setFirst_name(modelCandidates.getFirst_name());
            candidatesEntity.setLast_name(modelCandidates.getLast_name());
            candidatesEntity.setSelectedJob(modelCandidates.getSelected_job());
            candidatesEntity.setCreatedOn(new Date());

            candidatesRepository.save(candidatesEntity);
            response.put("status", "00");
            response.put("message", "Registered successfully");
            return response;

        }
        CandidatesEntity candidatesEntity = candidatesRepository.findDistinctById(1);


        //Check if the candidate exist with the sent candidate id.
        if (candidatesEntity == null) {
            response.put("status", "01");
            response.put("message", "Candidate not found");
            return response;
        }

        //Check if there is another candidate with the same email
        findCandidateByJobSelected = candidatesRepository.findDistinctBySelectedJob(modelCandidates.getSelected_job());

        if (findCandidateByJobSelected.isPresent()) {
            response.put("status", "01");
            response.put("message", "You already applied for this job");
            return response;
        }



//        if (!candidatesEntity.getEmail().equalsIgnoreCase(modelCandidates.getEmail())) {
//            //Check if there is another candidate with the same email
//            findCandidateByEmail = candidatesRepository.findDistinctByEmail(modelCandidates.getEmail());
//
//            if (findCandidateByEmail.isPresent()) {
//                response.put("status", "01");
//                response.put("message", "Email " + modelCandidates.getEmail() + " is already registered");
//                return response;
//            }
//
//        }


//        if (!candidatesEntity.getPhone().equalsIgnoreCase(modelCandidates.getPhone())) {
//            //Check if there is another candidate with the same phone number.
//            findCandidateByPhone = candidatesRepository.findDistinctByPhone(modelCandidates.getPhone());
//
//            if (findCandidateByPhone.isPresent()) {
//                response.put("status", "01");
//                response.put("message", "Phone number " + modelCandidates.getPhone() + " is already registered");
//                return response;
//            }
//
//        }


        //Update the candidate.
        candidatesEntity.setEducation_level(modelCandidates.getEducation_level());
        candidatesEntity.setYears_of_experience(modelCandidates.getYears_of_experience());
        candidatesEntity.setPhone(modelCandidates.getPhone());
        candidatesEntity.setEmail(modelCandidates.getEmail());
        candidatesEntity.setFirst_name(modelCandidates.getFirst_name());
        candidatesEntity.setSelectedJob(modelCandidates.getSelected_job());
        candidatesEntity.setLast_name(modelCandidates.getLast_name());
        candidatesEntity.setCreatedOn(new Date());

        candidatesRepository.save(candidatesEntity);
        response.put("status", "00");
        response.put("message", "Updated successfully");

        return response;
    }


    public HashMap<String, Object> getCandidates(String type, long id) {
        response = new HashMap<>();

        if (type.equalsIgnoreCase("all")) {
            response.put("status", "00");
            response.put("message", "success");
            response.put("data", candidatesRepository.findAll());
            return response;
        }


        CandidatesEntity candidatesEntity = candidatesRepository.findDistinctById(id);

        if (candidatesEntity == null) {
            response.put("status", "01");
            response.put("message", "Candidate not found");
            return response;
        }


        response.put("status", "00");
        response.put("message", "success");
        response.put("data", candidatesEntity);
        return response;
    }


    public HashMap<String, Object> deleteCandidate(long id) {
        response = new HashMap<>();
        CandidatesEntity candidatesEntity = candidatesRepository.findDistinctById(id);

        //Check if the candidate exist with the sent candidate id.
        if (candidatesEntity == null) {
            response.put("status", "01");
            response.put("message", "Candidate not found");
            return response;
        }

        candidatesRepository.delete(candidatesEntity);
        response.put("status", "00");
        response.put("message", "Candidate deleted successfully");

        return response;

    }


    public HashMap<String, Object> getMyApplications(GetMyApplications modelCandidates)
    {
        response = new HashMap<>();

        System.out.println("My Applications " + new Gson().toJson(modelCandidates));

        List<JobApplications> applications = new ArrayList<>();

        List<CandidatesEntity> myApplications = candidatesRepository.getDistinctByEmail(modelCandidates.getEmail());

        System.out.println("My Applications " + new Gson().toJson(myApplications));

        for (CandidatesEntity singleApplication: myApplications)
        {
            JobsEntity jobsEntity = jobsRepository.findDistinctById(singleApplication.getSelectedJob());
            JobApplications jobApplications  = new JobApplications();
            jobApplications.setDescription(jobsEntity.getDescription());
            jobApplications.setName(jobsEntity.getName());
            jobApplications.setInterview_start_time(jobsEntity.getInterview_start_time());
            jobApplications.setInterview_end_time(jobsEntity.getInterview_end_time());
            jobApplications.setInterview_date(AppFunctions.convertDate(jobsEntity.getInterview_date()));
            jobApplications.setStatus(jobsEntity.getStatus());
            applications.add(jobApplications);
        }

        response.put("status", "00");
        response.put("message", "success");
        response.put("data", applications);

        return response;
    }

}
