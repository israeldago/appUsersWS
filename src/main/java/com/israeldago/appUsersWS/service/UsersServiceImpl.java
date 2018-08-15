package com.israeldago.appUsersWS.service;

import com.israeldago.appUsersWS.entities.db.UserDB;
import com.israeldago.appUsersWS.entities.dto.UserDTO;
import com.israeldago.appUsersWS.repository.UsersRepository;
import com.israeldago.appUsersWS.service.exceptions.CredentialsCheckException;
import com.israeldago.appUsersWS.service.exceptions.OffBoardingException;
import com.israeldago.appUsersWS.service.exceptions.RegistrationUserException;
import com.israeldago.appUsersWS.service.shared.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@WebService(endpointInterface = "com.israeldago.appUsersWS.service.shared.UsersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository repository;


    @Override
    public UserDTO registerUser(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String identityCardNumber) {
        //TODO: validation
        if(findUserBy(username) != null){
            throw new RegistrationUserException("Username already taken");
        }
        if(retrieveUserBy(identityCardNumber) != null){
            throw new RegistrationUserException("The given identityCardNumber already in use");
        }
        return Optional.of(repository.save(newUserFromGivenParams(username, password, firstName, lastName, dateOfBirth, identityCardNumber)))
                        .map(UserDB::toDTO)
                        .orElseThrow(RegistrationUserException::new);

    }

    private UserDB newUserFromGivenParams(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String identityCardNumber) {
        return UserDB.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .dateOfBirth(dateOfBirth)
                        .identityCardNumber(identityCardNumber)
                        .username(username)
                        .password(password)
                        .registrationDate(LocalDateTime.now())
                        .build();
    }

    @Override
    public UserDTO checkUserWithCredentials(String username, String password) {
        return Optional.of(findUserBy(username))
                        .filter(userDB -> userDB.getPassword().equals(password))
                        .orElseThrow(() -> new CredentialsCheckException("Bad Credentials"));
    }

    @Override
    public UserDTO findUserBy(String username) {
        return repository.findByUsername(username)
                            .map(UserDB::toDTO)
                            .orElse(null);
    }

    @Override
    public UserDTO retrieveUserBy(String identityCardNumber) {
        return repository.findByIdentityCardNumber(identityCardNumber)
                .map(UserDB::toDTO)
                .orElse(null);
    }

    @Override
    public Boolean offBoardUser(int id) {
        UserDB offboardedUser = repository.findById(id).orElseThrow(() -> new OffBoardingException("No user found with the given id ==> "+id));
        repository.delete(offboardedUser);
        return ! repository.findById(id).isPresent();
    }

    @Override
    public Stream<UserDTO> findAllUsers() {
        return repository.findAll().stream()
                          .map(UserDB::toDTO);
    }
}
