package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object employeeObject, Errors errors) {
        // write validation logic here
        Employee emp=(Employee) employeeObject;
        if(emp.getFullName()==null || emp.getFullName().trim().isEmpty())
        {
            errors.rejectValue("fullname", "fullname.empty","The fullName is a mandatory field");
        }
        if(emp.getMobileNumber()==null || emp.getMobileNumber()==0){
            errors.rejectValue("mobileNumber","mobileNumber.empty","The mobileNumber is a mandatory field");
        }else if(emp.getMobileNumber().toString().length()!=10){
            errors.rejectValue("mobileNumber", "mobileNumber.invalid","The mobileNumber is a mandatory field");
        }
        if(emp.getEmailId()==null || emp.getEmailId().trim().isEmpty()){
            errors.rejectValue("emailId", "emailId.empty","The emailId is a mandatory field");
        }else if(!emp.getEmailId().contains("@")){
            errors.rejectValue("emailId", "emailId.invalid","The emailId should be in a valid email format");
        }
        if(emp.getDateOfBirth()==null || emp.getDateOfBirth().isEmpty()){
            errors.rejectValue("dateOfBirth", "dateOfBirth.empty","The dateOfBirth is a mandatory field");
        }else if(!emp.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")){
            errors.rejectValue("dateOfBirth", "dateOfBirth.invalid","The dateOfBirth should be in YYYY-MM-DD format");
        }
        
        
        
        
        
        
        
       /* Employee emp = (Employee) employeeObject;
        
        if(emp.getFullName()==null ||emp.getFullName().trim().isEmpty()) {
        	errors.rejectValue("fullName", "fullName.empty", "The fullName is mandotary field");
        }
        if(emp.getMobileNumber() == null || emp.getMobileNumber() == 0) {
        	errors.rejectValue("mobileNumber", "mobileNumber.empty", "Mobile number is mandatory");
        }
        else if(emp.getMobileNumber().toString().length() != 10) {
        	errors.rejectValue("mobileNumber", "mobileNumber.invalid", "Mobilenumber is mandatory");
        }
        
        if(emp.getEmailId() == null || emp.getEmailId().trim().isEmpty()) {
        	errors.rejectValue("emailId", "emailId.empty", "Mandatory");
        }
        else if(!emp.getEmailId().contains("@")) {
        	errors.rejectValue("emailId", "email.invalid", "should be in format");
        }
        
        if(emp.getDateOfBirth()==null || emp.getDateOfBirth().isEmpty()) {
        	errors.rejectValue("dob", "dob.empty", "Mandotory");
        }
        else if(!emp.getDateOfBirth().matches("\\d{4}-\\d{2}-\\{2}")) {
        	errors.rejectValue("dob", "dob.invalid", "Invalid");
        }*/
    }
}
