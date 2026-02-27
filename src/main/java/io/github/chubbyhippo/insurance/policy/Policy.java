package io.github.chubbyhippo.insurance.policy;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    private UUID id;

    private String customerId;

    @Embedded
    private Coverage coverage;

    @Embedded
    private Money premium;

    @Enumerated(EnumType.STRING)
    private PolicyStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    protected Policy() {}

    private Policy(UUID id, String customerId, Coverage coverage, Money premium, LocalDate startDate, LocalDate endDate) {
        this.id = Objects.requireNonNull(id);
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("customerId required");
        this.customerId = customerId;
        this.coverage = Objects.requireNonNull(coverage);
        this.premium = Objects.requireNonNull(premium);
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);

        if (!endDate.isAfter(startDate)) throw new IllegalArgumentException("endDate must be after startDate");
        this.status = PolicyStatus.DRAFT;
    }

    public static Policy draftNew(String customerId, Coverage coverage, Money premium, LocalDate startDate, LocalDate endDate) {
        return new Policy(UUID.randomUUID(), customerId, coverage, premium, startDate, endDate);
    }

    public void activate() {
        if (status != PolicyStatus.DRAFT) throw new IllegalStateException("Only DRAFT policies can be activated");
        status = PolicyStatus.ACTIVE;
    }

    public void cancel() {
        if (status == PolicyStatus.CANCELLED) return;
        if (status == PolicyStatus.DRAFT) throw new IllegalStateException("Cannot cancel DRAFT policy");
        status = PolicyStatus.CANCELLED;
    }

    public UUID id() { return id; }
    public PolicyStatus status() { return status; }
    public Money premium() { return premium; }
    public Coverage coverage() { return coverage; }
}
