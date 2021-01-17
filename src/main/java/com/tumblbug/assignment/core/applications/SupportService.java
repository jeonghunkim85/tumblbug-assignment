package com.tumblbug.assignment.core.applications;

import com.tumblbug.assignment.core.applications.port.in.SupportUseCases;
import com.tumblbug.assignment.core.applications.port.out.ReadProjectPort;
import com.tumblbug.assignment.core.applications.port.out.WriteProjectPort;
import com.tumblbug.assignment.core.domains.Support;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportService implements SupportUseCases {

    final ReadProjectPort readProjectPort;
    final WriteProjectPort writeProjectPort;

    @Override
    public void support(Support support) {
        // todo. implement
    }
}
