package io.github.chubbyhippo.insurance.policy;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

@Embeddable
public final class Money {
    private BigDecimal amount;
    private String currency;

    protected Money() {
    }

    public Money(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null) throw new IllegalArgumentException("Money requires amount + currency");
        if (amount.scale() > 2) amount = amount.setScale(2);
        if (amount.signum() < 0) throw new IllegalArgumentException("Money cannot be negative");
        this.amount = amount;
        this.currency = currency.getCurrencyCode();
    }

    public BigDecimal amount() {
        return amount;
    }

    public Currency currency() {
        return Currency.getInstance(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
