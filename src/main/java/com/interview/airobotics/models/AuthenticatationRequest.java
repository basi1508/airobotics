package com.interview.airobotics.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatationRequest {
    private String userName;
    private String password;

    public AuthenticatationRequest(){

    }
    public AuthenticatationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
