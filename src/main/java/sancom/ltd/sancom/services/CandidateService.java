package sancom.ltd.sancom.services;


import org.springframework.stereotype.Service;
import sancom.ltd.sancom.entities.CandidatesEntity;
import sancom.ltd.sancom.models.ModelCandidates;
import sancom.ltd.sancom.repositories.CandidatesRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CandidateService {
    private HashMap<String, Object> response;
    private CandidatesRepository candidatesRepository;

    public CandidateService(CandidatesRepository candidatesRepository) {
        this.candidatesRepository = candidatesRepository;
    }


    public HashMap<String, Object> registerCandidate(ModelCandidates modelCandidates, String type) {
        response = new HashMap<>();


        Optional<CandidatesEntity> findCandidateByPhone;
        Optional<CandidatesEntity> findCandidateByEmail;
        if (type.equalsIgnoreCase("register")) {

            //Check if there is another candidate with the same email
            findCandidateByEmail = candidatesRepository.findDistinctByEmail(modelCandidates.getEmail());

            if (findCandidateByEmail.isPresent()) {
                response.put("status", "01");
                response.put("message", "Email " + modelCandidates.getEmail() + " is already registered");
                return response;
            }

            //Check if there is another candidate with the same phone number.
            findCandidateByPhone = candidatesRepository.findDistinctByPhone(modelCandidates.getPhone());

            if (findCandidateByPhone.isPresent()) {
                response.put("status", "01");
                response.put("message", "Phone number " + modelCandidates.getPhone() + " is already registered");
                return response;
            }


            //Store the candidate.
            CandidatesEntity candidatesEntity = new CandidatesEntity();
            candidatesEntity.setEducation_level(modelCandidates.getEducation_level());
            candidatesEntity.setYears_of_experience(modelCandidates.getYears_of_experience());
            candidatesEntity.setPhone(modelCandidates.getPhone());
            candidatesEntity.setEmail(modelCandidates.getEmail());
            candidatesEntity.setFirst_name(modelCandidates.getFirst_name());
            candidatesEntity.setLast_name(modelCandidates.getLast_name());
            candidatesEntity.setSelected_job(modelCandidates.getSelected_job());
            candidatesEntity.setCreatedOn(new Date());

            candidatesRepository.save(candidatesEntity);
            response.put("status", "00");
            response.put("message", "Registered successfully");
            return response;

        }
        CandidatesEntity candidatesEntity = candidatesRepository.findDistinctById(modelCandidates.getId());


        //Check if the candidate exist with the sent candidate id.
        if (candidatesEntity == null) {
            response.put("status", "01");
            response.put("message", "Candidate not found");
            return response;
        }


        if (!candidatesEntity.getEmail().equalsIgnoreCase(modelCandidates.getEmail())) {
            //Check if there is another candidate with the same email
            findCandidateByEmail = candidatesRepository.findDistinctByEmail(modelCandidates.getEmail());

            if (findCandidateByEmail.isPresent()) {
                response.put("status", "01");
                response.put("message", "Email " + modelCandidates.getEmail() + " is already registered");
                return response;
            }

        }


        if (!candidatesEntity.getPhone().equalsIgnoreCase(modelCandidates.getPhone())) {
            //Check if there is another candidate with the same phone number.
            findCandidateByPhone = candidatesRepository.findDistinctByPhone(modelCandidates.getPhone());

            if (findCandidateByPhone.isPresent()) {
                response.put("status", "01");
                response.put("message", "Phone number " + modelCandidates.getPhone() + " is already registered");
                return response;
            }

        }


        //Update the candidate.
        candidatesEntity.setEducation_level(modelCandidates.getEducation_level());
        candidatesEntity.setYears_of_experience(modelCandidates.getYears_of_experience());
        candidatesEntity.setPhone(modelCandidates.getPhone());
        candidatesEntity.setEmail(modelCandidates.getEmail());
        candidatesEntity.setFirst_name(modelCandidates.getFirst_name());
        candidatesEntity.setSelected_job(modelCandidates.getSelected_job());
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

}
