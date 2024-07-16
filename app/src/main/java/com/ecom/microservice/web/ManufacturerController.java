package com.ecom.microservice.web;

import java.util.List;

import com.ecom.microservice.api.model.ManufacturerResponse;
import com.ecom.microservice.service.ManufacturerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Operation(
        summary = "Query manufacturers",
        description = "Query the db to retrieve all available manufacturers"
    )
    @GetMapping
    public List<ManufacturerResponse> search(
        @Parameter(description = "Page Index") @RequestParam(value = "i", defaultValue = "0") int page,
        @Parameter(description = "Page Size") @RequestParam(value = "s", defaultValue = "20") int size) {
        return manufacturerService.search(PageRequest.of(page, size));
    }

}
