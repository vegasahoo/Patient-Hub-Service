package com.patient.hub.ph.command.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String patientId;
    private String Name;
    private int age;
    private String sex;
    private long mobile;
    private String locality;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String detailsOfDisease;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
}
