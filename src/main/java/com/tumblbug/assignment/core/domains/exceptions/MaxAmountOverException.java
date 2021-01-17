package com.tumblbug.assignment.core.domains.exceptions;

import com.tumblbug.assignment.core.domains.Project;

public class MaxAmountOverException extends ProjectDomainException {

    public MaxAmountOverException(String field) {
        super(field + " cannot be over " + Project.MAX_AMOUNT);
    }

}
