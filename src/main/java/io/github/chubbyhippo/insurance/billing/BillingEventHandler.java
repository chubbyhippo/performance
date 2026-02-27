package io.github.chubbyhippo.insurance.billing;

import io.github.chubbyhippo.insurance.policy.PolicyEvents;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class BillingEventHandler {

    private final InvoiceService invoices;

    public BillingEventHandler(InvoiceService invoices) {
        this.invoices = invoices;
    }

    @ApplicationModuleListener
    void on(PolicyEvents.PolicyActivated event) {
        invoices.createInvoiceFor(event.policyId());
    }

    @ApplicationModuleListener
    void on(PolicyEvents.PolicyCancelled event) {
        invoices.voidOpenInvoicesFor(event.policyId());
    }
}
