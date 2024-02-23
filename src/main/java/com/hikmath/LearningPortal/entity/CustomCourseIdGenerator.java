package com.hikmath.LearningPortal.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class CustomCourseIdGenerator implements IdentifierGenerator {
//overide the generate method given by hibernate
    private static final String PREFIX = "course_";
    private static long counter = 0;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // Custom logic to generate the next ID (for simplicity, using a counter).
        Long id = ++counter;
        return PREFIX + String.format("%08d", id);
    }
}
