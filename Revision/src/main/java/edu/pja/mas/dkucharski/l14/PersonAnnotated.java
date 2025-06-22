package edu.pja.mas.dkucharski.l14;

import java.time.LocalDate;

public class PersonAnnotated {
    @GUIGenerateAttribute
    private String firstName;
    @GUIGenerateAttribute
    private String lastName;
    @GUIGenerateAttribute
    private LocalDate birthDate;
    @GUIGenerateAttribute
    private boolean higherEducation;
    @GUIGenerateAttribute
    private String remarks;
    @GUIGenerateAttribute
    private int SSN;
    @GUIGenerateAttribute
    private double annualIncome;

    @GUIGenerateMethod
    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        return java.time.Period.between(birthDate, java.time.LocalDate.now()).getYears();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isHigherEducation() {
        return higherEducation;
    }

    public void setHigherEducation(boolean higherEducation) {
        this.higherEducation = higherEducation;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }
}