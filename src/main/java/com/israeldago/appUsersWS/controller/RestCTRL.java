package com.israeldago.appUsersWS.controller;

import com.israeldago.appUsersWS.entities.dto.UserDTO;
import com.israeldago.appUsersWS.entities.enums.AppRole;
import com.israeldago.appUsersWS.service.shared.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/users")
public class RestCTRL {
    @Autowired
    private UsersService service;

    @GetMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO registerUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String identityCardNumber = request.getParameter("identityCardNumber");
        String dateAString = request.getParameter("dateOfBirth");
        LocalDate dateOfBirth = dateAString != null
                ? LocalDate.parse(dateAString, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                : LocalDate.of(1900, 1,01);
        return service.registerUser(username, password, firstName, lastName, dateOfBirth, identityCardNumber);
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDTO findUserBy(@PathVariable String username) {
        return service.findUserBy(username);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Stream<UserDTO> findAllUsers() {
        return service.findAllUsers();
    }

    @GetMapping(path = "{userId}/roles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Set<AppRole> findUserRoles(@PathVariable Integer userId) {
        return service.findUserRoles(userId);
    }

}
