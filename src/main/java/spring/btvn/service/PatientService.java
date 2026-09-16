package spring.btvn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.btvn.common.reponse.ApiResponse;
import spring.btvn.common.reponse.PaginationMeta;
import spring.btvn.entity.Patient;
import spring.btvn.exception.ResourceNotFoundException;
import spring.btvn.repository.PatientRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public ApiResponse<List<Patient>> getAllPatients(int page, int size) {
        log.trace("Entering getAllPatients with page={}, size={}", page, size);

        int pageNumber = Math.max(page - 1, 0);
        Pageable pageable = PageRequest.of(pageNumber, size);

        Page<Patient> patientPage = patientRepository.findAll(pageable);
        log.debug("Found {} patients across {} total pages",
                patientPage.getNumberOfElements(), patientPage.getTotalPages());

        PaginationMeta meta = PaginationMeta.builder()
                .page(patientPage.getNumber())
                .size(patientPage.getSize())
                .totalElements(patientPage.getTotalElements())
                .totalPages(patientPage.getTotalPages())
                .build();

        return ApiResponse.success(patientPage.getContent(), meta);
    }

    public ApiResponse<Patient> getPatientById(Long id) {
        log.trace("Entering getPatientById with id={}", id);

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Patient not found with id: {}", id);
                    return new ResourceNotFoundException("Không tìm thấy bệnh nhân với id: " + id);
                });

        log.debug("Patient retrieved successfully: id={}, name={}", patient.getId(), patient.getName());
        return ApiResponse.success(patient);
    }
}