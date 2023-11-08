package com.highwayagents.highway.agents.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agency {
    private int agencyId;
    private String agencyName;

    private String emailId;
    private String password;

    private String phoneNumber;
    private String city;
    private String state;
    private String Country;



}
