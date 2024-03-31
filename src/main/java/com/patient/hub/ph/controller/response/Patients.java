package com.patient.hub.ph.controller.response;


import com.patient.hub.ph.command.vo.Patient;

import java.util.List;

public record Patients(List<Patient> patientList) {
}
