package com.israeldago.appUsersWS.entities.db;

import com.israeldago.appUsersWS.entities.enums.AppRole;
import lombok.Data;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ROLES")
@XmlRootElement
@Data
public class RoleDB implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "APP_ROLE")
    @Enumerated(EnumType.STRING)
    private AppRole applicationRole;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private UserDB user;
}
