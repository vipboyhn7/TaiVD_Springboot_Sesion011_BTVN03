package spring.btvn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.btvn.common.reponse.ApiResponse;
import spring.btvn.dto.PatientRequest;
import spring.btvn.entity.Patient;
import spring.btvn.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService patientService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Patient>>> getAllPatients(@RequestParam(defaultValue = "1") int page,
                                                                     @RequestParam(defaultValue = "10") int size
    ) {
        ApiResponse<List<Patient>> response = patientService.getAllPatients(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patient>> getPatientById(@PathVariable Long id) {
        ApiResponse<Patient> response = patientService.getPatientById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody PatientRequest request) {
        log.info("Receiving new patient: {}", request.getName());

        if (request.getAge() > 120) {
            log.warn("Patient age is abnormally high: {} (Name: {})", request.getAge(), request.getName());
        }

        return ResponseEntity.ok("Patient added successfully");
    }
}
