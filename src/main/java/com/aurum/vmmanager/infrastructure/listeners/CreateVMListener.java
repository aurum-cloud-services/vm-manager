package com.aurum.vmmanager.infrastructure.listeners;

import com.aurum.vmmanager.application.abstractions.IListenerAbstraction;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CreateVMListener implements IListenerAbstraction {
    @Override
    @RabbitListener(queues = "create-vm-queue")
    public void reciveMessage(String message) {
        System.out.println(message);
    }
}
