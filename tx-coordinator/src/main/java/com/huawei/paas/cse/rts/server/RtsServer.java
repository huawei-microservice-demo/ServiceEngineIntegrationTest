package com.huawei.paas.cse.rts.server;


import java.util.HashMap;

import org.apache.servicecomb.foundation.common.net.NetUtils;
import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.foundation.common.utils.Log4jUtils;
import org.apache.servicecomb.serviceregistry.RegistryUtils;
import org.apache.servicecomb.serviceregistry.api.registry.MicroserviceInstance;
import org.apache.servicecomb.serviceregistry.client.ServiceRegistryClient;
import org.jboss.jbossts.star.service.TMApplication;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.Undertow;

public class RtsServer {
    private static final int PORT = 8099;
    private static final Logger LOGGER = LoggerFactory.getLogger(RtsServer.class);

    public RtsServer() {
    }

    public static void main(String[] args) throws Exception {
        Log4jUtils.init();
        BeanUtils.init();
        UndertowJaxrsServer server = new UndertowJaxrsServer();
        server.start(Undertow.builder().addHttpListener(PORT, "0.0.0.0"));
        System.setProperty("recovery", "true");
        server.deploy(new TMApplication(), "/");
        updateProp(PORT);
    }

    private static void updateProp(int port) {
        ServiceRegistryClient client = RegistryUtils.getServiceRegistryClient();
        MicroserviceInstance instance = RegistryUtils.getMicroserviceInstance();
        HashMap map = new HashMap();
        String ip = NetUtils.getHostAddress();
        map.put("baseURI", "http://" + ip + ":" + port);
        if (client.updateInstanceProperties(instance.getServiceId(), instance.getInstanceId(), map)) {
            LOGGER.info("update coordinator base URI success!");
        }
    }
}