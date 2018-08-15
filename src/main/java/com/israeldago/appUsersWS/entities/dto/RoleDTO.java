package com.israeldago.appUsersWS.entities.dto;

import com.israeldago.appUsersWS.entities.enums.AppRole;
import lombok.Value;

@Value
public class RoleDTO implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private AppRole applicationRole;
    private UserDTO user;
}
