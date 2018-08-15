package com.israeldago.appUsersWS.entities.dto;

import com.israeldago.appUsersWS.entities.db.UserDB;
import lombok.*;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@XmlRootElement(name = "User")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"id","username", "password","firstName", "lastName", "dateOfBirth","identityCardNumber", "registrationDate"})
@Data
@NoArgsConstructor
public class UserDTO implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    @XmlElement
    private int id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private LocalDate dateOfBirth;
    @XmlElement
    private String identityCardNumber;
    @XmlElement
    private String username;
    @XmlElement
    private String password;
    @XmlElement
    private LocalDateTime registrationDate;

    @Builder
    public UserDTO(int id, String firstName, String lastName, LocalDate dateOfBirth, String identityCardNumber, String username, String password, LocalDateTime registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.identityCardNumber = identityCardNumber;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public UserDB toDB(){
        return UserDB.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .identityCardNumber(identityCardNumber)
                .username(username)
                .password(password)
                .registrationDate(registrationDate)
                .build();
    }
}
