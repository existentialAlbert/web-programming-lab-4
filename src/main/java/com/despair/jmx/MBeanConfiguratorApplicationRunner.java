package com.despair.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MBeanConfiguratorApplicationRunner implements ApplicationRunner {
    @Autowired
    private Management management;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("jmx:type=com.despair.jmx.Management");
        server.registerMBean(management, name);
    }
}
