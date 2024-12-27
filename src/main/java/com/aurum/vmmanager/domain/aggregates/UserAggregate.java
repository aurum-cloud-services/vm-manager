package com.aurum.vmmanager.domain.aggregates;

import com.aurum.vmmanager.domain.exceptions.domain.CpfNotValidException;
import com.aurum.vmmanager.domain.exceptions.domain.NameNotValidException;
import com.aurum.vmmanager.domain.exceptions.domain.PasswordNotValidException;
import com.aurum.vmmanager.domain.validators.CpfValidator;
import com.aurum.vmmanager.domain.validators.DomainValidator;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserAggregate {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String password;
    private LocalDate createdAt;

    private UserAggregate(
            String name,
            String email,
            String cpf,
            String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public static UserAggregate build(
            String name,
            String email,
            String cpf,
            String password
    ) throws Exception {
        var aggregate = new UserAggregate(name, email, cpf, password);

        aggregate.validateDomain();

        return aggregate;
    }

    private void validateDomain() throws Exception {
        DomainValidator.when(this.name.length() > 40, new NameNotValidException());
        DomainValidator.when(!CpfValidator.isValid(this.cpf), new CpfNotValidException());
        DomainValidator.when(this.password.length() < 12 || this.password.length() > 100, new PasswordNotValidException());
    }
}
