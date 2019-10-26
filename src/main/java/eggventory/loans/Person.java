package eggventory.loans;

import java.util.ArrayList;

public class Person {

    private String matricNo;
    private String name;
    private ArrayList<Loan> personLoans;

    public Person(String matricNo, String name) {
        this.matricNo = matricNo;
        this.name = name;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public String getName() {
        return name;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public void setName(String name) {
        this.name = name;
    }
}
