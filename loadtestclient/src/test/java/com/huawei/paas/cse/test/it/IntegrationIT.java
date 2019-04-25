package com.huawei.paas.cse.test.it;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.huawei.cse.wkapp.loadtest.PerformanceClient;
import com.huawei.paas.cse.demo.TestMgr;
import com.huawei.paas.cse.demo.framework.FrameworkVersionClient;
import com.huawei.paas.cse.demo.springmvc.client.SpringmvcClient;
import com.huawei.paas.cse.demo.springmvc.generics.GenericsTestServiceClient;

public class IntegrationIT {
    @BeforeClass
    public static void setUp() throws Exception {
        PerformanceClient.main(null);
    }

    @Test
    public void testSpringMVC() throws Exception {
        // run many times to test loadbalance
        SpringmvcClient.run();
        SpringmvcClient.run();
        SpringmvcClient.run();

        TestMgr.summary();
        Assert.assertTrue(TestMgr.isSuccess());
    }

    @Test
    public void testGenerics() throws Exception {
        // run many times to test loadbalance
        GenericsTestServiceClient.run();
        GenericsTestServiceClient.run();
        GenericsTestServiceClient.run();

        TestMgr.summary();
        Assert.assertTrue(TestMgr.isSuccess());
    }

    @Test
    public void testFrameworkVersion() throws Exception {
        // run many times to test loadbalance
        FrameworkVersionClient.run();
        FrameworkVersionClient.run();
        FrameworkVersionClient.run();

        TestMgr.summary();
        Assert.assertTrue(TestMgr.isSuccess());
    }
}
