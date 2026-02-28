package io.github.chubbyhippo.insurance.billing;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceService {

  private final InvoiceRepository invoices;
  private final Clock clock;

  public InvoiceService(InvoiceRepository invoices, Clock clock) {
    this.invoices = invoices;
    this.clock = clock;
  }

  @Transactional
  public void createInvoiceFor(UUID policyId) {
    if (policyId == null) throw new IllegalArgumentException("policyId required");

    // idempotency: don't create duplicates if event is re-delivered
    boolean alreadyOpen = invoices.existsByPolicyIdAndStatus(policyId, InvoiceStatus.OPEN);
    if (alreadyOpen) return;

    Instant now = Instant.now(clock);
    invoices.save(Invoice.openForPolicy(policyId, now));
  }

  @Transactional
  public void voidOpenInvoicesFor(UUID policyId) {
    if (policyId == null) throw new IllegalArgumentException("policyId required");

    List<Invoice> open = invoices.findByPolicyIdAndStatus(policyId, InvoiceStatus.OPEN);
    if (open.isEmpty()) return;

    Instant now = Instant.now(clock);
    for (Invoice invoice : open) {
      invoice.voidNow(now);
    }

    // save updated status/voidedAt
    invoices.saveAll(open);
  }
}
