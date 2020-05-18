package com.despair;

import com.despair.jmx.Management;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.*;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("jmx:type=com.despair.jmx.Management");
        Management management = new Management();
        server.registerMBean(management, name);
        SpringApplication.run(Application.class, args);
    }

}
