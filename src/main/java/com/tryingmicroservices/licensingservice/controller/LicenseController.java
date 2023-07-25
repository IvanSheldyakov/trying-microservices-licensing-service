package com.tryingmicroservices.licensingservice.controller;

import com.tryingmicroservices.licensingservice.model.License;
import com.tryingmicroservices.licensingservice.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("v1/organization/{organizationId}/license")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {

        return ResponseEntity.ok(licenseService.getLicense(licenseId, organizationId));
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId, locale));
    }

    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.createLicense(license, organizationId, locale));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId,
                organizationId, locale));
    }
}
