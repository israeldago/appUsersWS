package com.israeldago.appUsersWS.entities.dto;

import com.israeldago.appUsersWS.entities.db.UserDB;
import com.israeldago.appUsersWS.entities.enums.AppRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.ElementCollection;
import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@XmlRootElement(name = "User")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = {"id","username", "password","firstName", "lastName", "dateOfBirth","identityCardNumber", "registrationDate", "roleCollection"})
@Data
@NoArgsConstructor
@ToString(exclude = "roleCollection")
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
    @ElementCollection
    private Set<AppRole> roleCollection;

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
