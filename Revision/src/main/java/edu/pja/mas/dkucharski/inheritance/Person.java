package edu.pja.mas.dkucharski.inheritance;

import java.time.LocalDate;
import java.util.EnumSet;

enum PersonType {
    Person, Employee, Student, Pensioner
}

public class Person {
    private String firstName;
    private  String lastName;
    private LocalDate birthDate;

    private boolean medicalTest;
    private int number;

    // We need to use EnumSet rather then PersonType because we would like
    // to have a possibility of storing combinations of the Person, e.g. Employee
    //+ Student
    private EnumSet<PersonType> personKind = EnumSet.of(PersonType.Person);

    public boolean hasMedicalTest() throws Exception{
        if (personKind.contains(PersonType.Employee)) {
            return medicalTest;
        }

        throw new Exception("This person is not an employee!");
    }

    public void setMedicalTest(boolean medicalTest) throws Exception{
        if (personKind.contains(PersonType.Employee)) {
            this.medicalTest = medicalTest;
        } else {
            throw new Exception("This person is not an employee!");
        }
    }
}
