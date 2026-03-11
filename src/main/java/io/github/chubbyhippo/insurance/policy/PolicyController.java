package io.github.chubbyhippo.insurance.policy;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/policies")
public class PolicyController {

  private final PolicyService service;

  public PolicyController(PolicyService service) {
    this.service = service;
  }

  record CreatePolicyRequest(
      String customerId,
      String coverageType,
      int coverageLimit,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {}

  private record CreatePolicyResponse(UUID policyId) {}

  @PostMapping
  CreatePolicyResponse create(@RequestBody CreatePolicyRequest req) {
    var id =
        service.createDraft(
            req.customerId(),
            req.coverageType(),
            req.coverageLimit(),
            req.startDate(),
            req.endDate());
    return new CreatePolicyResponse(id);
  }

  @PostMapping("/{id}/activate")
  void activate(@PathVariable UUID id) {
    service.activate(id);
  }

  @PostMapping("/{id}/cancel")
  void cancel(@PathVariable UUID id) {
    service.cancel(id);
  }

  @GetMapping("/{id}")
  Policy get(@PathVariable UUID id) {
    return service.get(id);
  }
}
