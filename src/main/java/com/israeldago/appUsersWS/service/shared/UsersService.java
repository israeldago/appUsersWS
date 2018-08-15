package com.israeldago.appUsersWS.service.shared;

import com.israeldago.appUsersWS.entities.dto.UserDTO;
import com.israeldago.appUsersWS.entities.enums.AppRole;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

@WebService(serviceName = "UsersWS")
@SOAPBinding(style= SOAPBinding.Style.RPC)
public interface UsersService {

    @WebMethod @WebResult(name = "User")
    UserDTO registerUser(@WebParam(name = "username") String username,
                         @WebParam(name = "password") String password,
                         @WebParam(name = "firstName") String firstName,
                         @WebParam(name = "lastName") String lastName,
                         @WebParam(name = "dateOfBirth") LocalDate dateOfBirth,
                         @WebParam(name = "identityCardNumber") String identityCardNumber);
    @WebMethod @WebResult(name = "User")
    UserDTO checkUserWithCredentials(@WebParam(name = "username") String username, @WebParam(name = "password") String password);
    @WebMethod @WebResult(name = "User")
    UserDTO findUserBy(@WebParam(name = "username") String username);
    @WebMethod @WebResult(name = "User")
    UserDTO retrieveUserBy(@WebParam(name = "identityCardNumber") String identityCardNumber);
    @WebMethod @WebResult(name = "OffBoardStatus")
    Boolean offBoardUser(@WebParam(name = "userId") int id);
    @WebMethod(exclude = true)
    Stream<UserDTO> findAllUsers();
    @WebMethod (exclude = true)
    Set<AppRole> findUserRoles(@WebParam(name = "user_id") Integer id);
}
