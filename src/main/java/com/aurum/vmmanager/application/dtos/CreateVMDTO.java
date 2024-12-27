package com.aurum.vmmanager.application.dtos;

import lombok.Data;

@Data
public class CreateVMDTO {
    private String name;
    private Long createdBy;
}
