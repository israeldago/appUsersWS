package com.israeldago.appUsersWS.service;

import com.israeldago.appUsersWS.entities.db.RoleDB;
import com.israeldago.appUsersWS.entities.db.UserDB;
import com.israeldago.appUsersWS.entities.dto.UserDTO;
import com.israeldago.appUsersWS.entities.enums.AppRole;
import com.israeldago.appUsersWS.repository.RolesRepository;
import com.israeldago.appUsersWS.repository.UsersRepository;
import com.israeldago.appUsersWS.service.exceptions.CredentialsCheckException;
import com.israeldago.appUsersWS.service.exceptions.OffBoardingException;
import com.israeldago.appUsersWS.service.exceptions.RegistrationUserException;
import com.israeldago.appUsersWS.service.shared.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.jws.WebService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toCollection;

@WebService(endpointInterface = "com.israeldago.appUsersWS.service.shared.UsersService")
@Service
public class UsersServiceImpl implements UsersService {

    private final RolesRepository rolesRepository;
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Transactional
    public UserDTO registerUser(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String identityCardNumber) {
        //TODO: validation
        if(findUserBy(username) != null){
            throw new RegistrationUserException("Username already taken");
        }
        if(retrieveUserBy(identityCardNumber) != null){
            throw new RegistrationUserException("The given identityCardNumber already in use");
        }
        return Optional.of(usersRepository.save(newUserFromGivenParams(username, password, firstName, lastName, dateOfBirth, identityCardNumber)))
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
        return usersRepository.findByUsername(username)
                .map(UserDB::toDTO)
                .orElse(null);
    }

    @Override
    public UserDTO retrieveUserBy(String identityCardNumber) {
        return usersRepository.findByIdentityCardNumber(identityCardNumber)
                .map(UserDB::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public Boolean offBoardUser(int id) {
        UserDB offBoardedUser = usersRepository.findById(id).orElseThrow(() -> new OffBoardingException("No user found with the given id ==> "+id));
        usersRepository.delete(offBoardedUser);
        return ! usersRepository.findById(id).isPresent();
    }

    @Override
    public Stream<UserDTO> findAllUsers() {
        return usersRepository.findAll().stream()
                          .map(UserDB::toDTO);
    }

    @Override
    public Set<AppRole> findUserRoles(Integer id) {
        UserDB userDB = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("No user found with the given id"));
        return rolesRepository.findByUser(userDB).stream()
                .map(RoleDB::getApplicationRole)
                .collect(toCollection(LinkedHashSet::new));
    }

}
