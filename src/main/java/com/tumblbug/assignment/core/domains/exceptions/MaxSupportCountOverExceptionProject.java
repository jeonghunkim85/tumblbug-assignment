package com.tumblbug.assignment.core.domains.exceptions;

import com.tumblbug.assignment.core.domains.Project;

public class MaxSupportCountOverExceptionProject extends ProjectDomainException {

    public MaxSupportCountOverExceptionProject() {
        super("support count cannot be over " + Project.MAX_SUPPORT_COUNT);
    }

}
