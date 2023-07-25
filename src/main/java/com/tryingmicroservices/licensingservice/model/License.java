package com.tryingmicroservices.licensingservice.model;

public record License(

        int id,
        String licenseId,
        String description,
        String organizationId,
        String productName,
        String licenseType
) {
}
