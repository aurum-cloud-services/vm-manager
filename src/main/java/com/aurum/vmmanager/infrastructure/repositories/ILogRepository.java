package com.aurum.vmmanager.infrastructure.repositories;

import com.aurum.vmmanager.infrastructure.entities.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILogRepository extends JpaRepository<LogEntity, Long> {
}
