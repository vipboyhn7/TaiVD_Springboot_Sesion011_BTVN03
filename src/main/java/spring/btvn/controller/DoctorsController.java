package spring.btvn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.btvn.common.reponse.ApiResponse;
import spring.btvn.common.reponse.PaginationMeta;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {
    @GetMapping()
    public ResponseEntity<ApiResponse<List<?>>> getDoctors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<?> doctors = List.of();

        PaginationMeta meta = PaginationMeta.builder()
                .page(page)
                .totalElements(100L)
                .totalPages(10)
                .build();

        return ResponseEntity.ok(ApiResponse.success(doctors, meta));
    }
}
