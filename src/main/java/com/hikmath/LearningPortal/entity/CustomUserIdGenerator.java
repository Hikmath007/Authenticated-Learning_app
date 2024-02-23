package com.hikmath.LearningPortal.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class CustomUserIdGenerator implements IdentifierGenerator, Serializable {

    private static final String PREFIX = "user_";
    private static long counter = 0;

    public CustomUserIdGenerator() {
        // Default constructor
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Custom logic to generate the next ID (for simplicity, using a counter).
        Long id = ++counter;
        return PREFIX + String.format("%07d", id);
    }
}
