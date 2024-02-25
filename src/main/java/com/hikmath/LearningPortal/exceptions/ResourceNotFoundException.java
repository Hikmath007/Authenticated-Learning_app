package com.hikmath.LearningPortal.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ResourceNotFoundException extends RuntimeException{
     final String resourceName;
    final String fieldName;
     final String fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,String fieldValue){
        super(String.format(" %s not found with %s: %s", resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

}
