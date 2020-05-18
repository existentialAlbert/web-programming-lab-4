package com.despair;

import com.despair.jmx.Management;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.*;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        SpringApplication.run(Application.class, args);
    }

}
