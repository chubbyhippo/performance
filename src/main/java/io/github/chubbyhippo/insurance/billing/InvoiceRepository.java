package io.github.chubbyhippo.insurance.billing;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    boolean existsByPolicyIdAndStatus(UUID policyId, InvoiceStatus status);

    List<Invoice> findByPolicyIdAndStatus(UUID policyId, InvoiceStatus status);
}
