package com.tumblbug.assignment.core.domains.exceptions;

import com.tumblbug.assignment.core.domains.Project;

public class MaxAmountOverExceptionProject extends ProjectDomainException {

    public MaxAmountOverExceptionProject(String field) {
        super(field + " cannot be over " + Project.MAX_AMOUNT);
    }

}
