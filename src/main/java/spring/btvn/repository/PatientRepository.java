package spring.btvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.btvn.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
