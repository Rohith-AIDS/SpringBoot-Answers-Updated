package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee emp = (Employee) target;

        // Validate fullName
        if (emp.getFullName() == null || emp.getFullName().trim().isEmpty()) {
            errors.rejectValue("fullName", "fullName.empty", "The fullName is a mandatory field");
        }

        // Validate mobileNumber
        if (emp.getMobileNumber() == null) {
            errors.rejectValue("mobileNumber", "mobileNumber.empty", "The mobileNumber is a mandatory field");
        } else {
            int length = emp.getMobileNumber().toString().length();
            if (length != 10) {
                errors.rejectValue("mobileNumber", "mobileNumber.invalid", "The mobileNumber is a mandatory field");
            }
        }

        // Validate emailId
        if (emp.getEmailId() == null || emp.getEmailId().trim().isEmpty()) {
            errors.rejectValue("emailId", "emailId.empty", "The emailId is a mandatory field");
        } else if (!emp.getEmailId().contains("@")) {
            errors.rejectValue("emailId", "emailId.invalid", "The emailId should be in a valid email format");
        }

        // Validate dateOfBirth
        if (emp.getDateOfBirth() == null || emp.getDateOfBirth().trim().isEmpty()) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.empty", "The dateOfBirth is a mandatory field");
        } else if (!emp.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.invalid", "The dateOfBirth should be in YYYY-MM-DD format");
        }
    }
}
