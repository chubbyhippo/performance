package io.github.chubbyhippo.insurance.billing;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvoiceService {

    public void createInvoiceFor(UUID policyId) {
        // persist invoice, call payment provider, etc.
        // keeping it minimal here
    }

    public void voidOpenInvoicesFor(UUID policyId) {
        // ...
    }
}
