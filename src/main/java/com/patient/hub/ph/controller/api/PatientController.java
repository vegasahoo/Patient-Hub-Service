package com.patient.hub.ph.controller.api;

import com.patient.hub.ph.command.model.patient.CreatePatientCommand;
import com.patient.hub.ph.command.model.patient.DeletePatientCommand;
import com.patient.hub.ph.command.model.patient.GetPatientCommand;
import com.patient.hub.ph.command.model.patient.UpdatePatientCommand;
import com.patient.hub.ph.command.model.user.CreateUserCommand;
import com.patient.hub.ph.command.model.user.GetUserCommand;
import com.patient.hub.ph.command.register.CommandRegister;
import com.patient.hub.ph.controller.response.Patients;
import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.repository.userinfo.dto.UserInfo;
import com.patient.hub.ph.usecase.service.patient.CreatePatient;
import com.patient.hub.ph.usecase.service.patient.DeletePatient;
import com.patient.hub.ph.usecase.service.patient.GetPatient;
import com.patient.hub.ph.usecase.service.patient.UpdatePatient;
import com.patient.hub.ph.usecase.service.register.MethodType;
import com.patient.hub.ph.usecase.service.register.PatientUseCaseRegister;
import com.patient.hub.ph.usecase.service.register.UserUseCaseRegister;
import com.patient.hub.ph.usecase.service.user.CreateUser;
import com.patient.hub.ph.usecase.service.user.GetUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class PatientController {


    @GetMapping(value = "/get-patient")
    @PreAuthorize("hasAuthority('ROLE_patient')")
    public Patients getPatientData(@RequestParam String patientId){
        System.out.println(CommandRegister.MAPPER_SERVICE_MAP.values());
        GetPatientCommand getPatientCommand = (GetPatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.GET).createCommand(patientId);
        ArrayList<Patient> patients = new ArrayList<>();
        GetPatient patientService = (GetPatient) PatientUseCaseRegister.get(MethodType.GET);
        patients.add((Patient) patientService.handle(getPatientCommand));
        return new Patients(patients);
    }

    @GetMapping(value = "/get-all-patient")
    public Patients getAllPatient(){
        GetPatient patientService = (GetPatient) PatientUseCaseRegister.get(MethodType.GET);
        List<Patient> patientList = patientService.getAllPatient();
        return new Patients(patientList);
    }

    @PostMapping(value = "/register-patient")
    public String registerPatient(@RequestBody String patientData){
        CreatePatientCommand command = (CreatePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.CREATE).createCommand(patientData);
        CreatePatient patientService = (CreatePatient) PatientUseCaseRegister.get(MethodType.CREATE);
        patientService.handle(command);
        return "Success";
    }

    @PutMapping(value = "/update-patient")
    public String updatePatientData(@RequestParam String patientId, @RequestBody String patientData){
        UpdatePatientCommand command = (UpdatePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.UPDATE).createCommand(patientId, patientData);
        UpdatePatient patientService = (UpdatePatient) PatientUseCaseRegister.get(MethodType.UPDATE);
        patientService.handle(command);
        return "Success";
    }

    @PatchMapping(value = "/discharge-patient")
    public String dischargePatient(@RequestParam String patientId){
        UpdatePatientCommand command = (UpdatePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.UPDATE).createCommand(patientId);
        UpdatePatient patientService = (UpdatePatient) PatientUseCaseRegister.get(MethodType.UPDATE);
        patientService.handle(command);
        return "Success";
    }

    @DeleteMapping(value = "/delete-patient")
    @PreAuthorize("hasAuthority('ROLE_admin')")
    public String deletePatientData(@RequestParam String patientId){
        DeletePatientCommand command = (DeletePatientCommand) CommandRegister
                .get(com.patient.hub.ph.command.register.MethodType.DELETE).createCommand(patientId);
        DeletePatient patientService = (DeletePatient) PatientUseCaseRegister.get(MethodType.DELETE);
        patientService.handle(command);
        return "Success";
    }

    @PostMapping(value = "/create-user")
    public String createUser(@RequestBody String userInfo){
        CreateUserCommand command = (CreateUserCommand) CommandRegister.get(com.patient.hub.ph.command.register.MethodType.CREATEUSER)
                .createCommand(userInfo);
        CreateUser createUserService = (CreateUser) UserUseCaseRegister.get(MethodType.CREATE);
        createUserService.handle(command);
        return "Success";
    }

    @GetMapping(value = "/get-user")
    public UserInfo getUserByName(@RequestParam String name){
        GetUserCommand command = (GetUserCommand) CommandRegister.get(com.patient.hub.ph.command.register.MethodType.GETUSER)
                .createCommand(name);
        GetUser getUserService = (GetUser) UserUseCaseRegister.get(MethodType.GET);
        Optional<UserInfo> userInfo = (Optional<UserInfo>) getUserService.handle(command);
        return userInfo.orElse(null);

    }
}
