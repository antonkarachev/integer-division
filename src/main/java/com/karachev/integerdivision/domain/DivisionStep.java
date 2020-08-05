package com.karachev.integerdivision.domain;

import java.util.Objects;

public class DivisionStep {
    private final int subQuotient;
    private final int closestMultiplier;
    private final int remainder;

    public DivisionStep(int subQuotient, int closestMultiplier, int remainder) {
        this.closestMultiplier = closestMultiplier;
        this.subQuotient = subQuotient;
        this.remainder = remainder;
    }

    public int getSubQuotient() {
        return subQuotient;
    }

    public int getClosestMultiplier() {
        return closestMultiplier;
    }

    public int getRemainder() {
        return remainder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionStep that = (DivisionStep) o;

        return subQuotient == that.subQuotient &&
                closestMultiplier == that.closestMultiplier &&
                remainder == that.remainder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subQuotient, closestMultiplier, remainder);
    }

    @Override
    public String toString() {
        return "DivisionStep:" +
                "subQuotient = " + subQuotient +
                ", closestMultiplier = " + closestMultiplier +
                ", remainder = " + remainder;
    }
}
