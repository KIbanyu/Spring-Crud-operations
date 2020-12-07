package sancom.ltd.sancom.controllers;

import org.springframework.web.bind.annotation.*;
import sancom.ltd.sancom.models.ModelCandidates;
import sancom.ltd.sancom.services.CandidateService;

import java.util.HashMap;

@RestController
@RequestMapping(value = "api/web/v1/")
public class CandidatesController {

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

    @GetMapping("candidates/getCandidateById")
    public HashMap<String, Object> getCandidateById(long id) {

        return candidateService.getCandidates("candidateById", id);
    }


}
