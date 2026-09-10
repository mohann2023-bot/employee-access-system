package com.loan;

import java.util.ArrayList;
import java.util.List;

public class CreditAssessment {
    // Defined rules thresholds
    private static final int MIN_AGE = 21;
    private static final double MIN_INCOME = 2000.0;
    private static final int MIN_CREDIT_SCORE = 600;
    private static final double MAX_DTI_RATIO = 0.50; // 50% max DTI

    public static class AssessmentResult {
        public String riskStatus; // Low Risk, Medium Risk, High Risk/Rejected
        public double maxPermissibleLoan;
        public List<String> rejectionReasons = new ArrayList<>();
    }

    public AssessmentResult evaluateLoan(LoanApplication app) throws InvalidLoanDataException {
        Customer customer = app.getCustomer();

        // Input Validations
        if (customer.getGovtId() == null || customer.getGovtId().isBlank()) {
            throw new InvalidLoanDataException("Government ID cannot be blank.");
        }
        if (customer.getMonthlyIncome() < 0 || app.getRequestedAmount() < 0) {
            throw new InvalidLoanDataException("Financial amounts cannot be negative.");
        }

        AssessmentResult result = new AssessmentResult();
        
        // 1. Calculate Maximum Permissible Loan (Example rule: 10x monthly income)
        result.maxPermissibleLoan = customer.getMonthlyIncome() * 10;

        // 2. Calculate Debt-to-Income (DTI) Ratio
        double dti = customer.getExistingDebtObligations() / customer.getMonthlyIncome();

        // 3. Evaluate Rule Violations
        if (customer.getAge() < MIN_AGE) {
            result.rejectionReasons.add("Customer must be at least 21 years old.");
        }
        if (customer.getMonthlyIncome() < MIN_INCOME) {
            result.rejectionReasons.add("Monthly income is below minimum threshold.");
        }
        if (app.getRequestedAmount() > result.maxPermissibleLoan) {
            result.rejectionReasons.add("Requested amount exceeds the income-dependent limit.");
        }
        if (app.getCreditScore() < MIN_CREDIT_SCORE) {
            result.rejectionReasons.add("Credit score is below the minimum requirement.");
        }
        if (dti > MAX_DTI_RATIO) {
            result.rejectionReasons.add("Debt-to-Income ratio exceeds maximum permissible limit.");
        }

        // 4. Determine Risk Status
        if (!result.reasons.isEmpty()) {
            result.riskStatus = "High Risk/Rejected";
        } else {
            // Low Risk: High credit score (>=750) and low DTI (<=0.30)
            if (app.getCreditScore() >= 750 && dti <= 0.30) {
                result.riskStatus = "Low Risk";
            } else {
                result.riskStatus = "Medium Risk";
            }
        }

        return result;
    }
}
