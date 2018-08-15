package com.israeldago.appUsersWS.configuration;

import com.israeldago.appUsersWS.repository.RolesRepository;
import com.israeldago.appUsersWS.repository.UsersRepository;
import com.israeldago.appUsersWS.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;
import java.util.logging.Logger;

@Configuration
public class AppConfig implements CommandLineRunner{

    @Autowired private UsersRepository usersRepository;
    @Autowired private RolesRepository rolesRepository;
    @Value("${ws.endpoint.wsdl}") private String wsdlUrl;
    @Value("http://${server.host}:${server.port}${server.servlet.context-path}${spring.h2.console.path}") private String dbConsoleUrl;
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());

    @Override
    public void run(String... args) throws Exception {
        Endpoint.publish(wsdlUrl, new UsersServiceImpl(usersRepository,rolesRepository));
        LOGGER.info("\n\nServices can be called via SOAP Requests\nWSDL available at "+ wsdlUrl);
        LOGGER.info("\n\nDB Console available at "+ dbConsoleUrl);
    }
}
