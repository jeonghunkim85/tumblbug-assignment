package com.tumblbug.assignment.core.domains.exceptions;

import com.tumblbug.assignment.core.domains.Project;

public class MaxSupportCountOverException extends ProjectDomainException {

    public MaxSupportCountOverException() {
        super("support count cannot be over " + Project.MAX_SUPPORT_COUNT);
    }

}
