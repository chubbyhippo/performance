package io.github.chubbyhippo.insurance.billing;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

  boolean existsByPolicyIdAndStatus(UUID policyId, InvoiceStatus status);

  List<Invoice> findByPolicyIdAndStatus(UUID policyId, InvoiceStatus status);
}
