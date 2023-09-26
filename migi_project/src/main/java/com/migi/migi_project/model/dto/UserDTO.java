package com.migi.migi_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String phoneNumber;
    private String address;
    private String email;
    private Timestamp createDate;
    private Collection<String> roles;

    // update information
    private String firstName;
    private String lastName;
    private String image;
}
