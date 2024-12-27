package com.aurum.vmmanager.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Table(name = "tb_logs")
@Entity
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;
    private String log;

    @Column(name = "where_happened")
    private String whereHappened;
    private String transaction;
}
