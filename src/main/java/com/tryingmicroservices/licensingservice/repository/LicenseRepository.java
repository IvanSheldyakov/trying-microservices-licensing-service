package com.tryingmicroservices.licensingservice.repository;

import com.tryingmicroservices.licensingservice.model.License;

import java.util.List;

public interface LicenseRepository {

    List<License> findByOrganizationId(String organizationId);

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

    int save(License license);

    int deleteById(String id);

}
