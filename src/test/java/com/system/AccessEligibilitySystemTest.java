package com.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccessEligibilitySystemTest {
    private final AccessEligibilitySystem system = new AccessEligibilitySystem();

    @Test
    public void testNormalEligibleScenario() throws Exception {
        Employee emp = new Employee("E001", "Alice", 25, "IT", "Active", 3, true);
        var result = system.evaluateAccess(emp, 2);
        assertEquals("Eligible", result.status);
    }

    @Test
    public void testMultipleFailureScenario() throws Exception {
        Employee emp = new Employee("E002", "Bob", 19, "Marketing", "Active", 1, false);
        var result = system.evaluateAccess(emp, 2);
        assertEquals("Not Eligible", result.status);
        assertTrue(result.reasons.size() >= 3); 
    }
}
