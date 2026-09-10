package com.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessEligibilitySystem {
    private static final List<String> AUTHORIZED_DEPARTMENTS = Arrays.asList("IT", "HR", "Finance", "Administration");

    public static class EvaluationResult {
        public String status;
        public List<String> reasons = new ArrayList<>();
        public EvaluationResult(String status) { this.status = status; }
    }

    public EvaluationResult evaluateAccess(Employee emp, int requestedResourceLevel) throws InvalidEmployeeDataException {
        if (emp.getId() == null || emp.getId().isBlank() || emp.getName() == null || emp.getName().isBlank()) {
            throw new InvalidEmployeeDataException("Employee ID and Name cannot be blank.");
        }
        if (emp.getAge() < 0) {
            throw new InvalidEmployeeDataException("Age cannot be negative.");
        }

        EvaluationResult result = new EvaluationResult("Eligible");

        if (emp.getAge() < 21) result.reasons.add("Employee must be at least 21 years old.");
        if (!AUTHORIZED_DEPARTMENTS.contains(emp.getDepartment())) result.reasons.add("Department not authorized.");
        if (!"Active".equalsIgnoreCase(emp.getEmploymentStatus())) result.reasons.add("Employment status is not active.");
        if (!emp.isIdValid()) result.reasons.add("Employee ID is invalid.");
        if (emp.getSecurityClearanceLevel() < requestedResourceLevel) result.reasons.add("Insufficient security clearance level.");

        if (!result.reasons.isEmpty()) {
            if (!emp.isIdValid() || !"Active".equalsIgnoreCase(emp.getEmploymentStatus())) {
                result.status = "Not Eligible";
            } else {
                result.status = "Conditionally Eligible";
            }
        }
        return result;
    }
}
