package com.CRUD.User;

import lombok.Data;

@Data
public class UserRequest {
    private String fullName;
    private String email;
    private String gender;
    private String birtDay;
    private String address;
}
