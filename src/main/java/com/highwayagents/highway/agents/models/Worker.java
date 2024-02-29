package com.highwayagents.highway.agents.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Worker {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int workerId;

    @Column(name = "workerName")
    private String workerName;
    @Column(name = "emailId")
    private String emailId;
    @Column(name = "password")
    private String password;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String Country;
    @Column(name = "payPerHour")
    private  int payPerHour;
    @Column(name = "workerType")
    private String workerType;
    @Column(name = "yearsOfExperience")
    private int yearsOfExperience;
}
