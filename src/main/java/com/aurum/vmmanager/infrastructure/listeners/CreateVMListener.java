package com.aurum.vmmanager.infrastructure.listeners;

import com.aurum.vmmanager.application.abstractions.ICreateVMUseCase;
import com.aurum.vmmanager.application.abstractions.IListenerAbstraction;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CreateVMListener implements IListenerAbstraction {
    private final ICreateVMUseCase useCase;

    public CreateVMListener(ICreateVMUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    @RabbitListener(queues = "create-vm-queue")
    public void reciveMessage(String message) {
        useCase.run(message);
    }
}
