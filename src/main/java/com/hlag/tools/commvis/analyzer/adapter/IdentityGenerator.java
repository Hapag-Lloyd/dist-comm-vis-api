package com.hlag.tools.commvis.analyzer.adapter;

import com.hlag.tools.commvis.analyzer.port.IIdentityGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Simple class which generates unique ids. As it does not belong to the core competency of the application it has
 * been moved to the adapter package to improve the testability.
 */
@Component
public class IdentityGenerator implements IIdentityGenerator {
    @Override
    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
