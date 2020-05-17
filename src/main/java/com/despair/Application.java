package com.despair;

import com.despair.jmx.DatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.*;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("jmx:type=com.despair.jmx.DatabaseManager");
        DatabaseManager databaseManager = new DatabaseManager();
        server.registerMBean(databaseManager, name);
        SpringApplication.run(Application.class, args);
    }

}
