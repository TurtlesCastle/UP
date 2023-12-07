package com.example.finals.repos;

import com.example.finals.entities.License;
import com.example.finals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepo  extends JpaRepository<License, Long> {
    public License findByFio(String fio);
}
