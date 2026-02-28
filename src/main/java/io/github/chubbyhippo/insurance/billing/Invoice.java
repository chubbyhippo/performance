package io.github.chubbyhippo.insurance.billing;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
    name = "invoices",
    indexes = {
      @Index(name = "idx_invoices_policy_id", columnList = "policyId"),
      @Index(name = "idx_invoices_policy_id_status", columnList = "policyId,status")
    })
public class Invoice {

  @Id private UUID id;

  private UUID policyId;

  @Enumerated(EnumType.STRING)
  private InvoiceStatus status;

  private Instant createdAt;
  private Instant voidedAt;

  protected Invoice() {}

  private Invoice(UUID id, UUID policyId, InvoiceStatus status, Instant createdAt) {
    this.id = id;
    this.policyId = policyId;
    this.status = status;
    this.createdAt = createdAt;
  }

  public static Invoice openForPolicy(UUID policyId, Instant now) {
    if (policyId == null) throw new IllegalArgumentException("policyId required");
    if (now == null) throw new IllegalArgumentException("now required");
    return new Invoice(UUID.randomUUID(), policyId, InvoiceStatus.OPEN, now);
  }

  public void voidNow(Instant now) {
    if (status == InvoiceStatus.VOID) return;
    if (status == InvoiceStatus.PAID) throw new IllegalStateException("Cannot void a PAID invoice");
    status = InvoiceStatus.VOID;
    voidedAt = now;
  }

  public UUID id() {
    return id;
  }

  public UUID policyId() {
    return policyId;
  }

  public InvoiceStatus status() {
    return status;
  }

  public Instant createdAt() {
    return createdAt;
  }

  public Instant voidedAt() {
    return voidedAt;
  }
}
