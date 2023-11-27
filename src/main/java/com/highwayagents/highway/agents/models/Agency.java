package com.highwayagents.highway.agents.models;

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
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int agencyId;

    @Column(name = "agency_name")
    private String agencyName;

    @Column(name = "agency_type")
    private String agencyType;
    @Column(name = "agency_email",unique = true)
    private String emailId;
    @Column(name = "agency_password")
    private String password;
    @Column(name = "agency_phoneNumber")
    private String phoneNumber;
    @Column(name = "agency_city")
    private String city;
    @Column(name = "agency_state")
    private String state;
    @Column(name = "agency_country")
    private String Country;



}
