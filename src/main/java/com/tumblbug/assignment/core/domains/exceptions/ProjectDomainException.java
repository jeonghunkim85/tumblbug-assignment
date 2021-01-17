package com.tumblbug.assignment.core.domains.exceptions;

public class ProjectDomainException extends RuntimeException{
    public ProjectDomainException() {
        super();
    }

    public ProjectDomainException(String message) {
        super(message);
    }
}
