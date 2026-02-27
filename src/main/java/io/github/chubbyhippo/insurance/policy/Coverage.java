package io.github.chubbyhippo.insurance.policy;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public final class Coverage {
    private String type;
    private int limit;

    protected Coverage() {
    }

    public Coverage(String type, int limit) {
        if (type == null || type.isBlank()) throw new IllegalArgumentException("Coverage type required");
        if (limit <= 0) throw new IllegalArgumentException("Coverage limit must be > 0");
        this.type = type;
        this.limit = limit;
    }

    public String type() {
        return type;
    }

    public int limit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coverage that)) return false;
        return limit == that.limit && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, limit);
    }
}
