package io.github.chubbyhippo.insurance.policy;

import java.util.UUID;

public final class PolicyEvents {

  private PolicyEvents() {}

  public record PolicyActivated(UUID policyId) {}

  public record PolicyCancelled(UUID policyId) {}
}
