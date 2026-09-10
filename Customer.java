package com.loan;

public class Customer {
    private String id;
    private String name;
    private int age;
    private String govtId;
    private double monthlyIncome;
    private double existingDebtObligations;

    public Customer(String id, String name, int age, String govtId, double monthlyIncome, double existingDebtObligations) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.govtId = govtId;
        this.monthlyIncome = monthlyIncome;
        this.existingDebtObligations = existingDebtObligations;
    }

    public String getId() { return id; }
    public int getAge() { return age; }
    public String getGovtId() { return govtId; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public double getExistingDebtObligations() { return existingDebtObligations; }
}
