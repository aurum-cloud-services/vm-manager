package com.aurum.vmmanager.domain.core;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue createVm() {
        return new Queue("create-vm-queue", true);
    }

    @Bean
    public Queue deleteVm() {
        return new Queue("delete-vm-queue", true);
    }
}
