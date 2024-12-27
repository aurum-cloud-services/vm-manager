package com.aurum.vmmanager.application.commands;

import com.aurum.vmmanager.application.abstractions.ICreateVMCommand;
import com.aurum.vmmanager.infrastructure.entities.LogEntity;
import com.aurum.vmmanager.infrastructure.repositories.ILogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CreateVMCommand implements ICreateVMCommand {

    @Value("${VBOX_UBUNTU_APPLIANCE}")
    private String ubuntuPath;

    private final ILogRepository logRepository;

    public CreateVMCommand(ILogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void run(String name) {
        String command = String.format(
                "VboxManage import \"%s\" --vsys 0 --vmname \"%s\"",
                ubuntuPath, name);

        try {
            Process process = Runtime.getRuntime().exec(command);

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            var error = LogEntity.builder()
                    .log(e.getMessage())
                    .whereHappened("vm-manager")
                    .transaction(String.format("create-vm-command:run->%s", name))
                    .build();

            logRepository.save(error);
        }
    }
}
