package com.israeldago.appUsersWS.entities.db;

import com.israeldago.appUsersWS.entities.dto.UserDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Data @NoArgsConstructor
public class UserDB implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "IDENTITY_CARD_NUMBER")
    private String identityCardNumber;
    private String username;
    private String password;
    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    @Builder
    public UserDB(Integer id, String firstName, String lastName, LocalDate dateOfBirth, String identityCardNumber, String username, String password, LocalDateTime registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.identityCardNumber = identityCardNumber;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public UserDTO toDTO(){
        return UserDTO.builder()
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