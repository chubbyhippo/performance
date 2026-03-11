package io.github.chubbyhippo.insurance.underwriting;

import io.github.chubbyhippo.insurance.policy.Coverage;
import io.github.chubbyhippo.insurance.policy.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Service
public class UnderwritingService {

    public Money calculatePremium(Coverage coverage) {
        var base =
                switch (coverage.type().toUpperCase()) {
                    case "AUTO" -> new BigDecimal("60.00");
                    case "HOME" -> new BigDecimal("45.00");
                    default -> new BigDecimal("30.00");
                };

        var limitFactor =
                BigDecimal.valueOf(coverage.limit())
                        .divide(BigDecimal.valueOf(10_000), 6, RoundingMode.HALF_UP);

        var factor = BigDecimal.ONE.add(limitFactor);

        return new Money(base.multiply(factor), Currency.getInstance("USD"));
    }
}
