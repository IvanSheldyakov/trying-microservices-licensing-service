package com.tryingmicroservices.licensingservice.repository.impl;

import com.tryingmicroservices.licensingservice.model.License;
import com.tryingmicroservices.licensingservice.repository.LicenseRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcLicenseRepository implements LicenseRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLicenseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<License> findByOrganizationId(String organizationId) {
        return jdbcTemplate.query(
                "select * from licenses where organization_id = ?",
                BeanPropertyRowMapper.newInstance(License.class),
                organizationId
        );
    }

    @Override
    public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from licenses where license_id = ? and organization_id = ?",
                    BeanPropertyRowMapper.newInstance(License.class),
                    licenseId,
                    organizationId
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }

    }

    @Override
    public int save(License license) {
        return jdbcTemplate
                .update(
                        "insert into licenses(license_id, description, organization_id, product_name, license_type, comment) values (?,?,?,?,?,?)",
                        license.getLicenseId(),
                        license.getDescription(),
                        license.getOrganizationId(),
                        license.getProductName(),
                        license.getLicenseType(),
                        license.getComment()
                );
    }

    @Override
    public int deleteById(String id) {
        return jdbcTemplate.update("delete from licenses where license_id = ?", id);
    }
}
