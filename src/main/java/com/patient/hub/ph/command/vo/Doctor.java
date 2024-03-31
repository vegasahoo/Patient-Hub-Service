package com.patient.hub.ph.command.vo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "doctor")
@Getter
@Setter
public class Doctor {
    @Id
    private String doctorId;
    private String name;

}
