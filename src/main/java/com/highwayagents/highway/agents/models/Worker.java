package com.highwayagents.highway.agents.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int workerId;
    private String workerName;
    private String emailId;
    private String password;
    private String phoneNumber;
    private String city;
    private String state;
    private String Country;
    private  int payPerHour;
    private String workerType;
    private int yearsOfExperience;
}
