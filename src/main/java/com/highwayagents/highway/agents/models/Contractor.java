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
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contractorId;
    @Column(name="contractor_name")
    private String contractorName;
    @Column(name="email_Id",unique = true)
    private String emailId;
    @Column(name="contractor_password")
    private String password;
    @Column(name="contractor_ph_no")
    private String PhoneNumber;
    @Column(name="contractor_city")
    private String City;
    @Column(name="contractor_state")
    private String State;
    @Column(name="contractor_country")
    private String Country;


}
