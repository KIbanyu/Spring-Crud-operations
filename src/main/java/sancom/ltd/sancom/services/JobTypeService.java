package sancom.ltd.sancom.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sancom.ltd.sancom.entities.EntityJobType;
import sancom.ltd.sancom.entities.JobsEntity;
import sancom.ltd.sancom.models.ModelJobType;
import sancom.ltd.sancom.repositories.JobTypeRepository;

import java.util.Date;
import java.util.HashMap;

@Service
public class JobTypeService {
    private HashMap<String, Object> response;

    @Autowired
    private JobTypeRepository jobTypeRepository;

    public HashMap<String, Object> addJobType(ModelJobType modelJobType, String type) {
        response = new HashMap<>();

        if (type.equalsIgnoreCase("add")) {
            EntityJobType entityJobType = new EntityJobType();
            entityJobType.setName(modelJobType.getName());
            entityJobType.setCreatedOn(new Date());
            entityJobType.setDescription(modelJobType.getDescription());
            jobTypeRepository.save(entityJobType);

            response.put("status", "00");
            response.put("message", "Job type added successfully");
            return response;

        }

        EntityJobType entityJobTypeToEdit = jobTypeRepository.findDistinctById(modelJobType.getId());

        if (entityJobTypeToEdit == null) {
            response.put("status", "01");
            response.put("message", "Job type could not be found");
            return response;
        }

        entityJobTypeToEdit.setDescription(modelJobType.getDescription());
        entityJobTypeToEdit.setName(modelJobType.getName());
        jobTypeRepository.save(entityJobTypeToEdit);

        response.put("status", "00");
        response.put("message", "Job type updated successfully");
        return response;
    }


    public HashMap<String, Object> getJobTypes() {
        response = new HashMap<>();
        response.put("status", "00");
        response.put("data", jobTypeRepository.findAll());
        response.put("message", "Success");

        return response;
    }

    public HashMap<String, Object> deleteJobType(long id) {
        response = new HashMap<>();

        EntityJobType entityJobType = jobTypeRepository.findDistinctById(id);

        if (entityJobType == null) {
            response.put("status", "01");
            response.put("message", "Job type could not be");
            return response;
        }

        jobTypeRepository.delete(entityJobType);

        response.put("status", "00");
        response.put("message", "Job type deleted successfully");


        return response;

    }


}
