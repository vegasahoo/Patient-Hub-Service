package com.patient.hub.ph.repository;

import com.patient.hub.ph.command.vo.Patient;
import com.patient.hub.ph.command.vo.Status;
import com.patient.hub.ph.exception.MyCustomException;
import com.patient.hub.ph.exception.ResourceNotFoundException;
import com.patient.hub.ph.usecase.port.IPatientRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@CacheConfig(cacheNames = "patient")
public class PatientDBRepoImpl implements IPatientRepo{

    private static final Logger logger = LoggerFactory.getLogger(PatientDBRepoImpl.class);

    private final IPatientDBRepo patientDBRepo;

    public PatientDBRepoImpl(IPatientDBRepo patientDBRepo) {
        this.patientDBRepo = patientDBRepo;
    }

    @Override
    @Cacheable(key = "#patientId")
    public Patient getPatientById(String patientId) {
        try{
            Optional<Patient> patient = patientDBRepo.findById(patientId);
            if(patient.isPresent()){
                logger.info("Patient fetched successfully from db");
                return patient.get();
            }
            else {
                throw new ResourceNotFoundException("Patient details not found in db");
            }
        }
        catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }

    @Override
    public List<Patient> getAllPatient() {
        try{
            List<Patient> allPatients = patientDBRepo.findAll();
            logger.info("All patient details fetched");
            return allPatients;
        }
        catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }


    @Override
    @Transactional
    @CachePut(key = "#patient.patientId")
    public Patient registerPatient(Patient patient) {
        try{
            Patient newPatient = patientDBRepo.save(patient);
            logger.info("Patient created successfully");
            return newPatient;
        }
        catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }

    @Override
    @CachePut(key = "#patientId")
    public Patient updatePatientData(String patientId, Patient updatedPatient) {
        try{
            Optional<Patient> patient = patientDBRepo.findById(patientId);
            if(patient.isPresent()){
                logger.info("Patient data updated successfully");
                return patientDBRepo.save(updatePatientMapper(patient.get(), updatedPatient));
            }
            else {
                throw new ResourceNotFoundException("Patient Not found for the given id");
            }
        }
        catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }

    @Override
    @CachePut(key = "#patientId")
    public Patient dischargePatient(String patientId) {
        try{
            Optional<Patient> patient = patientDBRepo.findById(patientId);
            if(patient.isPresent()){
                patient.get().setStatus(Status.DISCHARGED);
                logger.info("Patient status updated to Discharged");
                return patientDBRepo.save(patient.get());
            }
            else{
                throw new ResourceNotFoundException("Patient Not found for the given id");
            }
        }
        catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }

    @Override
    @CacheEvict(key = "#patientId")
    public void deletePatientById(String patientId) {
        try{
            patientDBRepo.deleteById(patientId);;
            logger.info("Patient deleted successfully from db");
        }
        catch (Exception ex){
            throw new MyCustomException(ex.getMessage());
        }
    }


    private Patient updatePatientMapper(Patient source, Patient updateRequest){
        source.setLocality(updateRequest.getLocality());
        source.setName(updateRequest.getName());
        source.setAge(updateRequest.getAge());
        source.setMobile(updateRequest.getMobile());
        source.setDoctor(updateRequest.getDoctor());
        source.setDetailsOfDisease(updateRequest.getDetailsOfDisease());
        return source;
    }
}
