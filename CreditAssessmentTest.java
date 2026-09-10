package com.loan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAssessmentTest {
    private final CreditAssessment assessment = new CreditAssessment();

    @Test
    public void testBoundaryEligibleLowRisk() throws Exception {
        Customer customer = new Customer("C01", "John", 21, "ID123", 5000.0, 1000.0); // DTI = 0.20
        LoanApplication app = new LoanApplication("A01", customer, 25000.0, 760);
        
        var result = assessment.evaluateLoan(app);
        assertEquals("Low Risk", result.riskStatus);
        assertEquals(50000.0, result.maxPermissibleLoan);
    }

    @Test
    public void testHighRiskMultipleRejectionReasons() throws Exception {
        Customer customer = new Customer("C02", "Jane", 19, "ID456", 1500.0, 900.0); // Underage, Low Income, DTI = 0.60
        LoanApplication app = new LoanApplication("A02", customer, 30000.0, 550); // Low credit score, over limit
        
        var result = assessment.evaluateLoan(app);
        assertEquals("High Risk/Rejected", result.riskStatus);
        assertTrue(result.rejectionReasons.size() > 1);
    }
}
