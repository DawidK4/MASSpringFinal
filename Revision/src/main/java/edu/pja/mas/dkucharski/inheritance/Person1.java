package edu.pja.mas.dkucharski.inheritance;

import edu.pja.mas.dkucharski.attributes.Employee;
import edu.pja.mas.dkucharski.utils.ObjectPlusPlus;

import java.time.LocalDate;

public class Person1 extends ObjectPlusPlus {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    /**
     * Creates a person as an employee.
     */
    public Person1(String firstName, String lastName, LocalDate birthDate, boolean medicalTest) {
        super();

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;

        // "Changes" a person into an employee
        addEmployee(medicalTest);
    }

    public void addEmployee(boolean medicalTest){
        // Creation of the employee part
        Employee p = new Employee(medicalTest);

        // Adding an employee as a link
        // We use a method provided by the ObjectPlusPlus
        this.addLink(roleNameEmployee, roleNameGeneralization, p);
    }

    public void addStudent(int number) throws Exception {
        // Creation of the student part
        Student s = new Student(number);

        // Adding a student as a link
        // We use a method provided by the ObjectPlusPlus
        this.addLink(roleNameStudent, roleNameGeneralization, s);
    }

    private final static String roleNameEmployee = "specializationEmployee";
    private final static String roleNamePensioner = "specializationPensioner";

    public boolean hasMedicalTest() throws Exception {
        // get an employee object
        try {
            ObjectPlusPlus[] obj = this.getLinks(roleNameEmployee);
            return ((Employee) obj[0]).isMedicalTest();
        } catch (Exception e) {
            // Probably this is an exception telling that this is not an employee
            // (we should introduce different exception classes)
            throw new Exception("The object is not an employee!");
        }
    }

    public int getNumber() throws Exception {
        // get a student object
        try {
            ObjectPlusPlus[] obj = this.getLinks(roleNameStudent);
            return ((Student) obj[0]).getNumber();
        } catch (Exception e) {
            // Probably this is an exception telling that this is not a student
            // (we should introduce different exception classes)
            throw new Exception("The object is not a student!");
        }
    }

    public float getIncome() throws Exception {
        float income = 0.0f;

        if (this.anyLink(roleNameEmployee)) {
            // Employee
            ObjectPlusPlus[] obj = this.getLinks(roleNameEmployee);

            // ==> add employee's income
            income += ((Employee) obj[0]).getIncome();
        }

        if (this.anyLink(roleNamePensioner)) {
            // Pensioner
            ObjectPlusPlus[] obj = this.getLinks(roleNamePensioner);

            // ==> add pensioner's income
            income += ((Pensioner) obj[0]).getIncome();
        }

        if (this.anyLink(roleNameStudent)) {
            // Student
            ObjectPlusPlus[] obj = this.getLinks(roleNameStudent);

            // ==> add student's income
            income += ((Student) obj[0]).getIncome();
        }

        return income;
    }


}
