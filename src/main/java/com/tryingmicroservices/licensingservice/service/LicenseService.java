package com.tryingmicroservices.licensingservice.service;

import com.tryingmicroservices.licensingservice.model.License;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LicenseService {

    private final MessageSource messageSource;

    public LicenseService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public License getLicense(String licenseId, String organizationId) {
        return null;
    }

    public String createLicense(License license, String organizationId, Locale locale) {

        return String.format(messageSource.getMessage("license.create.message", null, locale), license.getLicenseId());
    }

    public String updateLicense(License license, String organizationId, Locale locale) {
        return String.format(messageSource.getMessage("license.update.message", null, locale), license.getLicenseId());
    }

    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        return String.format(messageSource.getMessage("license.delete.message", null, locale), licenseId, organizationId);
    }
}
