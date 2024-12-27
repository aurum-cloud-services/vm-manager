package com.aurum.vmmanager.domain.validators;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainValidator {
    public static void when(boolean condition, Exception e) throws Exception {
        if (condition)
            throw e;
    }
}
