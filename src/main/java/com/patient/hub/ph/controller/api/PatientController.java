package com.patient.hub.ph.controller.api;

import com.patient.hub.ph.command.model.*;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.command.service.GetPatientCommandService;
import com.patient.hub.ph.controller.response.Patients;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.usecase.service.*;
import com.patient.hub.ph.usecase.service.register.MethodType;
import com.patient.hub.ph.usecase.service.register.UsecaseRegister;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PatientController {

    @GetMapping(value = "/get-patient")
    public Patients getPatientData(@RequestParam String patientId){
        System.out.println(CommandRegister.MAPPER_SERVICE_MAP.values());
        GetPatientCommand getPatientCommand = (GetPatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.GET).createCommand(patientId);
        ArrayList<Patient> patients = new ArrayList<>();
        GetPatient patientService = (GetPatient) UsecaseRegister.get(MethodType.GET);
        patients.add((Patient) patientService.handle(getPatientCommand));
        return new Patients(patients);
    }

    @GetMapping(value = "/get-all-patient")
    public Patients getAllPatient(){
        GetPatient patientService = (GetPatient) UsecaseRegister.get(MethodType.GET);
        List<Patient> patientList = patientService.getAllPatient();
        return new Patients(patientList);
    }

    @PostMapping(value = "/register-patient")
    public String registerPatient(@RequestBody String patientData){
        CreatePatientCommand command = (CreatePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.CREATE).createCommand(patientData);
        CreatePatient patientService = (CreatePatient) UsecaseRegister.get(MethodType.CREATE);
        patientService.handle(command);
        return "Success";
    }

    @PutMapping(value = "/update-patient")
    public String updatePatientData(@RequestParam String patientId, @RequestBody String patientData){
        UpdatePatientCommand command = (UpdatePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.UPDATE).createCommand(patientId, patientData);
        UpdatePatient patientService = (UpdatePatient) UsecaseRegister.get(MethodType.UPDATE);
        patientService.handle(command);
        return "Success";
    }

    @PatchMapping(value = "/discharge-patient")
    public String dischargePatient(@RequestParam String patientId){
        UpdatePatientCommand command = (UpdatePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.UPDATE).createCommand(patientId);
        UpdatePatient patientService = (UpdatePatient) UsecaseRegister.get(MethodType.UPDATE);
        patientService.handle(command);
        return "Success";
    }

    @DeleteMapping(value = "/delete-patient")
    public String deletePatientData(@RequestParam String patientId){
        DeletePatientCommand command = (DeletePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.DELETE).createCommand(patientId);
        DeletePatient patientService = (DeletePatient) UsecaseRegister.get(MethodType.DELETE);
        patientService.handle(command);
        return "Success";
    }
}
