package edu.pja.mas.dkucharski.attributes;

import java.util.Optional;

/**
 * Represents an employee with an optional extra bonus.
 * The class demonstrates the use of Optional to handle the extra bonus.
 * THIS PRESENTS AN IMPLEMENTATION OF AN OPTIONAL ATTRIBUTE IN A CLASS.
 */
public class Employee {
    // ...

    private Optional<Double> extraBounus = Optional.empty(); // initialization with empty Optional
    private double income;

    public Optional<Double> getExtraBounus() {
        return extraBounus;
    }

    public void setExtraBounus(Optional<Double> extraBounus) {
        this.extraBounus = extraBounus;
    }

    public Optional<Double> getExtraBonus() {
        return this.extraBounus;
    }

    public double getIncome() {
        return getIncome() + getExtraBonus().orElse(0.0);
    }
}
