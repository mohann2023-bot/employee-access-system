package com.loan;

public class LoanApplication {
    private String applicationId;
    private Customer customer;
    private double requestedAmount;
    private int creditScore;

    public LoanApplication(String applicationId, Customer customer, double requestedAmount, int creditScore) {
        this.applicationId = applicationId;
        this.customer = customer;
        this.requestedAmount = requestedAmount;
        this.creditScore = creditScore;
    }

    public String getApplicationId() { return applicationId; }
    public Customer getCustomer() { return customer; }
    public double getRequestedAmount() { return requestedAmount; }
    public int getCreditScore() { return creditScore; }
}
