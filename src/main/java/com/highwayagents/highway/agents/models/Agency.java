package com.highwayagents.highway.agents.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
@Builder
public class Agency implements UserDetails {

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

    @Enumerated(value=EnumType.STRING)
    Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
