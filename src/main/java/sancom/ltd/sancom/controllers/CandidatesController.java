package sancom.ltd.sancom.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sancom.ltd.sancom.models.GetMyApplications;
import sancom.ltd.sancom.models.ModelCandidates;
import sancom.ltd.sancom.services.CandidateService;

import java.util.HashMap;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/web/v1/")
public class CandidatesController {
    Logger logger = LoggerFactory.getLogger(CandidatesController.class);

    private CandidateService candidateService;

    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("candidates/registerCandidate")
    public HashMap<String, Object> candidateRegistration(@RequestBody ModelCandidates modelCandidates) {


        return candidateService.registerCandidate(modelCandidates, "register");
    }

    @GetMapping("candidates/deleteCandidate")
    public HashMap<String, Object> deleteCandidate(long id) {

        return candidateService.deleteCandidate(id);
    }

    @PostMapping("candidates/editCandidate")
    public HashMap<String, Object> editCandidate(@RequestBody ModelCandidates modelCandidates) {

        return candidateService.registerCandidate(modelCandidates, "update");
    }

    @GetMapping("candidates/getAllCandidates")
    public HashMap<String, Object> getAllCandidates() {

        return candidateService.getCandidates("all", 0);
    }

    @PostMapping("candidates/getMyApplications")
    public HashMap<String, Object> getMyApplications(@RequestBody GetMyApplications getMyApplications) {

        return candidateService.getMyApplications(getMyApplications);
    }

    @GetMapping("candidates/getCandidateById")
    public HashMap<String, Object> getCandidateById(long id) {

        return candidateService.getCandidates("candidateById", id);
    }


}
