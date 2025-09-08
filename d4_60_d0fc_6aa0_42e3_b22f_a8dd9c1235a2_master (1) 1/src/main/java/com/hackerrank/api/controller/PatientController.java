package com.hackerrank.api.controller;

import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.exception.ElementNotFoundException;
import com.hackerrank.api.model.Patient;
import com.hackerrank.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
  private final PatientService patientService;

  @Autowired
  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping
  public ResponseEntity<List<Patient>> getAllPatient() {
List<Patient> pat = patientService.getAllPatient();     
return new ResponseEntity<>(pat,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
    if(patient.getId()!=null){
      return ResponseEntity.status(400).build();
    }
    return new ResponseEntity<>(patientService.createNewPatient(patient), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {

   
     try{
          Patient pat = patientService.getPatientById(id);
          return ResponseEntity.status(200).body(pat);
     }
     catch(BadRequestException ex){
      return ResponseEntity.status(400).build();
     }
     catch(ElementNotFoundException ex){
      return ResponseEntity.status(404).build(); 
     }

   
}
}
