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
public class Contractor implements UserDetails  {

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

    @Enumerated(value=EnumType.STRING)
    Role role;
    @OneToMany(mappedBy = "contractor")
    private List<Token> tokens;

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
