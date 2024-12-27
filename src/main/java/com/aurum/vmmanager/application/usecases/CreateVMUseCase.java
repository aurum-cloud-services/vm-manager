package com.aurum.vmmanager.application.usecases;

import com.aurum.vmmanager.application.abstractions.ICreateVMCommand;
import com.aurum.vmmanager.application.abstractions.ICreateVMUseCase;
import com.aurum.vmmanager.application.dtos.CreateVMDTO;
import com.aurum.vmmanager.infrastructure.entities.LogEntity;
import com.aurum.vmmanager.infrastructure.repositories.ILogRepository;
import com.aurum.vmmanager.infrastructure.repositories.IUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateVMUseCase implements ICreateVMUseCase {
    private final ICreateVMCommand command;
    private final IUserRepository userRepository;
    private final ObjectMapper mapper;
    private final ILogRepository logRepository;

    @Autowired
    public CreateVMUseCase(ICreateVMCommand command, IUserRepository userRepository, ILogRepository logRepository) {
        this.command = command;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void run(String message) {
        CreateVMDTO dto = null;

        try {
            dto = mapper.readValue(message, CreateVMDTO.class);
        } catch (JsonProcessingException e) {
            var error = LogEntity.builder()
                    .log(e.getMessage())
                    .whereHappened("vm-manager")
                    .transaction("create-vm-usecase:run->jackson-deserialization")
                    .build();

            logRepository.save(error);

            return;
        }

        var name = dto.getName();
        name = name
                .toLowerCase()
                .trim()
                .replaceAll("\\s+", "-");
        name += "-";
        name += UUID.randomUUID().toString();

        var user = userRepository.findById(dto.getCreatedBy());
        if (user.isEmpty()) {
            return;
        }

        command.run(name);
    }
}
