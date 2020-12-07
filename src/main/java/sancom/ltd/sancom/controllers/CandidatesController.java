package sancom.ltd.sancom.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sancom.ltd.sancom.models.ModelCandidates;

import java.util.HashMap;

@RestController
@RequestMapping(value = "api/web/v1/")
public class CandidatesController {

    @PostMapping("candidates/registerUser")
    public HashMap<String, Object> candidateRegistration(@RequestBody ModelCandidates modelCandidates) {

        return null;
    }

    @PostMapping("candidates/delete")
    public HashMap<String, Object> deleteCandidate(@RequestBody ModelCandidates modelCandidates) {

        return null;
    }

    @PostMapping("candidates/edit")
    public HashMap<String, Object> editCandidate(@RequestBody ModelCandidates modelCandidates) {

        return null;
    }

}
