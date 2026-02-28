package io.github.chubbyhippo.insurance.policy;

import io.github.chubbyhippo.insurance.underwriting.UnderwritingService;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PolicyService {

  private final PolicyRepository repository;
  private final UnderwritingService underwriting;
  private final ApplicationEventPublisher events;

  public PolicyService(
      PolicyRepository repository,
      UnderwritingService underwriting,
      ApplicationEventPublisher events) {
    this.repository = repository;
    this.underwriting = underwriting;
    this.events = events;
  }

  @Transactional
  public UUID createDraft(
      String customerId, String coverageType, int coverageLimit, LocalDate start, LocalDate end) {
    Coverage coverage = new Coverage(coverageType, coverageLimit);
    Money premium = underwriting.calculatePremium(coverage);

    Policy policy = Policy.draftNew(customerId, coverage, premium, start, end);
    repository.save(policy);
    return policy.id();
  }

  @Transactional
  public void activate(UUID policyId) {
    Policy policy =
        repository
            .findById(policyId)
            .orElseThrow(() -> new IllegalArgumentException("Policy not found"));
    policy.activate();
    repository.save(policy);

    events.publishEvent(new PolicyEvents.PolicyActivated(policy.id()));
  }

  @Transactional
  public void cancel(UUID policyId) {
    Policy policy =
        repository
            .findById(policyId)
            .orElseThrow(() -> new IllegalArgumentException("Policy not found"));
    policy.cancel();
    repository.save(policy);

    events.publishEvent(new PolicyEvents.PolicyCancelled(policy.id()));
  }

  @Transactional(readOnly = true)
  public Policy get(UUID id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Policy not found"));
  }
}
