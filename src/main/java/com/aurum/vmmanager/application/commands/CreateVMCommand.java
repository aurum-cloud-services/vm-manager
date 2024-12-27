package com.aurum.vmmanager.application.commands;

import com.aurum.vmmanager.application.abstractions.ICreateVMCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CreateVMCommand implements ICreateVMCommand {

    @Value("${VBOX_UBUNTU_APPLIANCE}")
    private String ubuntuPath;

    @Override
    public void run(String name) {
        String command = String.format(
                "VboxManage import \"%s\" --vsys 0 --vmname \"%s\"",
                ubuntuPath, name);

        try {
            Process process = Runtime.getRuntime().exec(command);

            process.waitFor();
        } catch (IOException | InterruptedException e) {

        }
    }
}
