package sancom.ltd.sancom.services;


import org.springframework.stereotype.Service;
import sancom.ltd.sancom.entities.EntityEducationLevels;
import sancom.ltd.sancom.models.ModelEducationLevel;
import sancom.ltd.sancom.repositories.EducationLevelsRepository;

import java.util.Date;
import java.util.HashMap;

@Service
public class EducationLevelService {
    private HashMap<String, Object> response;

    private EducationLevelsRepository educationLevelsRepository;

    public EducationLevelService(EducationLevelsRepository educationLevelsRepository) {
        this.educationLevelsRepository = educationLevelsRepository;
    }


    public HashMap<String, Object> addEducationLevel(ModelEducationLevel modelEducationLevel, String type) {
        response = new HashMap<>();


        if (type.equalsIgnoreCase("add")) {
            EntityEducationLevels entityEducationLevels = new EntityEducationLevels();
            entityEducationLevels.setName(modelEducationLevel.getName());
            entityEducationLevels.setCreatedOn(new Date());
            educationLevelsRepository.save(entityEducationLevels);

            response.put("status", "00");
            response.put("message", "Education level added successfully");
            return response;
        }


        EntityEducationLevels entityEducationLevelsToEdit = educationLevelsRepository.findDistinctById(modelEducationLevel.getId());

        if (entityEducationLevelsToEdit == null) {
            response.put("status", "01");
            response.put("message", "Education level not found");
            return response;
        }

        entityEducationLevelsToEdit.setName(modelEducationLevel.getName());
        educationLevelsRepository.save(entityEducationLevelsToEdit);

        response.put("status", "00");
        response.put("message", "Education level updated successfully");

        return response;
    }


    public HashMap<String, Object> getEducationLevel() {

        response = new HashMap<>();
        response.put("status", "00");
        response.put("message", "success");
        response.put("data", educationLevelsRepository.findAll());

        return response;
    }


    public HashMap<String, Object> deleteEducationLevel(long id) {
        response = new HashMap<>();

        EntityEducationLevels entityEducationLevels = educationLevelsRepository.findDistinctById(id);

        if (entityEducationLevels == null) {
            response.put("status", "01");
            response.put("message", "Education level not found");
            return response;
        }

        educationLevelsRepository.delete(entityEducationLevels);

        response.put("status", "00");
        response.put("message", "Education level deleted successfully");
        return response;
    }

}
