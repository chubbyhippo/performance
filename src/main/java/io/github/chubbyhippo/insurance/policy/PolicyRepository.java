package io.github.chubbyhippo.insurance.policy;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, UUID> {}
