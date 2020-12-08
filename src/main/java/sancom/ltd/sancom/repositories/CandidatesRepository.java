package sancom.ltd.sancom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sancom.ltd.sancom.entities.CandidatesEntity;

import java.util.List;
import java.util.Optional;

public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {
//
//    Optional<CandidatesEntity> findDistinctByEmail(String email);
//    Optional<CandidatesEntity> findDistinctByPhone(String phone);
    Optional<CandidatesEntity> findDistinctBySelectedJob(long selected_job);
    CandidatesEntity findDistinctById(long id);
    List<CandidatesEntity> getDistinctByEmail(String email);

}
